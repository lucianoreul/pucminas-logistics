package com.logistics.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AccountVO {

    private Integer id;
    private String username;
    private String role;
    private String accessToken;
}
