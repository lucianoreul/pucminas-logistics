package com.ms.logistics.auth.dto;

import lombok.Data;

@Data
public class LoggedUserDTO {

    private Integer id;

    private String name;


    private String username;

    private String role;


    private String accessToken;


    private String refreshToken;


    public LoggedUserDTO() {
    }


    public LoggedUserDTO(Integer id, String username) {
        this.id = id;
        this.username = username;
    }
}
