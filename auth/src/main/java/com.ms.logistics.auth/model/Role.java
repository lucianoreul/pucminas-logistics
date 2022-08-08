package com.ms.logistics.auth.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum to define roles of the system
 *
 * @author LucianoReul
 */
public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER;

    public String getAuthority() {
        return name();
    }
}
