package com.eNote.eNote_core.services.analytics.lib;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author Alterranius
 */
@Component
public class AvgSolver {
    public double getSpeed(Map<Date, Date> dates) {
        double sum = 0d;
        for (Map.Entry<Date, Date> task : dates.entrySet()) {
            sum += (double) Math.abs(
                    (task.getKey().getTime() - task.getValue().getTime()) / (3600000));
        }
        return (double) Math.round((sum / dates.size()) * 100000) / 100;
    }
}
