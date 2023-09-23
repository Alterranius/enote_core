package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Plan;
import com.eNote.eNote_core.models.PlanType;
import com.eNote.eNote_core.models.Unit;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface PlansShower {
    Optional<Plan> getPlanDetails(Plan plan);
    List<Plan> getProjectPlans(int id);
    List<Plan> getDepartPlans(int id);
    List<PlanType> getPlanTypes();
}
