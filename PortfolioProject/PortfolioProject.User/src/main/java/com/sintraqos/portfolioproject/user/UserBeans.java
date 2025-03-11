package com.sintraqos.portfolioproject.user;

import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.statics.Enums;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeans {

    /**
     * Create default account
     */
    @Bean
    public CommandLineRunner defaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.count() == 0) {
                // If no users exist, create a default user
                UserEntity defaultUser = new UserEntity(
                        "admin",
                        "admin@mail.com",
                        passwordEncoder.encode("password"),
                        Enums.Role.ADMIN // Default role
                );
                userRepository.save(defaultUser); // Save the default user to the database
            }
        };
    }
}
