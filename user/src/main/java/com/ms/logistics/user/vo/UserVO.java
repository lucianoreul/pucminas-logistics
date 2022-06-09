package com.ms.logistics.user.vo;

import com.ms.logistics.user.domain.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class UserVO {

    private Integer id;

    private Integer accountId;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String birthday;

    private String area;

    private Long Sector;

    private String material;

    private String imageB64;

    public UserVO(User user) {
        BeanUtils.copyProperties(user, this);
    }

}
