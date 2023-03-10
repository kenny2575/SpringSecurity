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
        manager.createUser(
                User.withUsername("user1")
                        .password(encoder.encode("user1"))
                        .roles("READ")
                        .build()
        );
        manager.createUser(
                User.withUsername("user2")
                        .password(encoder.encode("user2"))
                        .roles("READ","WRITE")
                        .build()
        );
        manager.createUser(
                User.withUsername("user3")
                        .password(encoder.encode("user3"))
                        .roles("DELETE")
                        .build()
        );
        return manager;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
