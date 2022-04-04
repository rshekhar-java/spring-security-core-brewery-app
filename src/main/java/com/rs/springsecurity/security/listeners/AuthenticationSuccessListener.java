package com.rs.springsecurity.security.listeners;

import com.rs.springsecurity.domain.security.LoginSuccess;
import com.rs.springsecurity.domain.security.Users;
import com.rs.springsecurity.repositories.security.LoginSuccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * created by rs 4/3/2022.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationSuccessListener {
    private final LoginSuccessRepository loginSuccessRepository;

    @EventListener
    public void listen(AuthenticationSuccessEvent event){

        log.debug("User Logged In Okay");

        if (event.getSource() instanceof UsernamePasswordAuthenticationToken) {
            LoginSuccess.LoginSuccessBuilder builder = LoginSuccess.builder();

            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) event.getSource();

            if(token.getPrincipal() instanceof Users){
                Users user = (Users) token.getPrincipal();
                builder.user(user);

                log.debug("User name logged in: " + user.getUsername() );
            }

            if(token.getDetails() instanceof WebAuthenticationDetails){
                WebAuthenticationDetails details = (WebAuthenticationDetails) token.getDetails();

                log.debug("Source IP: " + details.getRemoteAddress());
                builder.sourceIp(details.getRemoteAddress());
            }
            LoginSuccess loginSuccess = loginSuccessRepository.save(builder.build());

            log.debug("Login Success saved. Id: " + loginSuccess.getId());
        }


    }
}
