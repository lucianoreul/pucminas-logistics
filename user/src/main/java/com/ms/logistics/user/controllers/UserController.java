package com.ms.logistics.user.controllers;

import com.ms.logistics.user.dto.UserDTO;
import com.ms.logistics.user.services.UserService;
import com.ms.logistics.user.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserVO> getByAccountId(
            @Valid @NotNull @RequestHeader("X-auth-user-id") Integer accountId
    ) {
        return ResponseEntity.ok(this.userService.getUserByAccountId(accountId));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> register(
            @Valid @NotNull @RequestBody UserDTO userDTO,
            @Valid @NotNull @RequestHeader("X-auth-user-id") Integer accountId
    ) {
        this.userService.createUser(userDTO, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
