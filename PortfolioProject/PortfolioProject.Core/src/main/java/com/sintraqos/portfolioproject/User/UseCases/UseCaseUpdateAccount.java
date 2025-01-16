package com.sintraqos.portfolioproject.User.UseCases;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.User.User;
import com.sintraqos.portfolioproject.User.UserMessage;
import com.sintraqos.portfolioproject.User.UserService;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
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
    private final UserLibraryService userLibraryService;

    @Autowired
    public UseCaseUpdateAccount(UserService userService, UserLibraryService userLibraryService) {
        this.userService = userService;
        this.userLibraryService = userLibraryService;
    }

    /**
     * Update the username of the given account
     *
     * @param currentUsername the current username of the user
     * @param newUsername     the new username of the user
     * @param password        the new password of the user
     */
    public Message changeUsername(String currentUsername, String newUsername, String password) {
        return userService.changeUsername(currentUsername, newUsername, password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username the username of the user
     * @param eMail    the new E-Mail address of the user
     */
    public Message changeEmail(String username, String eMail, String password) {
        return userService.changeEMail(username, eMail, password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username        the username of the user
     * @param currentPassword the current password of the user
     * @param newPassword     the new password of the user
     */
    public Message changePassword(String username, String currentPassword, String newPassword) {
        return userService.changePassword(username, currentPassword, newPassword);
    }

    /**
     * Add a game using an ID
     *
     * @param user the user to add the game to
     * @param gameID   the ID of the game
     */
    public Message addGame(User user, int gameID) {
        return  userLibraryService.addGame(user.getAccountID(), gameID);
    }
}
