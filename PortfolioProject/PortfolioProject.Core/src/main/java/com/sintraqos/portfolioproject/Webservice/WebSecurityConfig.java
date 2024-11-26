package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.API.Review.GameReviewAPI;
import com.sintraqos.portfolioproject.ForumPost.ForumPostManager;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Services.UserService;
import com.sintraqos.portfolioproject.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfig(
            UserService userService,
            PasswordEncoder passwordEncoder
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Security filter chain that configures URL access, login, and logout behavior.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/login", "/register", "/error")
                        .permitAll()
                        .requestMatchers("/account", "/settings", "/library", "/forum")
                        .authenticated()
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
                        .maximumSessions(1)           // Limit to 1 active session per user
                        .expiredUrl("/sessionExpired")  // Redirect to session expired page
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userService)  // Account details service to load user
                .passwordEncoder(passwordEncoder);      // Password encoder
        return authenticationManagerBuilder.build();
    }
}
