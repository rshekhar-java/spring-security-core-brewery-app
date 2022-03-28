package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * created by rs 3/25/2022.
 */
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String customer);
}
