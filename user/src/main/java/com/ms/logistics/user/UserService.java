package com.ms.logistics.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    protected UserRepository getRepository() {
        return repository;
    }

    public void insert(User user) {
        this.getRepository().save(user);
    }

    public Optional<User> findById(Integer id) {
        return getRepository().findById(id);
    }

    public void createUser(UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getLastName());
        this.insert(user);
    }

    public void updateUser(User user) {
        this.repository.saveAndFlush(user);
    }

}
