package com.rs.springsecurity.repositories.security;

import com.rs.springsecurity.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by rs 3/23/2022.
 */
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {
}
