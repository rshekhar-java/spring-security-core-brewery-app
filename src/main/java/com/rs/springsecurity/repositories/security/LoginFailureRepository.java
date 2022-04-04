package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.LoginFailure;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by rs 4/3/2022.
 */
public interface LoginFailureRepository extends JpaRepository<LoginFailure,Integer> {
}
