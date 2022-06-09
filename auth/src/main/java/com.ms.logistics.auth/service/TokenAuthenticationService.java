package com.ms.logistics.auth.service;

import com.ms.logistics.auth.dto.LoggedAccountDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.Account;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String AUTHORITIES_KEY = "auth";

    @Value("${app.token.secretkey}")
    private String SECRET_KEY;

    @Value("${app.token.expiration}")
    private long EXPIRATION_TOKEN;

    @Value("${app.refreshtoken.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    private final AccountService accountService;

    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationService.class);

    public TokenAuthenticationService(AccountService accountService) {
        this.accountService = accountService;
    }

    public LoggedAccountDTO addAuthentication(LoggedAccountDTO dto) {
        String jwt = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getUsername())
                .claim(AUTHORITIES_KEY, dto.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        String refresh = Jwts.builder().setId(String.valueOf(dto.getId())).setSubject(dto.getUsername())
                .claim(AUTHORITIES_KEY, dto.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        dto.setAccessToken(String.format("%s %s", TOKEN_PREFIX, jwt));
        dto.setRefreshToken(String.format("%s %s", TOKEN_PREFIX, refresh));

        return dto;
    }

    public Authentication getRefreshAuthentication(String refreshToken) {
        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody();

            if (claims != null) {
                LoggedAccountDTO userDTO = new LoggedAccountDTO(Integer.valueOf(claims.getId()), claims.getSubject());
                return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
            }
        }
        return null;
    }

    public LoggedAccountDTO isTokenInvalid(String token) throws BusinessException {
        String username;
        try {
            username = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (JwtException e) {
            throw new BusinessException("Token is invalid", HttpStatus.UNAUTHORIZED);
        }

        Optional<Account> accountOptional = accountService.findByUsername(username);

        if (accountOptional.isEmpty()) {
            throw new BusinessException("User not found", HttpStatus.NOT_FOUND);
        }

        Account account = accountOptional.get();
        return toDTO(account, token);
    }

    private LoggedAccountDTO toDTO(Account account, String token) {
        LoggedAccountDTO dto = new LoggedAccountDTO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setRole(account.getRole());
        dto.setAccessToken(token);
        return dto;
    }
}
