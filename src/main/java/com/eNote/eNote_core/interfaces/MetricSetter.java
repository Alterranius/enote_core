package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Metric;
import com.eNote.eNote_core.models.Unit;

/**
 * @author Alterranius
 */
public interface MetricSetter {
    String set(Metric metric, Unit unit);
    String reset(Metric metric, Unit unit);
}
