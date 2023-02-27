package com.logistics.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO for entity: Account.
 *
 * @author LucianoReul
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    /**
     * USERNAME
     */
    @NotBlank(message = "{user.username.nonBlank}")
    @Size(min = 2, max = 10, message = "{error.user.userName.size}")
    private String username;

    /**
     * PASSWORD
     */
    @NotBlank(message = "Password must not contain only blank space.")
    @Size(min = 6, max = 20, message = "{error.user.password.size}")
    private String password;
}
