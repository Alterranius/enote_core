package com.eNote.eNote_core.services.task.lib;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alterranius
 */
public class SpeedResolverTest {
    private final SpeedResolver speedResolver = new SpeedResolver();

    @Test(timeout = 80L)
    public void speedResolverCorrect() throws ParseException {
        // arrange
        double await = 24d;
        double result;
        double speed = 24d;
        Date delegatedAt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-15 00:00:00");
        Date completedAt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-16 00:00:00");
        int length = 2;

        // act
        result = speedResolver.averageCumulate(speed, length, completedAt, delegatedAt);

        // assert
        Assertions.assertEquals(await, result);
    }

}