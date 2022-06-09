package com.ms.logistics.user.repository;

import com.ms.logistics.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);

    Optional<User> findByAccountId(Integer accountId);

    User getById(Integer id);
}
