package io.dveamer.chopsticks.core.analytics;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Result;
import io.dveamer.chopsticks.core.enums.ChopstickType;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class SmallGapAnalyticsHandler implements BiFunction<Result, ChopstickType, Result> {

    private final BiFunction<Chopstick,Chopstick,Boolean> compare;

    public SmallGapAnalyticsHandler(BiFunction<Chopstick, Chopstick, Boolean> compare) {
        this.compare = compare;
    }

    @Override
    public Result apply(final Result result, final ChopstickType type) {

        switch (type){

            case Left:
                return left(result);
            case Right:
                return right(result);
        }

        throw new RuntimeException("Invalid Chopstick Type");
    }


    public Result left(final Result result) {

        StreamSupporter<Chopstick> streamSupporter = new StreamSupporter<Chopstick>();

        final List<Chopstick> leftNotSameKeyChopsticks = streamSupporter.stream(result.getLeftChopsticks())
                .filter(cl -> !result.getRightMap().containsKey(cl.getKey()) || result.getRightMap().get(cl.getKey()).stream().filter(cr -> compare.apply(cl,cr)).count() < 1 )
                .collect(Collectors.toList());

        final List<Chopstick> leftOnlyChops = streamSupporter.stream(leftNotSameKeyChopsticks)
                .filter(c -> !result.getRightMap().containsKey(c.getKey()))
                .collect(Collectors.toList());

        final List<Chopstick> leftCrashChops = streamSupporter.stream(leftNotSameKeyChopsticks)
                .filter(c -> result.getRightMap().containsKey(c.getKey()))
                .collect(Collectors.toList());

        final Map<String, List<Chopstick>> leftDuplicatedMap = StreamSupporter.streamString(result.getLeftMap().keySet())
                .filter(k -> result.getLeftMap().get(k).size() > 1)
                .collect(Collectors.toMap(k -> k, k -> result.getLeftMap().get(k)));

        result.setLeftNotSameKeyChopsticks(leftNotSameKeyChopsticks);
        result.setLeftOnlyChops(leftOnlyChops);
        result.setLeftCrashChops(leftCrashChops);
        result.setLeftDuplicatedMap(leftDuplicatedMap); // TODO 결과가 틀텼다가 맞았다가 함

        return result;

    }


    public Result right(final Result result) {

        StreamSupporter<Chopstick> streamSupporter = new StreamSupporter<Chopstick>();

        final List<Chopstick> rightNotSameKeyChopsticks = streamSupporter.stream(result.getRightChopsticks())
                .filter(cr -> !result.getLeftMap().containsKey(cr.getKey()) || result.getLeftMap().get(cr.getKey()).stream().filter(cl -> compare.apply(cl,cr)).count() < 1 )
                .collect(Collectors.toList());

        final List<Chopstick> rightOnlyChops = streamSupporter.stream(rightNotSameKeyChopsticks)
                .filter(c -> !result.getLeftMap().containsKey(c.getKey()))
                .collect(Collectors.toList());

        final List<Chopstick> rightCrashChops = streamSupporter.stream(rightNotSameKeyChopsticks)
                .filter(c -> result.getLeftMap().containsKey(c.getKey()))
                .collect(Collectors.toList());

        final Map<String, List<Chopstick>> rightDuplicatedMap = StreamSupporter.streamString(result.getRightMap().keySet())
                .filter(k -> result.getRightMap().get(k).size() > 1)
                .collect(Collectors.toMap(k -> k, k -> result.getRightMap().get(k)));

        result.setRightNotSameKeyChopsticks(rightNotSameKeyChopsticks);
        result.setRightOnlyChops(rightOnlyChops);
        result.setRightCrashChops(rightCrashChops);
        result.setRightDuplicatedMap(rightDuplicatedMap);

        return result;
    }

}
