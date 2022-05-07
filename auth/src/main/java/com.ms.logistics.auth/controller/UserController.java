package com.ms.logistics.auth.controller;

import com.ms.logistics.auth.dto.UserDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("users")
public class UserController {

    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> register(@Valid @NotNull @RequestBody UserDTO userDTO) throws BusinessException {
        this.service.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
