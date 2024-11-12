package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Entities.UserEntity;
import com.sintraqos.portfolioproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new User(userEntity.getUsername(), userEntity.getPasswordHash(), List.of());
    }
}