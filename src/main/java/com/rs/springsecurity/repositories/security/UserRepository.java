package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by rs 3/23/2022.
 */
public interface UserRepository extends JpaRepository<Users,Integer> {

    Optional<Users> findByUsername(String username);
}
