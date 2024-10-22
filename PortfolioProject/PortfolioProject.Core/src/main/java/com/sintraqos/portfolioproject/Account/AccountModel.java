package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Services.AccountService;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Use for interaction with the ConnectionHandler and the AccountController
 */
@Component
public class AccountModel {
    //MariaDBConnectHandler connectHandler = MariaDBConnectHandler.getInstance();

    static AccountModel instance;

    public static AccountModel getInstance() {
        if (instance == null) {
            instance = new AccountModel();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of AccountModel");
    }
    @Autowired
    private AccountService accountService;

    /**
     * Create a new Account
     *
     * @param username the name of the new account
     * @param eMail    the e-Mail address of the new account
     * @param password the password of the new account
     */
    public void createAccount(String username, String eMail, String password) {
//        // Check if there already is an account with the given name
//        if (getAccount(0) != null) {
//            Console.writeLine("Account already exists");
//            return;
//        }
//
//        // Create new Account object
//        AccountDTO accountDTO = new AccountDTO(username, eMail, password);
//        AccountController accountController =  new AccountController();
//        accountController.createAccount(accountDTO);
//
//        Console.writeLine("Created new account: %s".formatted(username));

        // Create new account
        AccountEntity newAccount = accountService.createAccount(username, eMail, password);
        if (newAccount != null) {
            System.out.println("Created new account: " + username);
        }
    }

    /**
     * Log in to account
     *
     * @param username the name of the new account
     * @param password the password of the new account
     */
    public void loginAccount(String username, String password) {
//        // Try to retrieve the account using the connectionHandler,
//        // if it failed to retrieve it, or just failed for some reason stop this code after writing out the message
//        AccountDTO accountDTO = new AccountDTO(username, password);
//        Message loginAccountMessage = connectHandler.loginAccount(accountDTO);
//        if (!loginAccountMessage.isSuccessful()) {
//            Console.writeLine("Failed to log into account: %s- Reason: %s".formatted(username, loginAccountMessage.getMessage()));
//            return;
//        }

        //Console.writeLine("Successfully logged in to account: %s - ID: ".formatted(accountDTO.getUsername(), accountDTO.getAccountID()));
    }

    /**
     * Log out of account
     *
     * @param username the name of the account
     */
    public void logoutAccount(String username) {
        //TODO: Check if the account is online, then send an update message before logging the user out from their account
//
//        Account account = AccountManager.getInstance().getOnlineAccount(username);
//        if (account == null) {
//            Console.writeLine("Account: %s was not online".formatted(username));
//            return;
//        }
//
//        updateLibrary(account.getAccountID());

        Console.writeLine("Logged out of account: %s".formatted(username));
    }

    /**
     * Log in to account
     *
     * @param accountID the account to update the library of
     */
    public void updateLibrary(int accountID) {
        AccountDTO accountDTO = getAccount(accountID);
        if (accountDTO == null) {
            Console.writeLine("Failed find account with ID: %s".formatted(accountID));
            return;
        }

        Console.writeLine("Successfully added game to account: %s".formatted(accountDTO.getUsername()));
    }

    //region Get Account

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param accountID the ID of the account
     */
    public AccountDTO getAccount(int accountID) {
//        ConnectionHandler.GetAccountMessage getAccountMessage = connectHandler.getAccount(accountID);
//        if (getAccountMessage.getMessage().isSuccessful()) {
//            return getAccountMessage.getAccount();
//        } else {
//            Console.writeLine("Account not found");
            return null;
//        }
    }

    //endregion
}
