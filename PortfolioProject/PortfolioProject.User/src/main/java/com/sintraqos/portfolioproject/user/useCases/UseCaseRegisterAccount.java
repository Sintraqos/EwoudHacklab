package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
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

    @Autowired
    public UseCaseRegisterAccount(
            UseCaseValidateUser validateUser,
            UserLibraryService userLibraryService,
            PasswordEncoder passwordEncoder,
            UserRepository userRepository) {
        this.validateUser = validateUser;
        this.userLibraryService = userLibraryService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Create a new user
     *
     * @param username the name of the new user
     * @param eMail    the e-Mail address of the new user
     * @param password the password of the new user
     */
    public UserMessage registerAccount(String username, String eMail, String password) {
        // Check if the user is valid
        UserMessage validateUserMessage = validateUser.validateUser(username, eMail, password);
        if (!validateUserMessage.isSuccessful()) {
            return validateUserMessage;
        }

        // Create and save the new user
        UserEntity userEntity = new UserEntity(username, eMail, passwordEncoder.encode(password), Enums.Role.USER);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        // Cast the accountEntity to an AccountDTO object for transfer
        return new UserMessage(userEntity, "Created new account: '%s'".formatted(username));
    }
}
