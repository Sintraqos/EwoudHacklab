package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

@Configuration
@EnableWebSecurity
public class WebService {

    @Autowired
    AccountManager accountManager;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Pages
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/",
                                // Home
                                "/home",
                                "/returnHome",
                                // Register
                                "/register",
                                "/registerAccount",
                                // Login
                                "/login",
                                "/loginAccount",
                                // Account
                                "/account",
                                // Settings
                                "/settings",
                                // Library
                                "/library"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // Logout configuration
                .logout((logout) -> logout
                        .logoutUrl("/logout")                           // Defines the URL for logging out
                        .logoutSuccessHandler(new CustomLogoutSuccessHandler(accountManager))
                        .logoutSuccessUrl("/home")                      // Redirect after successful logout
                        .invalidateHttpSession(false)                    // Invalidates the session after logout
                        .deleteCookies("JSESSIONID") // Deletes the session cookie (optional)
                )
                .logout(LogoutConfigurer::permitAll); // Allows logout for all users
        return http.build();
    }
}