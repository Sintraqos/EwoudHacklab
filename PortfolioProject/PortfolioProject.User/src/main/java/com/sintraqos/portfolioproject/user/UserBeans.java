package com.sintraqos.portfolioproject.user;

// Project components
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.statics.Enums;

// Spring components
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserBeans {

    /**
     * Create default account
     */
    @Bean
    public CommandLineRunner defaultUser(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // If there are no stored users inside the database create a default admin
            if (userRepository.count() == 0) {
                // Create default user
                UserEntity defaultUser = new UserEntity(
                        "admin",
                        "admin@mail.com",
                        passwordEncoder.encode("password"),
                        Enums.Role.ADMIN // Admin role
                );
                // Save the user to the database
                userRepository.save(defaultUser);
            }
        };
    }
}
