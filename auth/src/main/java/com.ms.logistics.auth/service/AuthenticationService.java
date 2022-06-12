package com.ms.logistics.auth.service;

import com.ms.logistics.auth.dto.LoggedAccountDTO;
import com.ms.logistics.auth.dto.TokenDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.Account;
import com.ms.logistics.auth.model.AccountCredentials;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final TokenAuthenticationService tokenService;

    private final AccountService accountService;

    public Authentication attemptAuthentication(AccountCredentials credentials) {
        Account loggedAccount = accountService.login(credentials.getUsername(), credentials.getPassword());
        if (loggedAccount == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED ,"Credenciais invalida");
        }
        LoggedAccountDTO loggedAccountDTO = toDTO(loggedAccount);
        tokenService.addAuthentication(loggedAccountDTO);
        return new UsernamePasswordAuthenticationToken(loggedAccountDTO, null, Collections.emptyList());
    }

    public LoggedAccountDTO authenticate(AccountCredentials credentials) {
        Authentication auth = attemptAuthentication(credentials);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedAccountDTO) auth.getPrincipal();
    }

    public Authentication attemptRefresh(TokenDTO token) {
        Authentication auth = tokenService.getRefreshAuthentication(token.getRefreshToken());
        if (auth == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falha na Autenticação.");
        }
        Optional<Account> accountOptional = accountService.findByUsername(((LoggedAccountDTO) auth.getPrincipal()).getUsername());
        if (accountOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Falha na Autenticação.");
        }
        LoggedAccountDTO loggedUser = toDTO(accountOptional.get());
        tokenService.addAuthentication(loggedUser);
        return new UsernamePasswordAuthenticationToken(loggedUser, null, Collections.emptyList());
    }

    public LoggedAccountDTO refresh(TokenDTO token) {
        Authentication auth = attemptRefresh(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedAccountDTO) auth.getPrincipal();
    }

    private LoggedAccountDTO toDTO(Account account) {
        LoggedAccountDTO dto = new LoggedAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setRole(account.getRole());
        return dto;
    }

}
