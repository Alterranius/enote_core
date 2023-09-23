package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Indicator;
import com.eNote.eNote_core.models.Unit;

/**
 * @author Alterranius
 */
public interface IndicatorSetter {
    String set(Indicator indicator, Unit unit);
    String reset(Indicator indicator, Unit unit);
}
