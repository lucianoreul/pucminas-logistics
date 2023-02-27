package com.logistics.auth.controller;

import com.logistics.auth.dto.AccountCredentialsDTO;
import com.logistics.auth.dto.AccountDTO;
import com.logistics.auth.dto.TokenDTO;
import com.logistics.auth.service.AccountService;
import com.logistics.auth.service.AuthenticationService;
import com.logistics.auth.service.TokenAuthenticationService;
import com.logistics.auth.vo.LoggedAccountVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.logging.Logger;

/***
 * Rest Controller for Authentication microsservice
 *
 * @author LucianoReul
 */
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    /**
     * Authentication service
     */
    private final AuthenticationService authService;

    /**
     * Account service
     */
    private final AccountService accountService;

    /**
     * Token service
     */
    private final TokenAuthenticationService tokenAuthenticationService;

    /**
     * Logger object
     */
    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());

    /**
     * Login endpoint
     *
     * @param credentials
     * @return LoggedAccountDTO
     */
    @PostMapping(value = "/login")
    public ResponseEntity<LoggedAccountVO> login(@RequestBody AccountCredentialsDTO credentials) {
        LoggedAccountVO loggedAccountDTO = authService.authenticate(credentials);
        LOGGER.info("Successfully authenticated with: " + credentials.getUsername());
        return ResponseEntity.ok(loggedAccountDTO);
    }

    /**
     * Refresh authentication endpoint
     *
     * @param body TokenDTO with refresh token
     * @return LoggedAccountDTO
     */
    @PostMapping(value = "/refresh")
    public ResponseEntity<LoggedAccountVO> refresh(@RequestBody TokenDTO body) {
        return ResponseEntity.ok(authService.refresh(body));
    }

    /**
     *  Create account endpoint
     *
     * @param accountDTO object with credentials
     * @return HttpStatus ok if success
     */
    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@Valid @NotNull @RequestBody AccountDTO accountDTO) {
        this.accountService.createAccount(accountDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Token validate endpoint, necessary to gateway service validate a token
     *
     * @param token
     * @return LoggedAccountDTO
     */
    @PostMapping(value = "/validate")
    public ResponseEntity<LoggedAccountVO> validate(@Valid @NotNull @RequestParam String token) {
        LOGGER.info("Trying to validate token: " + token);
        return ResponseEntity.ok(tokenAuthenticationService.isTokenInvalid(token));
    }
}
