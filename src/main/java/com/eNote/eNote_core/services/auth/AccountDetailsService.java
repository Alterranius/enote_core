package com.eNote.eNote_core.services.auth;

import com.eNote.eNote_core.repositories.postgres.AccountDataRepository;
import com.eNote.eNote_core.security.AccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Alterranius
 */
@Service
public class AccountDetailsService implements UserDetailsService {
    private final AccountDataRepository accountDataRepository;

    @Autowired
    public AccountDetailsService(AccountDataRepository accountDataRepository) {
        this.accountDataRepository = accountDataRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AccountDetails(accountDataRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден!")));
    }
}
