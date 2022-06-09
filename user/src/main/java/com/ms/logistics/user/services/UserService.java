package com.ms.logistics.user.services;

import com.ms.logistics.user.domain.User;
import com.ms.logistics.user.dto.UserDTO;
import com.ms.logistics.user.exception.BusinessException;
import com.ms.logistics.user.repository.UserRepository;
import com.ms.logistics.user.vo.UserVO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findById(Integer id) {
        return this.userRepository.findById(id);
    }

    public UserVO getUserByAccountId(Integer id) throws BusinessException {
        Optional<User> userOptional = this.userRepository.findByAccountId(id);
        if (userOptional.isPresent()) {
            return new UserVO(userOptional.get());
        } else {
            throw new BusinessException("User not found", HttpStatus.NOT_FOUND);
        }
    }

    public void createUser(UserDTO userDTO, Integer accountId) throws BusinessException {
        if (userRepository.findByAccountId(accountId).isEmpty()) {
            User user = new User(userDTO, accountId);
            this.userRepository.save(user);
        } else {
            throw new BusinessException("User Exists", HttpStatus.CONFLICT);
        }
    }

    public void updateUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

}
