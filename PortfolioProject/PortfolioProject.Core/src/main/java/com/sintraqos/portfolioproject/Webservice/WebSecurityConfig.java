package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.User.UserService;
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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * Configuration for the web application
 * Currently handles the following configurations:
 * SecurityFilterChain,
 * AuthenticationManager,
 * ResourceHandlerRegistry
*/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig  implements WebMvcConfigurer {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    private final CustomAuthenticationHandler customAuthenticationHandler;

    @Autowired
    public WebSecurityConfig(
            UserService userService,
            PasswordEncoder passwordEncoder,
            CustomAuthenticationFailureHandler customAuthenticationFailureHandler,
            CustomAuthenticationHandler customAuthenticationHandler
    ){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
        this.customAuthenticationHandler = customAuthenticationHandler;
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
                .authenticationManager(authenticationManager(http))
                .authorizeRequests(requests -> requests
                        .requestMatchers("/", "/home", "/login", "/register", "/error")
                        .permitAll()
                        .requestMatchers("/account", "/settings", "/library", "/forum")
                        .authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/images/**").permitAll()  // Allow static image access
                        .requestMatchers("/css/**").permitAll()     // Allow style access
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/account", true)
                        .failureHandler(customAuthenticationFailureHandler)
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

        // Add the custom authentication provider
        authenticationManagerBuilder.authenticationProvider(customAuthenticationHandler);

        // Build the AuthenticationManager
        return authenticationManagerBuilder.build();
    }

    /**
     * Resource handler registration, use for handling where the images etc. are stored on the webservice
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(3600)  // Set cache period as needed
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
    }
}
