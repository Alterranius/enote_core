package com.eNote.eNote_core.controllers;

import com.eNote.eNote_core.dto.AccountDTO;
import com.eNote.eNote_core.dto.AccountDataDTO;
import com.eNote.eNote_core.dto.AuthenticationDTO;
import com.eNote.eNote_core.dto.UserDTO;
import com.eNote.eNote_core.models.Account;
import com.eNote.eNote_core.models.AccountData;
import com.eNote.eNote_core.models.Role;
import com.eNote.eNote_core.security.JWTUtil;
import com.eNote.eNote_core.services.auth.AccountDataService;
import com.eNote.eNote_core.services.auth.AuthResponseBody;
import com.eNote.eNote_core.services.auth.RegistrationService;
import com.eNote.eNote_core.services.auth.util.AccountDataValidator;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @author Alterranius
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AccountDataValidator accountDataValidator;
    private final RegistrationService registrationService;
    private final AccountDataService accountDataService;
    private final DaoAuthenticationProvider authenticationProvider;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private static final Logger authLogger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(AccountDataValidator accountDataValidator, RegistrationService registrationService, AccountDataService accountDataService, DaoAuthenticationProvider authenticationProvider, JWTUtil jwtUtil, ModelMapper modelMapper /*AuthenticationManager authenticationManager*/) {
        this.accountDataValidator = accountDataValidator;
        this.registrationService = registrationService;
        this.accountDataService = accountDataService;
        this.authenticationProvider = authenticationProvider;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<AuthResponseBody> performRegistration(@RequestBody UserDTO userDTO,
                                      BindingResult bindingResult) {
        accountDataValidator.validate(userDTO.getAccountDataDTO(), bindingResult);
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new AuthResponseBody(null, false, null));
        }
        authLogger.info("Registration has been completed for user {}", userDTO.getAccountDataDTO());
        Set<Role> roles = Set.of(registrationService.register(userDTO.getAccount(), userDTO.getAccountDataDTO()));
        String token = jwtUtil.generateToken(userDTO.getAccountDataDTO().getUsername());
        return ResponseEntity.ok(new AuthResponseBody(token, true, roles));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseBody> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        try {
            Authentication authentication = authenticationProvider.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new AuthResponseBody(null, false, null));
        }
        authLogger.info("User {} log in", authenticationDTO.getUsername());
        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        Set<Role> roles = new HashSet<>(accountDataService.loadUserByUsername(authenticationDTO.getUsername()).get().getAccount().getRoles());
        return ResponseEntity.ok(new AuthResponseBody(token, true, roles));
    }

    public AccountData convertToAccountData(AccountDataDTO accountDataDTO) {
        return this.modelMapper.map(accountDataDTO, AccountData.class);
    }

    public Account convertToAccount(AccountDTO accountDTO) {
        return this.modelMapper.map(accountDTO, Account.class);
    }
}
