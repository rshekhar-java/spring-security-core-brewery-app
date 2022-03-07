package com.rs.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan("com.rs.springsecurity.web.mappers")
public class SpringSecurityCoreBreweryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCoreBreweryAppApplication.class, args);
    }

}
