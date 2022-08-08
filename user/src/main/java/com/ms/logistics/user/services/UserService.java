package com.ms.logistics.user.services;

import com.ms.logistics.user.domain.User;
import com.ms.logistics.user.dto.UserDTO;
import com.ms.logistics.user.repository.UserRepository;
import com.ms.logistics.user.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Crud Service for model: User.
 *
 * @author LucianoReul
 */
@Service
@AllArgsConstructor
public class UserService {

    /**
     * User repository
     */
    private final UserRepository userRepository;

    /**
     * find a user by id
     *
     * @param id
     * @return
     */
    public Optional<User> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    /**
     * find a user by account id
     *
     * @param id
     * @return
     */
    public UserVO getUserByAccountId(Integer id) {
        Optional<User> userOptional = this.userRepository.findByAccountId(id);
        if (userOptional.isPresent()) {
            return new UserVO(userOptional.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    /**
     * Create a user in the database
     *
     * @param userDTO
     * @param accountId
     */
    public void createUser(UserDTO userDTO, Integer accountId) {
        if (userRepository.findByAccountId(accountId).isPresent()) {
            User user = new User(userDTO, accountId);
            this.userRepository.save(user);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User Exists");
        }
    }

    /**
     * Update a user from the database
     *
     * @param user
     */
    public void updateUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

}
