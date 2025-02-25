package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for deleting the given account
 */
@Getter
@Component
public class UseCaseDeleteAccount {
    private final UserRepository userRepository;
    private final UseCaseValidateUser validateUser;
    private final UserLibraryService libraryService;
    private final Logger logger;

    @Autowired
    public UseCaseDeleteAccount(UserRepository userRepository,
                                UseCaseValidateUser validateUser,
                                UserLibraryService libraryService,
                                Logger logger) {
        this.userRepository = userRepository;
        this.validateUser = validateUser;
        this.libraryService = libraryService;
        this.logger = logger;
    }

    /**
     * Delete a user
     *
     * @param username the name of the user
     * @param password the password of the user
     */
    public UserMessage deleteAccount(String username, String password) {
        logger.debug("Attempting to delete account with username: '%s'".formatted(username));
        // Check if an account already exists
        UserEntity account = userRepository.findByUsername(username);
        if (account == null) {
            String message = Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username);
            logger.debug(message);

            return new UserMessage(message);
        }

        // Compare the given password to the stored password
        UserMessage passwordCheck = validateUser.comparePassword(account.getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            // Clear the stored library of the account
            logger.debug("Deleting library from account: '%s'".formatted(account.getAccountID()));
            libraryService.deleteLibrary(account.getAccountID());

            // Delete the account from the database
            logger.debug("Deleting account: '%s'".formatted(account.getAccountID()));
            userRepository.delete(account);

            // Finalize account removal
            String message = "Successfully removed account with username: '%s'".formatted(username);
            logger.debug(message);
            return new UserMessage(true, message);
        } else {
            logger.debug(passwordCheck.getMessage());
            return passwordCheck;
        }
    }
}
