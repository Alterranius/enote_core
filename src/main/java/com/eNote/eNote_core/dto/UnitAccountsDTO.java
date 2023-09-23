package com.eNote.eNote_core.dto;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.Role;

/**
 * @author Alterranius
 */
public class UnitAccountsDTO {
    private Account account;
    private Role role;

    public UnitAccountsDTO() {
    }

    public UnitAccountsDTO(Account account, Role role) {
        this.account = account;
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
