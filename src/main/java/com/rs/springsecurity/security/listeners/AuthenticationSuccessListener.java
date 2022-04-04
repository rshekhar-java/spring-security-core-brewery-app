package com.rs.springsecurity.security.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * created by rs 4/3/2022.
 */
@Slf4j
@Component
public class AuthenticationSuccessListener {

    @EventListener
    public void listen(AuthenticationSuccessEvent event){

        log.debug("User Logged In Okay");

    }
}
