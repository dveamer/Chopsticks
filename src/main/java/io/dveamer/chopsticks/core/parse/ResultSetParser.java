package io.dveamer.chopsticks.core.parse;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class ResultSetParser implements Supplier<List<Chopstick>> {

    private ResultSet rs;
    private Function<ResultSet,Chopstick> mapper;
    private Predicate<Chopstick> filter;

    public ResultSetParser(ResultSet rs, Function<ResultSet, Chopstick> mapper) {
        this.rs = rs;
        this.mapper = mapper;
        this.filter = c -> true;
    }

    public ResultSetParser(ResultSet rs, Function<ResultSet, Chopstick> mapper, Predicate<Chopstick> filter) {
        this.rs = rs;
        this.filter = filter;
        this.mapper = mapper;
    }

    @Override
    public List<Chopstick> get() {
        return StreamSupporter.stream(rs)
                .map(mapper)
                .filter(c -> filter.test((Chopstick) c))
                .collect(Collectors.toList());
    }
}
