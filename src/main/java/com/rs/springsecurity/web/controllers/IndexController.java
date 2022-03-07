package com.rs.springsecurity.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by rs 3/7/2022.
 */
@Controller
public class IndexController {
    @GetMapping({"", "/"})
    public String index(){
        return "index";
    }
}
