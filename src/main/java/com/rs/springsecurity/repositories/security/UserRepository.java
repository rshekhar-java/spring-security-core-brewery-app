package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by rs 3/23/2022.
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);
}
