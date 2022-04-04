package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.LoginSuccess;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by rs 4/3/2022.
 */

public interface LoginSuccessRepository extends JpaRepository<LoginSuccess,Integer> {
}
