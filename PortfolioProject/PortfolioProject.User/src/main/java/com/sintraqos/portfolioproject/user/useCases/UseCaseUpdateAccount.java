package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase of handling the updating of the stored information of a given account
 */
@Getter
@Component
public class UseCaseUpdateAccount {
    private final UserService userService;

    @Autowired
    public UseCaseUpdateAccount(UserService userService) {
        this.userService = userService;
    }

    /**
     * Update the username of the given account
     *
     * @param currentUsername the current username of the user
     * @param newUsername     the new username of the user
     * @param password        the new password of the user
     */
    public UserMessage changeUsername(String currentUsername, String newUsername, String password) {
        return userService.changeUsername(currentUsername, newUsername, password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username the username of the user
     * @param eMail    the new E-Mail address of the user
     */
    public UserMessage changeEmail(String username, String eMail, String password) {
        return userService.changeEMail(username, eMail, password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username        the username of the user
     * @param currentPassword the current password of the user
     * @param newPassword     the new password of the user
     */
    public UserMessage changePassword(String username, String currentPassword, String newPassword) {
        return userService.changePassword(username, currentPassword, newPassword);
    }
}
