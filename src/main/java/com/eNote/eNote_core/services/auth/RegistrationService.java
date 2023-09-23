package com.eNote.eNote_core.services.auth;

import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.repositories.postgres.AccountDataRepository;
import com.eNote.eNote_core.repositories.postgres.AccountRepository;
import com.eNote.eNote_core.repositories.postgres.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Alterranius
 */
@Service
public class RegistrationService {
    private final AccountDataRepository accountDataRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final Role INITIAL_ROLE;

    @Autowired
    public RegistrationService(AccountDataRepository accountDataRepository, PasswordEncoder passwordEncoder, AccountRepository accountRepository, RoleRepository roleRepository) {
        this.accountDataRepository = accountDataRepository;
        this.passwordEncoder = passwordEncoder;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        Optional<Role> role = roleRepository.findById(0);
        INITIAL_ROLE = role.orElseGet(Role::new);
    }

    @Transactional
    public Role register(Account account, AccountData accountData) {
        accountData.setPassword(passwordEncoder.encode(accountData.getPassword())); // Шифрование
        account.setAccountData(accountData);
        accountData.setAccount(account);
        account = accountRepository.save(account);
        account.setRoles(new ArrayList<>());
        account.getRoles().add(roleRepository.findById(0).get());
        accountRepository.save(account);
        return INITIAL_ROLE;
    }
}
