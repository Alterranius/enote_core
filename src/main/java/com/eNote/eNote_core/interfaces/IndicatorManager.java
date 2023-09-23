package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Indicator;

/**
 * @author Alterranius
 */
public interface IndicatorManager {
    String create(Indicator indicator);
    String update(Indicator oldIndicator, Indicator indicator);
    String delete(Indicator indicator);
}
