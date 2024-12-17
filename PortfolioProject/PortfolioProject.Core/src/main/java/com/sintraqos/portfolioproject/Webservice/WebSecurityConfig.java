package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(
            UserService userService,
            PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Security filter chain that configures URL access, login, and logout behavior.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/login", "/register", "/error")
                        .permitAll()
                        .requestMatchers("/account", "/settings", "/library", "/forum")
                        .authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/account", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")         // Logout URL
                        .logoutSuccessUrl("/home")    // Redirect after logout
                        .invalidateHttpSession(true)  // Invalidate session on logout
                       .deleteCookies("JSESSIONID")  // Clean up session cookies
                )
                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)           // Limit to 1 active session per user
                        .expiredUrl("/sessionExpired")  // Redirect to session expired page
                );

        return http.build();
    }

    /**
     * Authentication Manager which handles authentication of users when logging in to their account
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        // Create and configure the AuthenticationManagerBuilder
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService)   // Custom user service to load user details
                .passwordEncoder(passwordEncoder); // Custom password encoder

        // Build the AuthenticationManager
        return authenticationManagerBuilder.build();
    }
}
