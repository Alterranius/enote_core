package com.eNote.eNote_core.services.analytics.lib;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Alterranius
 */
@Component
public class WeekPeeker {
    public Date getPreviousWeek(LocalDate date) {
        final int dayOfWeek = date.getDayOfWeek().getValue();
        final LocalDate from = date.minusDays(dayOfWeek + 6);
        return Date.from(from.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
