package com.ms.logistics.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "{error.user.name.nonBlank}")
    @Size(min = 2, max = 40, message = "{error.user.name.size}")
    private String name;

    @NotBlank(message = "{user.username.nonBlank}")
    @Size(min = 2, max = 10, message = "{error.user.userName.size}")
    private String username;

    @NotBlank(message = "Password must not contain only blank space.")
    @Size(min = 6, max = 20, message = "{error.user.password.size}")
    private String password;

}
