package com.ms.logistics.auth.security;

import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {

    private static final String TOKEN_PREFIX = "Bearer";

    public static final String HEADER = "Authorization";

    protected static final String HEADER_REFRESH = "Refresh-Token";

    private static final String AUTHORITIES_KEY = "auth";

    private final UserService userService;

    @Value("${app.token.secretkey}")
    private String SECRET_KEY;

    @Value("${app.token.expiration}")
    private long EXPIRATION_TOKEN;

    @Value("${app.refreshtoken.expiration}")
    private long EXPIRATION_REFRESH_TOKEN;

    public TokenAuthenticationService(UserService userService) {
        this.userService = userService;
    }

    public LoggedUserDTO addAuthentication(LoggedUserDTO dto) {

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

    public Authentication getAuthentication(HttpServletRequest request) {

        String token = request.getHeader(HEADER);

        if (token != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, "").trim()).getBody();

            if (claims != null) {
                LoggedUserDTO loggedUserDTO = new LoggedUserDTO(Integer.valueOf(claims.getId()), claims.getSubject());
                loggedUserDTO.setRole(this.userService.findById(loggedUserDTO.getId()).getRole());
                Collection<? extends GrantedAuthority> authorities =
                        Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(loggedUserDTO, token, authorities);
            }
            return null;
        }

        return null;
    }

    public String generateToken(String id, String username) {

        return Jwts.builder().setId(id).setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TOKEN))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public Authentication getRefreshAuthentication(String refreshToken) {
        if (refreshToken != null) {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(refreshToken.replace(TOKEN_PREFIX, "").trim()).getBody();

            if (claims != null) {
                LoggedUserDTO userDTO = new LoggedUserDTO(Integer.valueOf(claims.getId()), claims.getSubject());
                return new UsernamePasswordAuthenticationToken(userDTO, null, emptyList());
            }
        }
        return null;
    }
}
