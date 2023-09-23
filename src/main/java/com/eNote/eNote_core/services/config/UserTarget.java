package com.eNote.eNote_core.services.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.security.AccountDetails;
import com.eNote.eNote_core.services.accountConfig.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Alterranius
 */
@Component
public class UserTarget {
    @Value("${jwt_secret}")
    private String secret;

    private final AccountService accountService;

    @Autowired
    public UserTarget(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account getTokenUser() throws JWTVerificationException {
        return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAccountData().getAccount();
    }
}
