package com.eNote.eNote_core.services.analytics.lib;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Alterranius
 */
public class CompletedTaskLinerTest {
    private final CompletedTaskLiner completedTaskLiner = new CompletedTaskLiner();

    @Test(timeout = 100L)
    public void taskCompleterCorrect() throws ParseException {
        // arrange
        Map<String, String> result;
        Map<String, String> await = new HashMap<>();
        await.put("2023-05-05", "2");
        await.put("2023-05-06", "3");
        await.put("2023-05-07", "0");
        await.put("2023-05-08", "1");

        SortedMap<Date, Integer> input = new TreeMap<>();
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-05 04:03:02"), 0);
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-05 04:01:02"), 1);
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-06 04:04:02"), 2);
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-06 03:03:02"), 3);
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-06 02:03:02"), 4);
        input.put(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2023-05-08 09:03:02"), 5);

        // act
        result = completedTaskLiner.getCompletedTaskLine(input);

        // assert
        Assertions.assertEquals(await, result);
    }

}