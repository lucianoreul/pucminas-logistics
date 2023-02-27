package com.logistics.auth.repository;

import com.logistics.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * CRUD Repository for entity: Account.
 *
 * @author LucianoReul
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    /**
     * Login the user.
     *
     * @param username
     * @param encryptedPassword
     * @return Account's data.
     */
    @Query(value = "select * from account where upper(username) = upper(?1) and password = ?2", nativeQuery = true)
    Account login(String username, String encryptedPassword);

    /**
     * Checks if exists an user with the same username.
     *
     * @param username
     * @return true or false.
     */
    boolean existsByUsername(String username);

    /**
     * Finds the user by username.
     *
     * @param username
     * @return Account data
     */
    Optional<Account> findByUsername(String username);

    /**
     * Finds the user by id.
     *
     * @param id
     * @return Account data
     */
    @Query(value = "SELECT u FROM user_account u WHERE u.id = ?1 ", nativeQuery = true)
    Account getById(Integer id);
}
