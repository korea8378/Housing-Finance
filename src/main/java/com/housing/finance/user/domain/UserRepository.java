package com.housing.finance.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserId(String userId);

    Optional<User> findByUserIdAndPassword(String userId, String password);

    Optional<User> findByUserId(String userId);
}
