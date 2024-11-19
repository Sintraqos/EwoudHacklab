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
    private final ArrayList<User> onlineUsers = new ArrayList<>();

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
     * Create a user
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
     * Log in to user
     *
     * @param username the name of the new user
     * @param password the password of the new user
     */
    public UserMessage loginAccount(String username, String password) {

        // Check if the user is already online
        if (getOnlineAccount(username) != null) {
            return new UserMessage("Failed to login with username: '%s', Reason: 'Account already online'".formatted(username));
        }

        UserMessage message = userService.loginAccount(username, password);

        if (message.isSuccessful()) {
            // Add the user to the online list
            onlineUsers.add(new User(message.getAccount()));

            return message;
        } else {
            return new UserMessage("Failed to log in to user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }

    /**
     * Log out of user
     *
     * @param username the name of the user
     */
    public Message logoutAccount(String username) {
        // Check if the user is logged in
        User user = getOnlineAccount(username);
        if (user == null) {
            return new Message("Failed to logout user: '%s' since they weren't online".formatted(username));
        }

        // Update the library, then remove the user from the onlineAccounts list
        updateLibrary(user.getUsername());
        onlineUsers.remove(user);

        return new Message("Successfully logged out user: '%s'".formatted(username));
    }

    /**
     * Log in to user
     *
     * @param username the user to update the library of
     */
    public void updateLibrary(String username) {
        //AccountModel.getInstance().updateLibrary(accountID);
    }

    //region Get Account

    /**
     * Get user by username
     *
     * @param username the name of the user
     */
    public User getOnlineAccount(String username) {
        return onlineUsers.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
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
     * @param username the name of the user
     * @param gameID   the ID of the game
     */
    public Message addGame(String username, int gameID) {

        UserMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        UserLibraryEntityMessage libraryMessage = userLibraryService.addGame(message.getAccount().getAccountID(), gameID);

        return libraryMessage;
    }

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
     * Get a game using an ID
     *
     * @param username the name of the user
     * @param gameID   the ID of the game
     */
    public Message getGame(String username, int gameID) {
        UserMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        UserLibraryEntityMessage libraryMessage = userLibraryService.getGame(message.getAccount().getAccountID(), gameID);

        return libraryMessage;
    }

    /**
     * Get all information from the given user
     *
     * @param username the name of the user
     */
    public Message displayAccount(String username) {
        UserMessage message = setAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }
        User user = new User(message.getAccount());

        return new Message("""
                Account Information:
                ID:         %s
                Username:   %s
                E-Mail:     %s
                Password:   %s
                Library:    %s
                """.formatted(
                user.getAccountID(),
                user.getUsername(),
                user.getEMail(),
                user.getPassword(),
                user.getUserLibrary().toString()));
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

        List<UserLibraryEntity> entityList = userLibraryService.getLibrary(message.getAccount().getAccountID());
        ArrayList<Game> games = new ArrayList<>();
        for (UserLibraryEntity entity : entityList) {
            games.add(new Game(entity, gameManager.getGame(entity.getGameID()).getEntity()));
        }

        User user = new User(message.getAccount());
        user.setUserLibrary(games);

        return message;
    }

    public Message updateAccount(String username, String eMail, String password) {
        return new Message("updateAccount");

        //TODO: Update the user information
    }
}
