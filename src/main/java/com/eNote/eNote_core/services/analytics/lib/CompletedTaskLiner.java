package com.eNote.eNote_core.services.analytics.lib;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * @author Alterranius
 */
@Component
public class CompletedTaskLiner {
    public Map<String, String> getCompletedTaskLine(SortedMap<Date, Integer> tasks) {
        LocalDate min = tasks.firstKey().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate max = tasks.lastKey().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Map<String, String> result = new LinkedHashMap<>();
        for (int i = 0; i <= Math.abs(ChronoUnit.DAYS.between(max, min)); i++) {
            LocalDate anotherDay = min.plusDays(i);
            int sum = 0;
            while (tasks.firstKey()
                    .toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
                    .equals(anotherDay)) {
                sum += 1;
                tasks.remove(tasks.firstKey());
                if (tasks.isEmpty()) {
                    break;
                }
            }
            result.put(anotherDay.toString(), String.valueOf(sum));
        }
        return result;
    }
}
