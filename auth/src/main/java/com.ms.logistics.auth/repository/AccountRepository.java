package com.ms.logistics.auth.repository;

import com.ms.logistics.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "select * from user_account where upper(username) = upper(?1) and password = ?2", nativeQuery = true)
    Account login(String username, String encryptedPassword);

    boolean existsByUsername(String username);

    boolean existsByUsernameAndIdNot(String username, Integer id);

    Account findByUsername(String username);

    @Query(value = "select 1 from user_account where id = ?1 and password = ?2", nativeQuery = true)
    Integer isPasswordCorrect(Integer id, String password);

    @Modifying
    @Query(value = "update user_account set password = ?2 where id = ?1", nativeQuery = true)
    void updatePassword(Integer id, String newPassword);

    @Query(value = "SELECT u FROM user_account u WHERE u.id = ?1 ", nativeQuery = true)
    Account getById(Integer id);
}
