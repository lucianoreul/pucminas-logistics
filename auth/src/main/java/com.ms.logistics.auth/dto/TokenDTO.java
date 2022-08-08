package com.ms.logistics.auth.dto;

/**
 * DTO for entity: Token.
 *
 * @author LucianoReul
 */
public class TokenDTO {

    /**
     * REFRESH TOKEN
     */
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
