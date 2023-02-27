package com.logistics.auth.service;

import com.logistics.auth.model.Account;
import com.logistics.auth.vo.LoggedAccountVO;
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
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.Optional;

import static java.util.Collections.emptyList;

/**
 * Service for Token Authentication.
 *
 * @author LucianoReul
 */
@Service
public class TokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer";

    private static final String AUTHORITIES_KEY = "auth";

    /**
     * Secret key to create de token
     */
    @Value("${app.token.secretkey}")
    private String SECRET_KEY;

    /**
     * Token Expiration.
     */
    @Value("${app.token.expiration}")
    private long EXPIRATION_TOKEN;

    /**
     * Refresh Token Expiration.
     */
    @Value("${app.refreshtoken.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    /**
     * Account service
     */
    private final AccountService accountService;

    private final Logger log = LoggerFactory.getLogger(TokenAuthenticationService.class);

    /**
     * Constructor
     *
     * @param accountService
     */
    public TokenAuthenticationService(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Adds the authentication in the response.
     *
     * @param vo
     * @return LoggedAccountVO
     */
    public LoggedAccountVO addAuthentication(LoggedAccountVO vo) {
        String jwt = Jwts.builder().setId(String.valueOf(vo.getId())).setSubject(vo.getUsername())
                .claim(AUTHORITIES_KEY, vo.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        String refresh = Jwts.builder().setId(String.valueOf(vo.getId())).setSubject(vo.getUsername())
                .claim(AUTHORITIES_KEY, vo.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_REFRESH_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

        vo.setAccessToken(String.format("%s %s", TOKEN_PREFIX, jwt));
        vo.setRefreshToken(String.format("%s %s", TOKEN_PREFIX, refresh));

        return vo;
    }

    /**
     * Gets the refresh authentication.
     *
     * @param refreshToken
     * @return Refresh Authentication.
     */
    public Authentication getRefreshAuthentication(String refreshToken) {
        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody();

            if (claims != null) {
                LoggedAccountVO userDTO = new LoggedAccountVO(Integer.valueOf(claims.getId()), claims.getSubject());
                return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
            }
        }
        return null;
    }

    /**
     * Check if the token is valid
     *
     * @param token
     * @return LoggedAccountVO
     */
    public LoggedAccountVO isTokenInvalid(String token) {
        String username;
        try {
            username = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        } catch (JwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "Token Invalid");
        }

        Optional<Account> accountOptional = accountService.findByUsername(username);

        if (!accountOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        Account account = accountOptional.get();
        return toDTO(account, token);
    }

    /**
     * Converts Account to LoggedAccountVO with tokens
     *
     * @param account account data
     * @param token token string
     * @return LoggedAccountVO
     */
    private LoggedAccountVO toDTO(Account account, String token) {
        LoggedAccountVO dto = new LoggedAccountVO();
        dto.setId(account.getId());
        dto.setUsername(account.getUsername());
        dto.setRole(account.getRole());
        dto.setAccessToken(token);
        return dto;
    }
}
