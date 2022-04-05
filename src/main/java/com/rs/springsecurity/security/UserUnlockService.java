package com.rs.springsecurity.security;

import com.rs.springsecurity.domain.security.Users;
import com.rs.springsecurity.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * created by rs 4/5/2022.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserUnlockService {


    private final UserRepository userRepository;

    @Scheduled(fixedRate = 50000)//5000ms= 5s
    public void unlockAccounts(){
        log.debug("Running Unlock Accounts");

        List<Users> lockedUsers = userRepository
                .findAllByAccountNonLockedAndLastModifiedDateIsBefore(false,
                        Timestamp.valueOf(LocalDateTime.now().minusSeconds(30)));

        if(lockedUsers.size() > 0){
            log.debug("Locked Accounts Found, Unlocking");
            lockedUsers.forEach(user -> user.setAccountNonLocked(true));

            userRepository.saveAll(lockedUsers);
        }
    }
}
