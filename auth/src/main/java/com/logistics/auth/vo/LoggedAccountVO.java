package com.logistics.auth.vo;

import lombok.Data;

/**
 * VO for entity: Logged Account.
 *
 * @author LucianoReul
 */
@Data
public class LoggedAccountVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * USERNAME
     */
    private String username;

    /**
     * ROLE
     */
    private String role;

    /**
     * ACCESS TOKEN
     */
    private String accessToken;

    /**
     * REFRESH TOKEN
     */
    private String refreshToken;

    public LoggedAccountVO() {
    }

    public LoggedAccountVO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}
