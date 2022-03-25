package com.rs.springsecurity.bootstrap;

import com.rs.springsecurity.domain.security.Authority;
import com.rs.springsecurity.domain.security.Role;
import com.rs.springsecurity.domain.security.Users;
import com.rs.springsecurity.repositories.security.AuthorityRepository;
import com.rs.springsecurity.repositories.security.RoleRepository;
import com.rs.springsecurity.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

/**
 * created by rs 3/23/2022.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class UserDataLoader implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private void loadSecurityData() {
        //beer auths
        Authority createBeer = authorityRepository.save(Authority.builder().permission("beer.create").build());
        Authority updateBeer = authorityRepository.save(Authority.builder().permission("beer.update").build());
        Authority readBeer = authorityRepository.save(Authority.builder().permission("beer.read").build());
        Authority deleteBeer = authorityRepository.save(Authority.builder().permission("beer.delete").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role customerRole = roleRepository.save(Role.builder().name("CUSTOMER").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(Set.of(createBeer, updateBeer, readBeer, deleteBeer));

        customerRole.setAuthorities(Set.of(readBeer));

        userRole.setAuthorities(Set.of(readBeer));

        roleRepository.saveAll(Arrays.asList(adminRole, customerRole, userRole));

        userRepository.save(Users.builder()
                .username("spring")
                .password(passwordEncoder.encode("spring"))
                .role(adminRole)
                .build());

        userRepository.save(Users.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .role(userRole)
                .build());

        userRepository.save(Users.builder()
                .username("scott")
                .password(passwordEncoder.encode("tiger"))
                .role(customerRole)
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
