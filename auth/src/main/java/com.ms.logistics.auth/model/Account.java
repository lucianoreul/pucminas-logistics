package com.ms.logistics.auth.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

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

    @Column(name = "role")
    String role;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = Role.ROLE_USER.getAuthority();
    }
}
