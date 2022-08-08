package com.ms.logistics.user.vo;

import com.ms.logistics.user.domain.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * VO for entity: User.
 *
 * @author LucianoReul
 */
@Data
public class UserVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * ACCOUNT ID
     */
    private Integer accountId;

    /**
     * NAME
     */
    private String name;

    /**
     * LAST NAME
     */
    private String lastName;

    /**
     * E-MAIL
     */
    private String email;

    /**
     * PHONE NUMBER
     */
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

    public UserVO(User user) {
        BeanUtils.copyProperties(user, this);
    }

}
