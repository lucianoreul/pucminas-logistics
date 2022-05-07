package com.ms.logistics.auth.controller;

import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.dto.TokenDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.security.AccountCredentials;
import com.ms.logistics.auth.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class AuthenticationController {

    private final AuthenticationService authService;

    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoggedUserDTO> login(@RequestBody AccountCredentials credentials) throws BusinessException {
        LoggedUserDTO loggedUserDTO = authService.authenticate(credentials);
        LOGGER.info("Successfully authenticated with: " + credentials.getUsername());
        return ResponseEntity.ok(loggedUserDTO);
    }

    @PostMapping(value = "/refresh")
    public ResponseEntity<LoggedUserDTO> refresh(@RequestBody TokenDTO body) throws BusinessException {
        return ResponseEntity.ok(authService.refresh(body));
    }
}
