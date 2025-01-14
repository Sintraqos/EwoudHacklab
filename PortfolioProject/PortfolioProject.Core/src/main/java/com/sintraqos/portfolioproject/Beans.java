package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Game.GameService;
import com.sintraqos.portfolioproject.User.UserEntity;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
import com.sintraqos.portfolioproject.User.UserRepository;
import com.sintraqos.portfolioproject.Statics.*;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.Webservice.CustomAuthenticationHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity6.dialect.SpringSecurityDialect;

@Configuration
public class Beans {

    /**
     * Password encoder used for secure password storage
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public UserService userService() {
        return new UserService();
    }

    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public GameService gameService() {
        return new GameService();
    }

    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public UserLibraryService userLibraryService() {
        return new UserLibraryService();
    }

    /**
     * Service to handle storage of cache data
     */
    @Bean
    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
        return (cacheManager) -> cacheManager.setAllowNullValues(false);
    }

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
                Console.writeLine("Default user created.");
            }
        };
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

    @Bean
    public CustomAuthenticationHandler springAuthenticationHandler() {
        return new CustomAuthenticationHandler();
    }
}
