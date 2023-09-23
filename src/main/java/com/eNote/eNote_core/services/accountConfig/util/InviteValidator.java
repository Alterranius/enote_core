package com.eNote.eNote_core.services.accountConfig.util;

import com.eNote.eNote_core.models.Delegation;
import com.eNote.eNote_core.models.Invite;
import com.eNote.eNote_core.services.accountConfig.InviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Alterranius
 */
@Component
public class InviteValidator implements Validator {
    private final InviteService inviteService;

    @Autowired
    public InviteValidator(InviteService inviteService) {
        this.inviteService = inviteService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Delegation.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Invite invite = (Invite) target;
    }
}
