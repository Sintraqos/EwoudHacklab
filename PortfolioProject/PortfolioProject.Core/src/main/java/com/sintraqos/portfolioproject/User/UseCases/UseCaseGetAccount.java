package com.sintraqos.portfolioproject.User.UseCases;

import com.sintraqos.portfolioproject.User.UserMessage;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for getting accounts from the database
 */
@Getter
@Component
public class UseCaseGetAccount {
    private final UserService userService;

    @Autowired
    public UseCaseGetAccount(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get user using the username
     *
     * @param username the username of the user
     */
    public UserMessage getAccount(String username) {
        UserMessage message = userService.getAccount(username);

        if (!message.isSuccessful()) {
            return new UserMessage("Failed to retrieve user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }

        return message;
    }

    /**
     * Get user using the accountID
     *
     * @param accountID the ID of the user
     */
    public UserMessage getAccount(int accountID) {
        UserMessage message = userService.getAccount(accountID);

        if (!message.isSuccessful()) {
            return new UserMessage("Failed to retrieve user with ID: '%s', reason: '%s'".formatted(accountID, message.getMessage()));
        }

        return message;
    }

    /**
     * Get all users containing the username
     *
     * @param username the username of the user
     */
    public UserMessage getAccounts(String username) {
        return userService.getAccounts(username);
    }
    }
