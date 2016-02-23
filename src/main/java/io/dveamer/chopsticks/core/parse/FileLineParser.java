package io.dveamer.chopsticks.core.parse;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class FileLineParser implements Supplier<List<Chopstick>> {

    private String fileName;
    private Function<String,Chopstick> mapper;
    private Predicate<Chopstick> filter;

    public FileLineParser(String fileName, Function<String, Chopstick> mapper) {
        this.fileName = fileName;
        this.mapper = mapper;
        this.filter = c -> true;
    }

    public FileLineParser(String fileName, Function<String, Chopstick> mapper, Predicate<Chopstick> filter) {
        this.fileName = fileName;
        this.filter = filter;
        this.mapper = mapper;
    }

    @Override
    public List<Chopstick> get() {
        return (List<Chopstick>) StreamSupporter.fileLineStream(fileName)
                .map(mapper)
                .filter(c -> filter.test(c))
                .collect(Collectors.toList());
    }
}
