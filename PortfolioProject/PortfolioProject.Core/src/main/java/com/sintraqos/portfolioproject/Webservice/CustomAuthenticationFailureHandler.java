package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Statics.Console;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {

        String errorMessage = exception.getMessage();

        Console.writeLine("CustomAuthenticationFailureHandler: " +  errorMessage);
        Console.writeLine("Exception type: " + exception.getClass().getName());  // Log the full exception class name

        // Redirect to login page with error message
        String redirectUrl = UriComponentsBuilder.fromPath("/login")
                .queryParam("error", errorMessage)
                .toUriString();

        response.sendRedirect(redirectUrl);
    }
}
