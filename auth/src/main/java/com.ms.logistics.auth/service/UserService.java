package com.ms.logistics.auth.service;

import com.ms.logistics.auth.dto.LoggedUserDTO;
import com.ms.logistics.auth.dto.UserDTO;
import com.ms.logistics.auth.exception.BusinessException;
import com.ms.logistics.auth.model.User;
import com.ms.logistics.auth.repository.UserRepository;
import com.ms.logistics.auth.security.SecurityService;
import com.ms.logistics.auth.util.CryptoUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository repository;
    private SecurityService securityService;

    public UserService(UserRepository repository, SecurityService securityService) {
        this.repository = repository;
        this.securityService = securityService;
    }

    protected UserRepository getRepository() {
        return repository;
    }

    public User insert(User user) throws BusinessException {
        user.setPassword(CryptoUtil.hash(user.getPassword()));
        this.validateInsert(user);
        return this.getRepository().save(user);
    }

    protected void validateInsert(User model) throws BusinessException {
        if (getRepository().existsByUsername(model.getUsername())) {
            throw new BusinessException("Username already exists.");
        }
    }

    protected void validateUpdate(User model) throws BusinessException {
        if (getRepository().existsByUsernameAndIdNot(model.getUsername(), model.getId())) {
            throw new BusinessException("user.username.exists");
        }
    }

    public User login(String username, String password) {
        User user = getRepository().login(username, CryptoUtil.hash(password));
        return user;
    }

    public User findByUsername(String username) {
        return getRepository().findByUsername(username);
    }

    public User findById(Integer id) {
        return getRepository().findById(id).get();
    }

    @Transactional
    public void updatePassword(Integer id, String currentPassword, String newPassword) throws BusinessException {
        if (getRepository().isPasswordCorrect(id, CryptoUtil.hash(currentPassword)) == null) {
            throw new BusinessException("user.password.wrong");
        }

        getRepository().updatePassword(id, CryptoUtil.hash(newPassword));
    }

    public void createUser(UserDTO userDTO) throws BusinessException {
        User user = new User(userDTO.getUsername(), userDTO.getName(), userDTO.getPassword());
        this.insert(user);
    }

    public User getCurrentLoggedUser() {
        LoggedUserDTO loggedUserDTO = securityService.getCurrentUser();
        return this.repository.findById(loggedUserDTO.getId()).get();
    }

    public void updateUser(User user) {
        this.repository.saveAndFlush(user);
    }

}
