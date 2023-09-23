package com.eNote.eNote_core.services.accountConfig;

import com.eNote.eNote_core.interfaces.AccountManager;
import com.eNote.eNote_core.interfaces.AccountsShower;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.repositories.postgres.AccountDataRepository;
import com.eNote.eNote_core.repositories.postgres.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Alterranius
 */
@Service
public class AccountService implements AccountsShower, AccountManager {
    private final AccountRepository accountRepository;
    private final AccountDataRepository accountDataRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountDataRepository accountDataRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountDataRepository = accountDataRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Account> loadUserByUsername(String username) {
        return accountRepository.findAccountByAccountData_Username(username);
    }

    @Override
    public List<Account> getAccounts(int id) {
        Set<Account> accounts = new HashSet<>();
        accounts.addAll(accountRepository.findByRoles_Project_Id(id));
        List<Account> result = new ArrayList<>(accounts);
        return result;
    }

    @Override
    public Optional<Account> getAccount(int id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<AccountData> getAccountData(int id) {
        return Optional.of(getAccount(id).get().getAccountData());
    }

    @Override
    public String update(int id, Account account) {
        Optional<Account> currentAccount = accountRepository.findById(id);
        if (currentAccount.isPresent()) {
            currentAccount.get().setSurname(account.getSurname());
            currentAccount.get().setName(account.getName());
            currentAccount.get().setFathername(account.getFathername());
            currentAccount.get().setSpeciality(account.getSpeciality());
            currentAccount.get().setLevel(account.getLevel());
            currentAccount.get().setDescription(account.getDescription());
            currentAccount.get().setAbout(account.getAbout());
            currentAccount.get().setPortfolio(account.getPortfolio());
            currentAccount.get().setNickname(account.getNickname());
            accountRepository.save(currentAccount.get());
            return "Обновлено";
        } else {
            return "Неактуальный аккаунт";
        }
    }

    @Override
    public String update(int id, AccountData accountData) {
        Optional<AccountData> currentAccount = getAccountData(id);
        if (currentAccount.isPresent()) {
            currentAccount.get().setEmail(accountData.getEmail());
            currentAccount.get().setPhone(accountData.getPhone());
            currentAccount.get().setUsername(accountData.getUsername());
            currentAccount.get().setPassword(passwordEncoder.encode(accountData.getPassword()));
            accountDataRepository.save(currentAccount.get());
            return "Обновлено";
        } else {
            return "Неактуальный аккаунт";
        }
    }

    @Override
    public String delete(int id) {
        Optional<Account> currentAccount = accountRepository.findById(id);
        if (currentAccount.isPresent()) {
            accountRepository.delete(currentAccount.get());
            return "Удалено";
        } else {
            return "Неактуальный аккаунт";
        }
    }
}
