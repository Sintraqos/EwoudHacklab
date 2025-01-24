package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.statics.Errors;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling registration of a new account
 */
@Getter
@Component
public class UseCaseRegisterAccount {
    private final UserService userService;
    private final UserLibraryService userLibraryService;

    @Autowired
    public UseCaseRegisterAccount(
            UserService userService,
            UserLibraryService userLibraryService) {
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
    public UserMessage registerAccount(String username, String eMail, String password) {
        //Create new user
        UserMessage message = userService.createAccount(username, eMail, password);
        if (!message.isSuccessful()) {
            return new UserMessage("Failed to create user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        } else {
            return message;
        }
    }
}
