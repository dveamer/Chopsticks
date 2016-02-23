package io.dveamer.chopsticks;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Report;
import io.dveamer.chopsticks.core.parse.FileLineParser;
import io.dveamer.chopsticks.core.parse.ResultSetParser;

import java.sql.ResultSet;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class ChopsticksReception {

    private ReceptionData receptionData = new ReceptionData();

    public ChopsticksReception setCompare(BiFunction<Chopstick, Chopstick, Boolean> compare) {
        this.receptionData.setCompare(compare);
        return this;
    }

    public ChopsticksReception setLeftMapper(UnaryOperator<Chopstick> leftMapper) {
        this.receptionData.setLeftMapper(leftMapper);
        return this;
    }

    public ChopsticksReception setRightMapper(UnaryOperator<Chopstick> rightMapper) {
        this.receptionData.setRightMapper(rightMapper);
        return this;
    }

    public ChopsticksReception addLeftChopsticks(List<Chopstick> chopsticks) {
        this.receptionData.getLeftChopsticks().addAll(chopsticks);
        return this;
    }

    public ChopsticksReception addRightChopsticks(List<Chopstick> chopsticks) {
        this.receptionData.getRightChopsticks().addAll(chopsticks);
        return this;
    }

    public ChopsticksReception addRightParser(ResultSet rs, Function<ResultSet,Chopstick> parser) {
        this.receptionData.getRightParser().add(new ResultSetParser(rs, parser));
        return this;
    }

    public ChopsticksReception addRightParser(ResultSet rs, Function<ResultSet,Chopstick> parser, Predicate<Chopstick> filter) {
        this.receptionData.getRightParser().add(new ResultSetParser(rs, parser, filter));
        return this;
    }

    public ChopsticksReception addRightFileLineParser(String fileName, Function<String,Chopstick> parser) {
        this.receptionData.getRightParser().add(new FileLineParser(fileName, parser));
        return this;
    }

    public ChopsticksReception addRightFileLineParser(String fileName, Function<String,Chopstick> parser, Predicate<Chopstick> filter) {
        this.receptionData.getRightParser().add(new FileLineParser(fileName, parser, filter));
        return this;
    }

    public ChopsticksReception addLeftParser(ResultSet rs, Function<ResultSet,Chopstick> parser) {
        this.receptionData.getLeftParser().add(new ResultSetParser(rs, parser));
        return this;
    }

    public ChopsticksReception addLeftParser(ResultSet rs, Function<ResultSet,Chopstick> parser, Predicate<Chopstick> filter) {
        this.receptionData.getLeftParser().add(new ResultSetParser(rs, parser, filter));
        return this;
    }

    public ChopsticksReception addLeftFileLineParser(String fileName, Function<String,Chopstick> parser) {
        this.receptionData.getLeftParser().add(new FileLineParser(fileName, parser));
        return this;
    }

    public ChopsticksReception addLeftFileLineParser(String fileName, Function<String,Chopstick> parser, Predicate<Chopstick> filter) {
        this.receptionData.getLeftParser().add(new FileLineParser(fileName, parser, filter));
        return this;
    }

    public Report execute(){
        ReceptionData tempReceptionData = receptionData;
        receptionData = null;
        return new Controller().execute(tempReceptionData);
    }

}
