package com.eNote.eNote_core.services.auth.util;

import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.services.auth.AccountDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class AccountDataValidator implements Validator {
    private final AccountDataService accountDataService;

    @Autowired
    public AccountDataValidator(AccountDataService accountDataService) {
        this.accountDataService = accountDataService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountData accountData = (AccountData) target;
        if (accountDataService.loadUserByUsername(accountData.getUsername()).isPresent()) {
            errors.rejectValue("username", "", "Человек с таким именем существует");
        }
    }
}
