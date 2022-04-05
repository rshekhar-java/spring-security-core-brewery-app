package com.rs.springsecurity.web.controllers;

import com.rs.springsecurity.domain.security.Users;
import com.rs.springsecurity.repositories.security.UserRepository;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * created by rs 4/5/2022.
 */
@Slf4j
@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final GoogleAuthenticator googleAuthenticator;


    @GetMapping("/register2fa")
    public String register2Fa(Model model){

        Users user = getUser();

        String url = GoogleAuthenticatorQRGenerator.getOtpAuthURL("Ravi Shekhar", user.getUsername(),
                googleAuthenticator.createCredentials(user.getUsername()));

        log.debug("Google QR URL: " + url);

        model.addAttribute("googleurl", url);
        return "user/register2fa";
    }

    private Users getUser() {
        return (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @PostMapping
    public String confirm2Fa(@RequestParam Integer verifyCode){

        //todo - impl
        return "index";
    }
}
