package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Messages.AccountMessage;
import com.sintraqos.portfolioproject.Messages.AccountLibraryEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.AccountLibraryService;
import com.sintraqos.portfolioproject.Services.AccountService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Use for user input handling for account related scripts
 */
@Getter
@Component
public class AccountManager {

    // Local storage of online accounts
    private final ArrayList<Account> onlineAccounts = new ArrayList<>();

    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountLibraryService accountLibraryService;
    @Autowired
    private GameManager gameManager;

    /**
     * Create a new account
     *
     * @param username the name of the new account
     * @param eMail    the e-Mail address of the new account
     * @param password the password of the new account
     */
    public Message createAccount(String username, String eMail, String password) {
        //Create new account
        AccountMessage message = accountService.createAccount(username, eMail, password);
        if (!message.isSuccessful()) {
            return new Message("Failed to create account with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        } else {
            return message;
        }
    }

    /**
     * Create an account
     *
     * @param username the name of the account
     * @param password the password of the account
     */
    public Message deleteAccount(String username, String password) {
        Message message = accountService.deleteAccount(username, password);

        if (message.isSuccessful()) {
            return message;
        } else {
            return new Message("Failed to remove account with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }

    /**
     * Log in to account
     *
     * @param username the name of the new account
     * @param password the password of the new account
     */
    public AccountMessage loginAccount(String username, String password) {

        // Check if the account is already online
        if (getOnlineAccount(username) != null) {
            return new AccountMessage("Failed to login with username: '%s', Reason: 'Account already online'".formatted(username));
        }

        AccountMessage message = accountService.loginAccount(username, password);

        if (message.isSuccessful()) {
            // Add the account to the online list
            onlineAccounts.add(new Account(message.getAccount()));

            return message;
        } else {
            return new AccountMessage("Failed to log in to account with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }

    /**
     * Log out of account
     *
     * @param username the name of the account
     */
    public Message logoutAccount(String username) {
        // Check if the account is logged in
        Account account = getOnlineAccount(username);
        if (account == null) {
            return new Message("Failed to logout user: '%s' since they weren't online".formatted(username));
        }

        // Update the library, then remove the account from the onlineAccounts list
        updateLibrary(account.getAccountID());
        onlineAccounts.remove(account);

        return new Message("Successfully logged out user: '%s'".formatted(username));
    }

    /**
     * Log in to account
     *
     * @param accountID the account to update the library of
     */
    public void updateLibrary(int accountID) {
        //AccountModel.getInstance().updateLibrary(accountID);
    }

    //region Get Account

    /**
     * Get account by username
     *
     * @param username the name of the account
     */
    public Account getOnlineAccount(String username) {
        return onlineAccounts.stream()
                .filter(account -> account.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    /**
     * Get account by ID
     *
     * @param accountID the ID of the account
     */
    public Account getOnlineAccount(int accountID) {
        return onlineAccounts.stream()
                .filter(account -> account.getAccountID() == accountID)
                .findFirst().orElse(null);
    }

    /**
     * Get account using the username
     *
     * @param username the username of the account
     */
    public AccountMessage getAccount(String username) {
        AccountMessage message = accountService.getAccount(username);

        if (!message.isSuccessful()) {
            return new AccountMessage("Failed to retrieve account with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }

        return message;
    }

    /**
     * Get account using the ID
     *
     * @param accountID the ID of the account
     */
    public AccountMessage getAccount(int accountID) {
        AccountMessage message = accountService.getAccount(accountID);

        if (!message.isSuccessful()) {
            return new AccountMessage("Failed to retrieve account with ID: '%s', reason: '%s'".formatted(accountID, message.getMessage()));
        }

        return message;
    }

    //endregion

    /**
     * Add a game using an ID
     *
     * @param username the name of the account
     * @param gameID   the ID of the game
     */
    public Message addGame(String username, int gameID) {

        AccountMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        AccountLibraryEntityMessage libraryMessage = accountLibraryService.addGame(message.getAccount().getAccountID(), gameID);

        return libraryMessage;
    }

    /**
     * Get a game using an ID
     *
     * @param username the name of the account
     * @param gameID   the ID of the game
     */
    public Message getGame(String username, int gameID) {
        AccountMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        AccountLibraryEntityMessage libraryMessage = accountLibraryService.getGame(message.getAccount().getAccountID(), gameID);

        return libraryMessage;
    }
//
//    /**
//     * Get all games from account
//     *
//     * @param username the name of the account
//     */
//    public AccountMessage getGames(String username) {
//        AccountMessage message = setAccount(username);
//        if (!message.isSuccessful()) {
//            return message;
//        }
//
//        return new AccountMessage(message.getAccount(),"");
//    }

    /**
     * Get all information from the given account
     *
     * @param username the name of the account
     */
    public Message displayAccount(String username) {
        AccountMessage message = setAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }
        Account account = new Account(message.getAccount());

        return new Message("""
                Account Information:
                ID:         %s
                Username:   %s
                E-Mail:     %s
                Password:   %s
                Library:    %s
                """.formatted(
                account.getAccountID(),
                account.getUsername(),
                account.getEMail(),
                account.getPassword(),
                account.getAccountLibrary().toString()));
    }

    /**
     * Retrieve all information from the database, and apply it to a new Account object
     *
     * @param username the name of the account
     */
    AccountMessage setAccount(String username) {
        AccountMessage message = getAccount(username);
        if (!message.isSuccessful()) {
            return message;
        }

        List<AccountLibraryEntity> entityList = accountLibraryService.getLibrary(message.getAccount().getAccountID());
        ArrayList<Game> games = new ArrayList<>();
        for (AccountLibraryEntity entity : entityList) {
            games.add(new Game(entity, gameManager.getGame(entity.getGameID())));
        }

        Account account = new Account(message.getAccount());
        account.setAccountLibrary(games);

        return message;
    }

    public Message updateAccount(String username, String eMail, String password) {
        return new Message("updateAccount");

        //TODO: Update the account information
    }
}
