package io.dveamer.chopsticks;

import io.dveamer.chopsticks.core.bean.Chopstick;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class ReceptionData {

    private UnaryOperator<Chopstick> leftMapper;
    private UnaryOperator<Chopstick> rightMapper;

    private BiFunction<Chopstick, Chopstick, Boolean> compare;

    private List<Chopstick> leftChopsticks;
    private List<Chopstick> rightChopsticks;

    private List<Supplier<List<Chopstick>>> leftParser;
    private List<Supplier<List<Chopstick>>> rightParser;

    protected ReceptionData(){
        leftChopsticks = new ArrayList<>();
        rightChopsticks = new ArrayList<>();
        leftParser = new ArrayList<>();
        rightParser = new ArrayList<>();
    }

    public UnaryOperator<Chopstick> getLeftMapper() {
        return leftMapper;
    }

    public ReceptionData setLeftMapper(UnaryOperator<Chopstick> leftMapper) {
        this.leftMapper = leftMapper;
        return this;
    }

    public UnaryOperator<Chopstick> getRightMapper() {
        return rightMapper;
    }

    public ReceptionData setRightMapper(UnaryOperator<Chopstick> rightMapper) {
        this.rightMapper = rightMapper;
        return this;
    }

    public BiFunction<Chopstick, Chopstick, Boolean> getCompare() {
        return compare;
    }

    public ReceptionData setCompare(BiFunction<Chopstick, Chopstick, Boolean> compare) {
        this.compare = compare;
        return this;
    }

    public List<Chopstick> getLeftChopsticks() {
        return leftChopsticks;
    }

    public ReceptionData setLeftChopsticks(List<Chopstick> leftChopsticks) {
        this.leftChopsticks = leftChopsticks;
        return this;
    }

    public List<Chopstick> getRightChopsticks() {
        return rightChopsticks;
    }

    public ReceptionData setRightChopsticks(List<Chopstick> rightChopsticks) {
        this.rightChopsticks = rightChopsticks;
        return this;
    }

    public List<Supplier<List<Chopstick>>> getLeftParser() {
        return leftParser;
    }

    public ReceptionData setLeftParser(List<Supplier<List<Chopstick>>> leftParser) {
        this.leftParser = leftParser;
        return this;
    }

    public List<Supplier<List<Chopstick>>> getRightParser() {
        return rightParser;
    }

    public ReceptionData setRightParser(List<Supplier<List<Chopstick>>> rightParser) {
        this.rightParser = rightParser;
        return this;
    }
}
