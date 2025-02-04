package com.sintraqos.portfolioprojectAPI.webservice.authentication;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {

        // Redirect to login page with error message
        String redirectUrl = UriComponentsBuilder.fromPath("/login")
                .queryParam("warning", exception.getMessage())
                .toUriString();

        response.sendRedirect(redirectUrl);
    }
}
