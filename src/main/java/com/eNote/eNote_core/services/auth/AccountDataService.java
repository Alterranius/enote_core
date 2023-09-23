package com.eNote.eNote_core.services.auth;

import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.repositories.postgres.AccountDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * @author Alterranius
 */
@Service
public class AccountDataService {
    private final AccountDataRepository accountDataRepository;

    @Autowired
    public AccountDataService(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    public Optional<AccountData> loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountDataRepository.findByUsername(username);
    }
}
