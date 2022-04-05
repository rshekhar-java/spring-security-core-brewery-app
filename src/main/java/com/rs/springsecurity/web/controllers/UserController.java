package com.rs.springsecurity.web.controllers;

import com.rs.springsecurity.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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


    @GetMapping("/register2fa")
    public String register2Fa(Model model){

        model.addAttribute("googleurl","todo");
        return "user/register2fa";
    }

    @PostMapping
    public String confirm2Fa(@RequestParam Integer verifyCode){

        //todo - impl
        return "index";
    }
}
