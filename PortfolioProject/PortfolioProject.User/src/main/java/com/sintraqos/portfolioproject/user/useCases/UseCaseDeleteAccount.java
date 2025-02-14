package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
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

    @Autowired
    public UseCaseDeleteAccount(UserRepository userRepository, UseCaseValidateUser validateUser, UserLibraryService libraryService) {
        this.userRepository = userRepository;
        this.validateUser = validateUser;
        this.libraryService = libraryService;
    }

    /**
     * Delete a user
     *
     * @param username the name of the user
     * @param password the password of the user
     */
    public UserMessage deleteAccount(String username, String password) {
        // Check if an account already exists
        UserEntity account = userRepository.findByUsername(username);
        if (account == null) {
            return new UserMessage(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }

        // Compare the given password to the stored password
        UserMessage passwordCheck = validateUser.comparePassword(account.getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {

            // Clear the stored library of the account
            libraryService.deleteLibrary(account.getAccountID());

            // Delete the account from the database
            userRepository.delete(account);
            return new UserMessage(true, "Successfully removed account with username: '%s'".formatted(username));
        } else {
            return passwordCheck;
        }
    }
}
