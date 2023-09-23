package com.eNote.eNote_core.services.delegation.util;

import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.services.delegation.DelegationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class DelegationValidator implements Validator {
    private final DelegationsService delegationsService;

    @Autowired
    public DelegationValidator(DelegationsService delegationsService) {
        this.delegationsService = delegationsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Delegation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Delegation delegation = (Delegation) target;
    }
}
