package com.rs.springsecurity.security;

import com.rs.springsecurity.domain.security.Authority;
import com.rs.springsecurity.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.rs.springsecurity.domain.security.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * created by rs 3/23/2022.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Getting User info via JpaUserDetailsService");

        return userRepository.findByUsername(username).orElseThrow(() -> {
            return new UsernameNotFoundException("User name: " + username + " not found");
        });
    }

}
