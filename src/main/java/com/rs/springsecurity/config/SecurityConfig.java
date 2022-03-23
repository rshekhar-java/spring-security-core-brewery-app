package com.rs.springsecurity.config;

import com.rs.springsecurity.security.BreweryPasswordEncoderFactories;
import com.rs.springsecurity.security.RestHeaderAuthFilter;
import com.rs.springsecurity.security.RestUrlAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * created by rs 3/7/2022.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    public RestHeaderAuthFilter restHeaderAuthFilter(AuthenticationManager authenticationManager){

        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    public RestUrlAuthFilter restUrlAuthFilter(AuthenticationManager authenticationManager){
        RestUrlAuthFilter filter = new RestUrlAuthFilter(new AntPathRequestMatcher("/api/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }


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

            http.addFilterBefore(restHeaderAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
                    .csrf().disable();


            http.addFilterBefore(restUrlAuthFilter(authenticationManager()),
                UsernamePasswordAuthenticationFilter.class);
        //url pattern matching
                 http.csrf().disable()
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

//                http.csrf().disable();
//        http.headers().frameOptions().sameOrigin();
                //h2 console config
                http.headers().frameOptions().sameOrigin();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return BreweryPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //in memory Config Fluent

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("spring")
                .password("{bcrypt}$2a$10$O79gq4gxRgbzwDLLACnfBOg.ZwAfKES2Hxn2HLnkadl5mJc8EADDS")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("{sha256}358cc8257d7e279c9595e5ef288ca2cfd4672c01320ba8f0dce404a904efa6434d673c93348a1577")
                .roles("USER");

        auth.inMemoryAuthentication().withUser("scott").password("{bcrypt10}$2a$10$5KuoJxpFGjOUXTigqQST8uJAKg1K8nUzCuzIL47W.HDbMObnRxUwK").roles("CUSTOMER");

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

}
