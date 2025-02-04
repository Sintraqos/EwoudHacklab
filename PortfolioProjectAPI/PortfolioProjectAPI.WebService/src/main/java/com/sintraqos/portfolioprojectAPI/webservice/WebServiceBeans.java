package com.sintraqos.portfolioprojectAPI.webservice;

import com.sintraqos.portfolioprojectAPI.webservice.authentication.CustomAuthenticationFailureHandler;
import com.sintraqos.portfolioprojectAPI.webservice.authentication.CustomAuthenticationHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class WebServiceBeans {
    @Bean
    public CustomAuthenticationHandler springAuthenticationHandler() {
        return new CustomAuthenticationHandler();
    }
    @Bean
    public CustomAuthenticationFailureHandler springAuthenticationFailureHandler() {
        return new CustomAuthenticationFailureHandler();
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
