package com.eNote.eNote_core.services.accountConfig.util;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.services.accountConfig.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class AccountValidator implements Validator {
    private final AccountService accountService;

    @Autowired
    public AccountValidator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Delegation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
    }
}
