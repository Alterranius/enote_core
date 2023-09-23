package com.eNote.eNote_core.interfaces;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
public interface AccountsShower {
    List<Account> getAccounts(int id);
    Optional<Account> getAccount(int id);
    Optional<AccountData> getAccountData(int id);

}
