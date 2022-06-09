package com.ms.logistics.auth.repository;

import com.ms.logistics.auth.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value = "select * from account where upper(username) = upper(?1) and password = ?2", nativeQuery = true)
    Account login(String username, String encryptedPassword);

    boolean existsByUsername(String username);

//    Account findByUsername(String username);

    Optional<Account> findByUsername(String username);

    @Query(value = "SELECT u FROM user_account u WHERE u.id = ?1 ", nativeQuery = true)
    Account getById(Integer id);
}
