package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;

/**
 * @author Alterranius
 */
public interface PasswordChanger {
    String changePassword(Account account, String oldPassword, String password);
}
