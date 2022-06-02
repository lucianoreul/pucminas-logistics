package com.ms.logistics.user;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<Void> register(@Valid @NotNull @RequestBody UserDTO userDTO) throws BusinessException {
        this.userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping()
//    public ResponseEntity<UserDTO> getUserByLoginAndPassword() throws BusinessException {
//        this.userService.login()
//    }
}
