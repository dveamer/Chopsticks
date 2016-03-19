package io.dveamer.chopsticks.core.analytics;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Result;
import io.dveamer.chopsticks.core.enums.ChopstickType;
import io.dveamer.chopsticks.core.suporter.StreamSupporter;

import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class Analyst {

    private final BiFunction<Chopstick,Chopstick,Boolean> compare;
    private final List<Chopstick> leftChopsticks;
    private final List<Chopstick> rightChopsticks;

    public Analyst(final BiFunction<Chopstick, Chopstick, Boolean> compare
            , final List<Chopstick> leftChopsticks
            , final List<Chopstick> rightChopsticks) {
        this.compare = compare;
        this.leftChopsticks = leftChopsticks;
        this.rightChopsticks = rightChopsticks;
    }

    public Result execute(){
        Result result = normal();

        if(result.getNormalList().size() == result.getLeftMap().size()
                && result.getNormalList().size() == result.getRightMap().size()){
            return result;
        }

        result = strategy(result, ChopstickType.Left).apply(result, ChopstickType.Left);
        result = strategy(result, ChopstickType.Right).apply(result, ChopstickType.Right);

        return result;
    }

    private Result normal(){

        final StreamSupporter<Chopstick> streamSupporter = new StreamSupporter<>();
        final HashMap<String, List<Chopstick>> leftMap = (HashMap<String, List<Chopstick>>) streamSupporter.stream(leftChopsticks)
                .collect(Collectors.groupingBy(Chopstick::getKey));

        final HashMap<String, List<Chopstick>> rightMap = (HashMap<String, List<Chopstick>>) streamSupporter.stream(rightChopsticks)
                .collect(Collectors.groupingBy(Chopstick::getKey));

        final List<Chopstick> normalList = StreamSupporter.streamString(rightMap.keySet())
                .filter(k -> leftMap.containsKey(k)
                        && leftMap.get(k).stream().filter(c1 -> rightMap.get(k).stream().filter(cr -> compare.apply(c1, cr)).findFirst().isPresent()).findFirst().isPresent())
                        .map(k -> leftMap.get(k).stream().filter(c1 -> rightMap.get(k).stream().filter(cr -> compare.apply(c1, cr)).findFirst().isPresent()).findFirst().get())
                .collect(Collectors.toList());


        Result result = new Result();
        result.setLeftChopsticks(leftChopsticks);
        result.setRightChopsticks(rightChopsticks);
        result.setLeftMap(leftMap);
        result.setRightMap(rightMap);
        result.setNormalList(normalList);

        return result;
    }

    private BiFunction<Result, ChopstickType, Result> strategy(final Result result, final ChopstickType type){
        int targetListSize = 0;

        switch (type){
            case Left:
                targetListSize = result.getLeftChopsticks().size();
                break;
            case Right:
                targetListSize = result.getRightChopsticks().size();
                break;
        }

        if(targetListSize==result.getNormalList().size()){
            return (r, c) -> r;
        }

        if( checkSmallSize(result.getRightChopsticks().size(), result.getNormalList().size()) ){
            return new SmallGapAnalyticsHandler(compare);
        }

        return new BigGapAnalyticsHandler(compare);
    }

    private boolean checkSmallSize(final int target, final int normal){
        if( (target-normal) > (target / 10)){ // TODO
            return false;
        }
        return true;
    }

}
