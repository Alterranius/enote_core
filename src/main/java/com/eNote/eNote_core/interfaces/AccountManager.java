package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;

/**
 * @author Alterranius
 */
public interface AccountManager {
    String update(int id, Account account);
    String update(int id, AccountData accountData);
    String delete(int id);
}
