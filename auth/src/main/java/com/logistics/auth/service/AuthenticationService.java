package com.logistics.auth.service;

import com.logistics.auth.model.Account;
import com.logistics.auth.vo.LoggedAccountVO;
import com.logistics.auth.dto.TokenDTO;
import com.logistics.auth.dto.AccountCredentialsDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

/**
 * Service for Authentication
 *
 * @author LucianoReul
 */
@Service
@AllArgsConstructor
public class AuthenticationService {

    /**
     * Token service
     */
    private final TokenAuthenticationService tokenService;

    /**
     * Account Service
     */
    private final AccountService accountService;

    /**
     * Attempt authentication
     *
     * @param credentials
     * @return Authentication with LoggedAccountVO as principal
     */
    public Authentication attemptAuthentication(AccountCredentialsDTO credentials) {
        Account loggedAccount = accountService.login(credentials.getUsername(), credentials.getPassword());
        if (loggedAccount == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"Credenciais invalida");
        }
        LoggedAccountVO loggedAccountDTO = toDTO(loggedAccount);
        tokenService.addAuthentication(loggedAccountDTO);
        return new UsernamePasswordAuthenticationToken(loggedAccountDTO, null, Collections.emptyList());
    }

    /**
     * Authenticates an user in the security context
     *
     * @param credentials
     * @return LoggedAccountVO for the authenticated user
     */
    public LoggedAccountVO authenticate(AccountCredentialsDTO credentials) {
        Authentication auth = attemptAuthentication(credentials);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedAccountVO) auth.getPrincipal();
    }

    /**
     * Attempt to refresh the current authentication
     *
     * @param token
     * @return Authentication with LoggedAccountVO as principal
     */
    public Authentication attemptRefresh(TokenDTO token) {
        Authentication auth = tokenService.getRefreshAuthentication(token.getRefreshToken());
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falha na Autenticação.");
        }
        Optional<Account> accountOptional = accountService.findByUsername(((LoggedAccountVO) auth.getPrincipal()).getUsername());
        if (accountOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falha na Autenticação.");
        }
        LoggedAccountVO loggedUser = toDTO(accountOptional.get());
        tokenService.addAuthentication(loggedUser);
        return new UsernamePasswordAuthenticationToken(loggedUser, null, Collections.emptyList());
    }

    /**
     * Refresh current authentication in the security context
     *
     * @param token
     * @return LoggedAccountVO updated
     */
    public LoggedAccountVO refresh(TokenDTO token) {
        Authentication auth = attemptRefresh(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedAccountVO) auth.getPrincipal();
    }

    /**
     * Converts Account to LoggedAccountVO
     *
     * @param account
     * @return LoggedAccountVO representation
     */
    private LoggedAccountVO toDTO(Account account) {
        LoggedAccountVO dto = new LoggedAccountVO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setRole(account.getRole());
        return dto;
    }

}
