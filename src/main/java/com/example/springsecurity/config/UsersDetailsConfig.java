package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UsersDetailsConfig {

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("first")
                .password(encoder.encode("first"))
                .authorities("first_auth")
                .roles("ADMIN")
                .build());
        manager.createUser(User.withUsername("second")
                .password(encoder.encode("second"))
                .authorities("second_auth")
                .roles("USER")
                .build());
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
