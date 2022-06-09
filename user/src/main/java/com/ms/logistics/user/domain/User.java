package com.ms.logistics.user.domain;

import com.ms.logistics.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "accountId")
    private Integer accountId;

    @Size(max = 40, min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 40, min = 2)
    @Column(name = "lastName", nullable = false)
    private String lastName;

    private String email;

    private String phone;

    private String birthday;

    private String area;

    private Long Sector;

    private String material;

    private String imageB64;

    public User(UserDTO dto, Integer accountId) {
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.birthday = dto.getBirthday();
        this.area = dto.getArea();
        this.Sector = dto.getSector();
        this.material = dto.getMaterial();
        this.imageB64 = dto.getImageB64();
        this.accountId = accountId;
    }
}
