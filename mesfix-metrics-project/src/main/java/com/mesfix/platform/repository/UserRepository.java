package com.mesfix.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mesfix.platform.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    void deleteUserByUsername(String username);
}