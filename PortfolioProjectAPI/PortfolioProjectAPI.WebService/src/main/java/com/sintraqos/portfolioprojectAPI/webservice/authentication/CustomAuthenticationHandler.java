package com.sintraqos.portfolioprojectAPI.webservice.authentication;

import com.sintraqos.portfolioprojectAPI.shared.Errors;
import com.sintraqos.portfolioprojectAPI.user.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationHandler implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private Logger logger;

    // Inject PasswordEncoder to handle password encoding and matching
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        try {
            // Load the user details from the UserService
            UserDetails userDetails = userService.loadUserByUsername(username);

            // Compare the raw password with the encoded password from the database
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                // If passwords don't match, throw BadCredentialsException
                throw new BadCredentialsException("Invalid credentials");
            }

            // Return a token with the user details and authorities if authentication is successful
            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        } catch (UsernameNotFoundException | BadCredentialsException ex) {
            logger.warn(ex.getMessage());
            throw ex;  // Re-throw if user is not found or credentials are invalid
        } catch (Exception ex) {
            logger.warn(ex.getMessage());
            throw new InternalAuthenticationServiceException(Errors.AUTH_FAILED, ex);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
