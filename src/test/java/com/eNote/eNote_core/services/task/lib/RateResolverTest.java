package com.eNote.eNote_core.services.task.lib;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Alterranius
 */
public class RateResolverTest {
    private final RateResolver rateResolver = new RateResolver();

    @Test(timeout = 80L)
    public void rateResolverCorrect() {
        // arrange
        double await = 0.7d;
        double result;
        int completed = 7;
        int failed = 3;

        // act
        result = rateResolver.calculateRating(completed, failed);

        // assert
        Assertions.assertEquals(await, result);
    }

}