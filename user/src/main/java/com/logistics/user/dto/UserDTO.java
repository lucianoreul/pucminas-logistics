package com.logistics.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * DTO for entity: User.
 *
 * @author LucianoReul
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public static final String EMAIL_REGEX = "^$|^(?:[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]){1,64}@(?:[a-zA-Z0-9à-á-â-ã-é-ê-í-ó-ô-õ-ú-ü-ç-]){2,64}\\.[a-zA-Z0-9]{1,10}(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,8}[a-zA-Z0-9])?)*$";

    /**
     * NAME
     */
    @NotBlank(message = "The Name is required")
    @Size(min = 2, max = 40, message = "The NAME should be between 2 and 40 characters.")
    private String name;

    /**
     * LAST NAME
     */
    @NotBlank(message = "The Last Name is required")
    @Size(min = 2, max = 40, message = "The Last Name should be between 2 and 40 characters.")
    private String lastName;

    /**
     * E-MAIL
     */
    @NotBlank(message = "The EMAIL is required.")
    @Pattern(regexp = EMAIL_REGEX, message = "he EMAIL is in a invalid pattern.")
    @Size(min = 6, max = 256, message = "The EMAIL should be between 6 and 256 characters.")
    private String email;

    /**
     * PHONE NUMBER
     */
    @Size(min = 10, max = 20, message = "The PHONE should be between 10 and 20 characters.")
    private String phone;

    /**
     * BIRTHDAY DATE
     */
    private String birthday;

    /**
     * AREA
     */
    private String area;

    /**
     * SECTOR
     */
    private Long Sector;

    /**
     * MATERIAL
     */
    private String material;

    /**
     * IMAGEB64
     */
    private String imageB64;
}
