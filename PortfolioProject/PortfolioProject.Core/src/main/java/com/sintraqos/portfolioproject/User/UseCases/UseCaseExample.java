package com.sintraqos.portfolioproject.User.UseCases;

import com.sintraqos.portfolioproject.User.UserMessage;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Example useCase script
 */
@Getter
@Component
public class UseCaseExample {
    private final UserService userService;
    private final UserLibraryService userLibraryService;

    @Autowired
    public UseCaseExample(UserService userService, UserLibraryService userLibraryService) {
        this.userService = userService;
        this.userLibraryService = userLibraryService;
    }

    /**
     * Create a new user
     *
     * @param username the name of the new user
     * @param eMail    the e-Mail address of the new user
     * @param password the password of the new user
     */
    public UserMessage createAccount(String username, String eMail, String password) {
        //Create new user
        UserMessage message = userService.createAccount(username, eMail, password);
        if (!message.isSuccessful()) {
            return new UserMessage("Failed to create user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        } else {
            return message;
        }
    }
}
