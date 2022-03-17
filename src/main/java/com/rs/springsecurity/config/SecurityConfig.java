package com.rs.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
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
                .and().formLogin().and()
//                .formLogin().and().csrf().ignoringAntMatchers("/h2-console/**")
//                         .and().headers().frameOptions().sameOrigin();
                    .httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

/*

    //user details config
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("spring")
                .password("spring")
                .roles("ADMIN")
                .build();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
*/

    @Bean
    PasswordEncoder passwordEncoder(){
        return new LdapShaPasswordEncoder();
    }


    //in memory Config Fluent

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password("{SSHA}mJA9E0nEQrBFIEyutDgUdr94eFzB1/Bm0OYLag==")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{SSHA}LT+zqH2PO+ALzl36ULU96DR/uJUSkrSi+7646Q==")
                .roles("USER");

        auth.inMemoryAuthentication().withUser("scott").password("tiger").roles("CUSTOMER");

    }
}
