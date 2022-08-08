package com.ms.logistics.user.domain;

import com.ms.logistics.user.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import javax.persistence.*;

/**
 * Model for table: User.
 *
 * @author LucianoReul
 */
@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * ACCOUNT ID
     */
    @Column(name = "accountId", nullable = false)
    private Integer accountId;

    /**
     * NAME
     */
    @Size(max = 40, min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * LAST NAME
     */
    @Size(max = 40, min = 2)
    @Column(name = "lastName", nullable = false)
    private String lastName;

    /**
     * E-MAIL
     */
    @Column(name = "email", nullable = false)
    private String email;

    /**
     * PHONE NUMBER
     */
    @Column(name = "phone_number")
    private String phone;

    /**
     * BIRTHDAY DATE
     */
    @Column(name = "birthday")
    private String birthday;

    /**
     * AREA
     */
    @Column(name = "area")
    private String area;

    /**
     * SECTOR
     */
    @Column(name = "sector")
    private Long sector;

    /**
     * MATERIAL
     */
    @Column(name = "material")
    private String material;

    /**
     * IMAGEB64
     */
    @Column(name = "imageB64")
    private String imageB64;

    public User(UserDTO dto, Integer accountId) {
        this.name = dto.getName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.birthday = dto.getBirthday();
        this.area = dto.getArea();
        this.sector = dto.getSector();
        this.material = dto.getMaterial();
        this.imageB64 = dto.getImageB64();
        this.accountId = accountId;
    }
}
