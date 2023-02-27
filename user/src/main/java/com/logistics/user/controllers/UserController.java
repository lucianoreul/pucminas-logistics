package com.logistics.user.controllers;

import com.logistics.user.vo.UserVO;
import com.logistics.user.dto.UserDTO;
import com.logistics.user.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Rest Controller for User microsservice
 *
 * @author LucianoReul
 */
@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    /**
     * User service.
     */
    private final UserService userService;

    /**
     * Get user data by account id
     *
     * @param accountId
     * @return UserVO
     */
    @GetMapping
    public ResponseEntity<UserVO> getByAccountId(
            @Valid @NotNull @RequestHeader("X-auth-user-id") Integer accountId
    ) {
        return ResponseEntity.ok(this.userService.getUserByAccountId(accountId));
    }

    /**
     * Create user endpoint
     *
     * @param userDTO
     * @param accountId
     * @return HttpStatus ok if success
     */
    @PostMapping(value = "/create")
    public ResponseEntity<Void> register(
            @Valid @NotNull @RequestBody UserDTO userDTO,
            @Valid @NotNull @RequestHeader("X-auth-user-id") Integer accountId
    ) {
        this.userService.createUser(userDTO, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
