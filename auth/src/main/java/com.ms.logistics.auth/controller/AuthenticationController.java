package com.ms.logistics.auth.controller;

import com.ms.logistics.auth.dto.AccountDTO;
import com.ms.logistics.auth.dto.LoggedAccountDTO;
import com.ms.logistics.auth.dto.TokenDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.security.AccountCredentials;
import com.ms.logistics.auth.service.AccountService;
import com.ms.logistics.auth.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

@RestController
@AllArgsConstructor
@RequestMapping("api/auth")
public class AuthenticationController {

    private final AuthenticationService authService;
    private final AccountService accountService;

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    @PostMapping(value = "/login")
    public ResponseEntity<LoggedAccountDTO> login(@RequestBody AccountCredentials credentials) throws BusinessException {
        LoggedAccountDTO loggedAccountDTO = authService.authenticate(credentials);
        LOGGER.info("Successfully authenticated with: " + credentials.getUsername());
        return ResponseEntity.ok(loggedAccountDTO);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<LoggedAccountDTO> refresh(@RequestBody TokenDTO body) throws BusinessException {
        return ResponseEntity.ok(authService.refresh(body));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> register(@Valid @NotNull @RequestBody AccountDTO accountDTO) throws BusinessException {
        this.accountService.createAccount(accountDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
