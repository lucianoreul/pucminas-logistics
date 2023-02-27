package com.logistics.user.repository;

import com.logistics.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * CRUD Repository for entity: User.
 *
 * @author LucianoReul
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by id
     *
     * @param id
     * @return Optional<User>
     */
    Optional<User> findById(Integer id);

    /**
     * Find User by account id
     *
     * @param accountId
     * @return Optional<User>
     */
    Optional<User> findByAccountId(Integer accountId);

    /**
     * Get user by id
     *
     * @param id
     * @return User
     */
    User getById(Integer id);
}
