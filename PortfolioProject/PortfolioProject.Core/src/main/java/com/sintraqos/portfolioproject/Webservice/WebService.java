package com.sintraqos.portfolioproject.Webservice;

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
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Pages
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(
                                "/",
                                "/home",
                                "/register",
                                "/registerAccount",
                                "/login",
                                "/loginAccount"
                        ).permitAll()
                        .anyRequest().authenticated()
                )
                // Logout
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

}