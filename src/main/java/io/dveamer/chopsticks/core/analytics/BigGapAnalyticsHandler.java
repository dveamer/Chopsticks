package io.dveamer.chopsticks.core.analytics;

import io.dveamer.chopsticks.core.bean.Chopstick;
import io.dveamer.chopsticks.core.bean.Result;
import io.dveamer.chopsticks.core.enums.ChopstickType;

import java.util.function.BiFunction;

/**
 * Created by dveamer on 16. 2. 10.
 */
public class BigGapAnalyticsHandler implements BiFunction<Result, ChopstickType, Result> {

    private final BiFunction<Chopstick,Chopstick,Boolean> compare;

    public BigGapAnalyticsHandler(BiFunction<Chopstick, Chopstick, Boolean> compare) {
        this.compare = compare;
    }

    @Override
    public Result apply(final Result result, final ChopstickType type) {

        // TODO

        return new SmallGapAnalyticsHandler(compare).apply(result, type);
    }

}
