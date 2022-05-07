package com.ms.logistics.auth.security;

import com.ms.logistics.auth.config.AppContext;
import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.model.User;
import com.ms.logistics.auth.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JWTAuthenticationManager implements AuthenticationManager {

    private UserService userService;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        User loggedUser = this.getUserService().login(auth.getName(), (String) auth.getCredentials());

        if (loggedUser != null) {
            return new UsernamePasswordAuthenticationToken(toUserDTO(loggedUser), auth.getCredentials());
        }

        throw new BadCredentialsException("Invalid username and/or password.");
    }

    protected UserService getUserService() {

        if (this.userService == null) {
            this.userService = AppContext.getBean(UserService.class);
        }

        return this.userService;
    }

    private LoggedUserDTO toUserDTO(User user) {

        LoggedUserDTO userDTO = new LoggedUserDTO();

        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());


        return userDTO;
    }
}
