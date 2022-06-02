package com.ms.logistics.user;

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

    @NotBlank(message = "{error.user.name.nonBlank}")
    @Size(min = 2, max = 40, message = "{error.user.name.size}")
    private String lastName;

}
