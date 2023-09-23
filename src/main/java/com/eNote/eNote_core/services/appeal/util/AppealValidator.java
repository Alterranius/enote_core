package com.eNote.eNote_core.services.appeal.util;

import com.eNote.eNote_core.models.Appeal;
import com.eNote.eNote_core.services.appeal.AppealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class AppealValidator implements Validator {
    private final AppealService appealService;

    @Autowired
    public AppealValidator(AppealService appealService) {
        this.appealService = appealService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Appeal.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Appeal appeal = (Appeal) target;
    }
}
