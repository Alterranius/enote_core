package com.eNote.eNote_core.dto;


import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Alterranius
 */
public class UserDTO {
    @JsonProperty("Account")
    private Account account;
    @JsonProperty("AccountData")
    private AccountData accountData;

    public UserDTO() {
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountData getAccountDataDTO() {
        return accountData;
    }

    public void setAccountDataDTO(AccountData accountDataDTO) {
        this.accountData = accountDataDTO;
    }
}
