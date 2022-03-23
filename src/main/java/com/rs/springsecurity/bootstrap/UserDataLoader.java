package com.rs.springsecurity.bootstrap;

import com.rs.springsecurity.domain.security.Authority;
import com.rs.springsecurity.domain.security.Users;
import com.rs.springsecurity.repositories.security.AuthorityRepository;
import com.rs.springsecurity.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * created by rs 3/23/2022.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {
        Authority admin = authorityRepository.save(Authority.builder().role("ADMIN").build());
        Authority userRole = authorityRepository.save(Authority.builder().role("USER").build());
        Authority customer = authorityRepository.save(Authority.builder().role("CUSTOMER").build());

        userRepository.save(Users.builder()
                .username("spring")
                .password(passwordEncoder.encode("spring"))
                .authority(admin)
                .build());

        userRepository.save(Users.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .authority(userRole)
                .build());

        userRepository.save(Users.builder()
                .username("scott")
                .password(passwordEncoder.encode("tiger"))
                .authority(customer)
                .build());

        log.debug("Users Loaded: " + userRepository.count());
    }

    @Override
    public void run(String... args) throws Exception {
        if (authorityRepository.count() == 0) {
            loadSecurityData();
        }
    }
}
