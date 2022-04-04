package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.LoginFailure;
import com.rs.springsecurity.domain.security.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * created by rs 4/3/2022.
 */
public interface LoginFailureRepository extends JpaRepository<LoginFailure,Integer> {
    List<LoginFailure> findAllByUserAndCreatedDateIsAfter(Users user, Timestamp timestamp);
}
