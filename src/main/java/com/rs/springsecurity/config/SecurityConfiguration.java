package com.rs.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * created by rs 3/7/2022.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();

//        httpSecurity
//                .authorizeRequests(authorize -> authorize.mvcMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated());

 /*       httpSecurity.authorizeRequests()
                .antMatchers("/","/h2-console/**").permitAll()
                .and()
                .authorizeRequests();
*/

//        httpSecurity.csrf().disable().authorizeRequests()
//                .antMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated();

//        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");

/*
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable();
*/

//
//        httpSecurity.headers().frameOptions().sameOrigin();


    }
}
