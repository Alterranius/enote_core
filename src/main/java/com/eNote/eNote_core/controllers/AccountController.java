package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.services.config.UserTarget;
import com.eNote.eNote_core.interfaces.AccountManager;
import com.eNote.eNote_core.interfaces.AccountsShower;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.services.accountConfig.util.AccountValidator;
import com.eNote.eNote_core.services.auth.util.AccountDataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping(value = "/account")
public class AccountController {
    private final AccountManager accountManager;
    private final AccountsShower accountsShower;
    private final AccountValidator accountValidator;
    private final AccountDataValidator accountDataValidator;
    private final UserTarget userTarget;

    @Autowired
    public AccountController(AccountManager accountManager, AccountsShower accountsShower, AccountValidator accountValidator, AccountDataValidator accountDataValidator, UserTarget userTarget) {
        this.accountManager = accountManager;
        this.accountsShower = accountsShower;
        this.accountValidator = accountValidator;
        this.accountDataValidator = accountDataValidator;
        this.userTarget = userTarget;
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateAccount(@RequestBody Account account,
                                                    @PathVariable("id") int id,
                                                    BindingResult bindingResult) {
        accountValidator.validate(account, bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        accountManager.update(id, account);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") int id) {
        try {
            accountManager.delete(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public Account getAccount() {
        try {
            Optional<Account> account = accountsShower.getAccount(userTarget.getTokenUser().getId());
            if (account.isPresent()) {
                return account.get();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @GetMapping("/security")
    @ResponseBody
    public AccountData getAccountData() {
        try {
            Optional<AccountData> account = accountsShower.getAccountData(userTarget.getTokenUser().getId());
            if (account.isPresent()) {
                return account.get();
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    @PutMapping("/security/{id}")
    public ResponseEntity<HttpStatus> updateAccount(@RequestBody AccountData accountData,
                                                    @PathVariable("id") int id) {
        accountManager.update(id, accountData);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/project/{id}")
    @ResponseBody
    public List<Account> getProjectAccounts(@PathVariable int id) {
        try {
            return accountsShower.getAccounts(id);
        } catch (Exception e) {
            return null;
        }
    }
}
