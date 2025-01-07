package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Services.UserService;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationHandler  implements AuthenticationProvider {
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            UserDetails userDetails = userService.loadUserByUsername(username);
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        } catch (BadCredentialsException ex) {
            Console.writeWarning(ex.getMessage());
            throw ex;  // Re-throw the exception if the account is banned or credentials are incorrect
        } catch (UsernameNotFoundException ex) {
            Console.writeWarning(ex.getMessage());
            throw ex;  // Re-throw if user is not found
        } catch (Exception ex) {
            Console.writeWarning(ex.getMessage());
            // Catch any unexpected errors here
            throw new InternalAuthenticationServiceException("Authentication failed", ex);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
