package com.sintraqos.portfolioproject.Webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final AccountDetailsService accountDetailsService;

    /**
     * Configure AuthenticationManager to work with AccountDetailsService and PasswordEncoder.
     */
    public WebSecurityConfig(AccountDetailsService accountDetailsService) {
        this.accountDetailsService = accountDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(accountDetailsService)  // Account details service to load user
                .passwordEncoder(passwordEncoder());      // Password encoder
        return authenticationManagerBuilder.build();
    }
    /**
     * Password encoder used for secure password storage (BCrypt is a common choice).
     * Removed the circular dependency by defining this independently.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    /**
     * Security filter chain that configures URL access, login, and logout behavior.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/login", "/loginAccount", "/register", "/registerAccount")
                        .permitAll()  // Public pages
                        .requestMatchers("/account", "/settings", "/library")
                        .authenticated()  // Protected pages
                        .anyRequest().authenticated()  // Any other request must be authenticated
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")          // Custom login page
                        .permitAll()                  // Allow everyone to access login page
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")         // Logout URL
                        .logoutSuccessUrl("/home")    // Redirect after logout
                        .invalidateHttpSession(true)  // Invalidate session on logout
                        .deleteCookies("JSESSIONID")  // Clean up session cookies
                )
                .sessionManagement(session -> session
                        .maximumSessions(1)           // Limit to 1 active session per user
                        .expiredUrl("/sessionExpired")  // Redirect to session expired page
                );

        return http.build();
    }
}
