package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.Messages.*;
import com.sintraqos.portfolioproject.UserLibrary.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Use for user input handling for user related scripts
 */
@Getter
@Component
public class UserManager {

    private final UserService userService;
    private final UserLibraryService userLibraryService;

    @Autowired
    public UserManager(UserService userService, UserLibraryService userLibraryService) {
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

    /**
     * Ban the given account
     *
     * @param username the username of the user
     */
    public Message banAccount(String username){
        return userService.banAccount(username);
    }

    /**
     * Unban the given account
     *
     * @param username the username of the user
     */
    public Message unbanAccount(String username) {
        return userService.unbanAccount(username);
    }

    //region Get User

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

    //endregion

    /**
     * Add a game using an ID
     *
     * @param user the user to add the game to
     * @param gameID   the ID of the game
     */
    public Message addGame(User user, int gameID) {
        return  userLibraryService.addGame(user.getAccountID(), gameID);
    }

    //region Update User

    /**
     * Update the username of the given account
     *
     * @param currentUsername the current username of the user
     * @param newUsername the new username of the user
     * @param password the new password of the user
     */
    public Message changeUsername(String currentUsername, String newUsername, String password) {
        return userService.changeUsername(currentUsername, newUsername, password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username the username of the user
     * @param eMail the new E-Mail address of the user
     */
    public Message changeEmail(String username, String eMail, String password){
        return userService.changeEMail(username,eMail,password);
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username the username of the user
     * @param currentPassword the current password of the user
     * @param newPassword the new password of the user
     */
    public Message changePassword(String username, String currentPassword, String newPassword) {
        return userService.changePassword(username,currentPassword,newPassword);
    }

    //endregion
}
