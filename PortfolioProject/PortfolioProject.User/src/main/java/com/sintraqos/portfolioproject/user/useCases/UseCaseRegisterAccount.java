package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling registration of a new account
 */
@Getter
@Component
public class UseCaseRegisterAccount {
    private final UseCaseValidateUser validateUser;
    private final UserLibraryService userLibraryService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final Logger logger;

    @Autowired
    public UseCaseRegisterAccount(
            UseCaseValidateUser validateUser,
            UserLibraryService userLibraryService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository,
            Logger logger) {
        this.validateUser = validateUser;
        this.userLibraryService = userLibraryService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.logger = logger;
    }

    /**
     * Create a new user
     *
     * @param username the name of the new user
     * @param eMail    the e-Mail address of the new user
     * @param password the password of the new user
     */
    public UserMessage registerAccount(String username, String eMail, String password,Enums.Role role) {
        logger.debug("Attempting to register new account with username: '%s'".formatted(username));
        // Check if the user is valid
        UserMessage validateUserMessage = validateUser.validateUser(username, eMail, password);
        if (!validateUserMessage.isSuccessful()) {
            logger.debug(validateUserMessage.getMessage());
            return validateUserMessage;
        }

        // Create and save the new user
        UserEntity userEntity = new UserEntity(username, eMail, passwordEncoder.encode(password), role);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        // Cast the accountEntity to an AccountDTO object for transfer
        String message = "Created new account: '%s'".formatted(username);
        logger.debug(message);

        return new UserMessage(userEntity, message);
    }
}
