package com.rs.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class},
        scanBasePackages ={"com.rs.springsecurity"})
//@ComponentScan({"com.rs.springsecurity.web.mappers","com.rs.springsecurity.bootstrap"})
@ComponentScan(basePackages={"com.rs.springsecurity.web.mappers","com.rs.springsecurity.bootstrap"})
public class SpringSecurityCoreBreweryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityCoreBreweryAppApplication.class, args);
    }

}
