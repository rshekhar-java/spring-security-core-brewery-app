package com.rs.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * created by rs 3/7/2022.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
  /*      http
                .csrf().disable().cors().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();*/

        //url pattern matching
                 http
                         .csrf().disable()
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/h2-console/**").permitAll()
                            .antMatchers("/","/webjars/**", "/login", "/resources/**").permitAll()
                            .antMatchers("/beers/find", "/beers*").permitAll()
                            .antMatchers(HttpMethod.GET, "/api/v1/beer/**").permitAll()
                            .mvcMatchers(HttpMethod.GET, "/api/v1/beerUpc/{upc}").permitAll();
                } )
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                         .formLogin()
                         .and()
//                .formLogin().and().csrf().ignoringAntMatchers("/h2-console/**")
//                         .and().headers().frameOptions().sameOrigin();
                    .httpBasic();

//        http.csrf().ignoringAntMatchers("/h2-console/**");
//        http.headers().frameOptions().sameOrigin();
    }


/*    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.authorizeRequests().antMatchers("/h2-console/**").permitAll();

//        httpSecurity
//                .authorizeRequests(authorize -> authorize.mvcMatchers("/h2-console/**").permitAll()
//                        .anyRequest().authenticated());

 *//*       httpSecurity.authorizeRequests()
                .antMatchers("/","/h2-console/**").permitAll()
                .and()
                .authorizeRequests();
*//*

//        httpSecurity.csrf().disable().authorizeRequests()
//                .antMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated();

//        httpSecurity.csrf().ignoringAntMatchers("/h2-console/**");

*//*
        httpSecurity.authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll()
                .and()
                .headers().frameOptions().disable()
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and()
                .cors().disable();
*//*

//
//        httpSecurity.headers().frameOptions().sameOrigin();


    }*/
}
