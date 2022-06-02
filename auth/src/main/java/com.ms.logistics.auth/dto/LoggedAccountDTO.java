package com.ms.logistics.auth.dto;

import lombok.Data;

@Data
public class LoggedAccountDTO {

    private Integer id;

    private String username;

    private String role;

    private String accessToken;

    private String refreshToken;

    public LoggedAccountDTO() {
    }

    public LoggedAccountDTO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}
