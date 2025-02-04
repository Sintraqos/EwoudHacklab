package com.sintraqos.portfolioprojectAPI.user.service;

import com.sintraqos.portfolioprojectAPI.shared.Errors;
import com.sintraqos.portfolioprojectAPI.user.DAL.UserEntity;
import com.sintraqos.portfolioprojectAPI.user.DAL.UserRepository;
import com.sintraqos.portfolioprojectAPI.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }

        // Account is banned, so no login should occur
        if (!userEntity.isAccountNonLocked() || !userEntity.isEnabled()) {
            throw new BadCredentialsException(Errors.ACCOUNT_BANNED.formatted(username));
        }

        return new User(userEntity);
    }
}
