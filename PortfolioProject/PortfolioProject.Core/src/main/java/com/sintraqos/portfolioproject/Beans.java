package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Services.GameService;
import com.sintraqos.portfolioproject.Services.UserLibraryService;
import com.sintraqos.portfolioproject.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Beans {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService())  // Account details service to load user
                .passwordEncoder(passwordEncoder());      // Password encoder
        return authenticationManagerBuilder.build();
    }

    /**
     * Password encoder used for secure password storage
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public UserService userService() {
        return new UserService();
    }
    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public GameService gameService() {
        return new GameService();
    }
    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public UserLibraryService userLibraryService() {
        return new UserLibraryService();
    }
}
