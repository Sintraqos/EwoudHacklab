package com.sintraqos.portfolioproject.User.UseCases;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.User.UserMessage;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for deleting the given account
 */
@Getter
@Component
public class UseCaseDeleteAccount {
    private final UserService userService;

    @Autowired
    public UseCaseDeleteAccount(UserService userService) {
        this.userService = userService;
    }

    /**
     * Delete a user
     *
     * @param username the name of the user
     * @param password the password of the user
     */
    public Message deleteAccount(String username, String password) {
        Message message = userService.deleteAccount(username, password);

        if (message.isSuccessful()) {
            return message;
        } else {
            return new Message("Failed to remove user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }
}
