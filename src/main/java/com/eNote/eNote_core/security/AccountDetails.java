package com.eNote.eNote_core.security;

import com.eNote.eNote_core.models.AccountData;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Alterranius
 */
public class AccountDetails implements UserDetails {
    private final AccountData accountData;

    public AccountDetails(AccountData accountData) {
        this.accountData = accountData;
    }

    // Коллекция прав пользователя
    @Override
    @Transactional
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority( accountData.getAccount().getRoles().toString()));
    }

    @Override
    public String getPassword() {
        return this.accountData.getPassword();
    }

    @Override
    public String getUsername() {
        return this.accountData.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Просрочка аккаунта
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // БАН
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Просрочка пароля
    }

    @Override
    public boolean isEnabled() {
        return true; // Включён в работу
    }

    // Для данных аутентифицированного пользователя
    public AccountData getAccountData() {
        return this.accountData;
    }
}
