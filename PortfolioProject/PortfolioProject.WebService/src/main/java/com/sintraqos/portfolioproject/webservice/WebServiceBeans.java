package com.sintraqos.portfolioproject.webservice;

// Project components
import com.sintraqos.portfolioproject.webservice.authentication.CustomAuthenticationHandler;

// Spring components
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class WebServiceBeans {
    @Bean
    public CustomAuthenticationHandler springAuthenticationHandler() {
        return new CustomAuthenticationHandler();
    }

    /**
     * Password encoder used for secure password storage
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }
}
