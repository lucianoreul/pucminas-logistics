package com.ms.logistics.auth.service;

import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.dto.TokenDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.User;
import com.ms.logistics.auth.security.AccountCredentials;
import com.ms.logistics.auth.security.TokenAuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    private final UserService userService;

    private final TokenAuthenticationService tokenService;

    public AuthenticationService(UserService userService, TokenAuthenticationService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    public Authentication attemptAuthentication(AccountCredentials credentials) throws BusinessException {
        User loggedUser = userService.login(credentials.getUsername(), credentials.getPassword());
        if (loggedUser == null) {
            throw new BusinessException("authentication.failure");
        }
        LoggedUserDTO loggedUserDTO = toDTO(loggedUser);
        tokenService.addAuthentication(loggedUserDTO);
        return new UsernamePasswordAuthenticationToken(loggedUserDTO, null, Collections.emptyList());
    }

    public LoggedUserDTO authenticate(AccountCredentials credentials) throws BusinessException {
        Authentication auth = attemptAuthentication(credentials);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedUserDTO) auth.getPrincipal();
    }

    public Authentication attemptRefresh(TokenDTO token) throws BusinessException {
        Authentication auth = tokenService.getRefreshAuthentication(token.getRefreshToken());
        if (auth == null) {
            throw new BusinessException("authentication.failure");
        }
        User userORM = userService.findByUsername(((LoggedUserDTO) auth.getPrincipal()).getUsername());
        LoggedUserDTO loggedUser = toDTO(userORM);
        tokenService.addAuthentication(loggedUser);
        return new UsernamePasswordAuthenticationToken(loggedUser, null, Collections.emptyList());
    }

    public LoggedUserDTO refresh(TokenDTO token) throws BusinessException {
        Authentication auth = attemptRefresh(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return (LoggedUserDTO) auth.getPrincipal();
    }

    private LoggedUserDTO toDTO(User user) {
        LoggedUserDTO dto = new LoggedUserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole());

        return dto;
    }
}
