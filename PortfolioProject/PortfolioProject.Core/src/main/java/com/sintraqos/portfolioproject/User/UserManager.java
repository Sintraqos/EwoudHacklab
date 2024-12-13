package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Messages.UserMessage;
import com.sintraqos.portfolioproject.Messages.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.UserLibraryService;
import com.sintraqos.portfolioproject.Services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Use for user input handling for user related scripts
 */
@Getter
@Component
public class UserManager {

    // Local storage of online accounts

    private final UserService userService;
    private final UserLibraryService userLibraryService;
    private final GameManager gameManager;

    @Autowired
    public UserManager(UserService userService, UserLibraryService userLibraryService, GameManager gameManager) {
        this.userService = userService;
        this.userLibraryService = userLibraryService;
        this.gameManager = gameManager;
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

    //region Get Account

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
     * Get user using the username
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

    //endregion

    /**
     * Add a game using an ID
     *
     * @param user the user to add the game to
     * @param gameID   the ID of the game
     */
    public Message addGame(User user, int gameID) {
        UserLibraryEntityMessage libraryMessage = userLibraryService.addGame(user.getAccountID(), gameID);

        return libraryMessage;
    }

    /**
     * Retrieve all information from the database, and apply it to a new Account object
     *
     * @param username the name of the user
     */
    UserMessage setAccount(String username) {
        UserMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        List<UserLibraryEntity> entityList = userLibraryService.getLibrary(message.getUserDTO().getAccountID());
        ArrayList<Game> games = new ArrayList<>();
        for (UserLibraryEntity entity : entityList) {
            games.add(new Game(entity, gameManager.getGame(entity.getGameID()).getEntity()));
        }

        User user = new User(message.getUserDTO());
        user.setUserLibrary(games);

        return message;
    }

    public UserMessage getAccounts(String username) {
        return userService.getAccounts(username);
    }

    public Message changeUsername(String currentUsername, String newUsername, String password) {
        return userService.changeUsername(currentUsername, newUsername, password);
    }
}
