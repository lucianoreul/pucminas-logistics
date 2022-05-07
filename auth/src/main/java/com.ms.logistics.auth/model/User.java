package com.ms.logistics.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import javax.persistence.*;

@Entity(name = "user_account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Size(max = 10, min = 2)
    @Column(name = "username", nullable = false)
    private String username;

    @Size(min = 6)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 40, min = 2)
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "role")
    String role;

    public User(String username, String name, String password) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.role = Role.ROLE_USER.getAuthority();
    }
}
