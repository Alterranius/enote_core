package com.eNote.eNote_core.services.auth;

import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.repositories.postgres.AccountDataRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * @author Alterranius
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsServiceTest {
    @Mock
    private AccountDataRepository accountDataRepository;

    @InjectMocks
    private AccountDetailsService accountDetailsService;

    @Test
    public void loadUserByUsername_throwsException_test() {
        String username = "Dummy";
        Mockito.when(accountDataRepository.findByUsername(username)).thenReturn(Optional.empty());
        Assertions.assertThrows(UsernameNotFoundException.class, () -> accountDetailsService.loadUserByUsername(username));
    }

    @Test
    public void loadUserByUsername_userFound_test() {
        String username = "Burmos";
        String password = "Burmos";
        AccountData accountData = new AccountData(0, null, username, password, null, null, null);
        Mockito.when(accountDataRepository.findByUsername(username)).thenReturn(Optional.of(accountData));
        Assertions.assertEquals(accountData.getUsername(), accountDetailsService.loadUserByUsername(username).getUsername());
    }

}