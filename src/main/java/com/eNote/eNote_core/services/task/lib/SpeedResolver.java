package com.eNote.eNote_core.services.task.lib;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Alterranius
 */
@Component
public class SpeedResolver {
    public double averageCumulate(double current, int length, Date completedAt, Date delegatedAt) {
        return (double) Math.round(
                ((length * current + ((double) Math.abs(completedAt.getTime() - delegatedAt.getTime()) / (3600000)) ) / (length + 1))
        );
    }
}
