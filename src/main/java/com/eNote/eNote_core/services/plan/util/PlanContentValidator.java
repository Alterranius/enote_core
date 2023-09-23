package com.eNote.eNote_core.services.plan.util;

import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.models.PlanContent;
import com.eNote.eNote_core.services.plan.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class PlanContentValidator implements Validator {
    private final PlanService planService;

    @Autowired
    public PlanContentValidator(PlanService planService) {
        this.planService = planService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Delegation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlanContent planContent = (PlanContent) target;
    }
}
