package com.sintraqos.portfolioprojectAPI.user.useCases;

import com.sintraqos.portfolioprojectAPI.user.entities.UserMessage;
import com.sintraqos.portfolioprojectAPI.user.service.UserService;
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
    public UserMessage deleteAccount(String username, String password) {
        UserMessage message = userService.deleteAccount(username, password);

        if (message.isSuccessful()) {
            return message;
        } else {
            return new UserMessage("Failed to remove user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }
}
