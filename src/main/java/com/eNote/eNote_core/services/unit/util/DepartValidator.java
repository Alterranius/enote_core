package com.eNote.eNote_core.services.unit.util;

import com.eNote.eNote_core.models.Depart;
import com.eNote.eNote_core.services.unit.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class DepartValidator implements Validator {
    private final DepartService departService;

    @Autowired
    public DepartValidator(DepartService departService) {
        this.departService = departService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Depart.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Depart depart = (Depart) target;
    }
}
