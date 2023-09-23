package com.eNote.eNote_core.services.task.lib;

import org.springframework.stereotype.Component;

/**
 * @author Alterranius
 */
@Component
public class RateResolver {
    public double calculateRating(int completed, int failed) {
        return (double) Math.round(((double) completed / (completed + failed)) * 100) / 100;
    }
}
