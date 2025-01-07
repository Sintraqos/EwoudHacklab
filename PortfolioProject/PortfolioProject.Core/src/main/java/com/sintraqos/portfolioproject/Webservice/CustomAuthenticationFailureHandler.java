package com.sintraqos.portfolioproject.Webservice;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Invalid username or password.";  // Default error message

        if (exception instanceof BadCredentialsException) {
            errorMessage = exception.getMessage();  // Get the message from the exception
        }

        // Redirect to login page with error message in query parameters
        String redirectUrl = UriComponentsBuilder.fromPath("/login")
                .queryParam("error", errorMessage)
                .toUriString();

        response.sendRedirect(redirectUrl);
    }
}
