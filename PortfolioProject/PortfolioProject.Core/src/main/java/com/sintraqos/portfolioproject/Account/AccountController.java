package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Connect.ConnectionHandler;
import com.sintraqos.portfolioproject.Connect.MariaDBConnectHandler;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for user input handling for account related scripts
 */
@Getter
public class AccountController {
    MariaDBConnectHandler connectHandler = MariaDBConnectHandler.getInstance();

    static AccountController instance;

    public static AccountController getInstance() {
        if (instance == null) {
            instance = new AccountController();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of AccountController");
        AccountModel.getInstance();
        AccountView.getInstance();
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param userName the name of the account we're looking for
     */
    public Account getAccount(String userName) {
        ConnectionHandler.GetAccountMessage getAccountMessage =connectHandler.getAccount(new Account(userName,""));
        if(getAccountMessage.getMessage().isSuccessful()){
        return getAccountMessage.getAccount();}
        else {
            Console.writeLine("Account not found");
            return null;
        }
    }

    /**
     * Create a new Account
     *
     * @param userName the name of the new account
     * @param eMail    the e-Mail address of the new account
     * @param password the password of the new account
     */
    public void createAccount(String userName, String eMail, String password) {
        // Check if there already is an account with the given name
        if (getAccount(userName) != null) {
            Console.writeLine("Account already exists");
            return;
        }

        // Create new Account object
        Account account = new Account(userName, eMail, password);

        Message createAccountMessage = connectHandler.createAccount(account);
        if (!createAccountMessage.isSuccessful()) {
            Console.writeLine("Failed to create new account: " + userName + " - Reason: " + createAccountMessage.getMessage());
            return;
        }

        Console.writeLine("Created new account: " + userName);
    }

    /**
     * Log in to account
     *
     * @param userName the name of the new account
     * @param password the password of the new account
     */
    public void loginAccount(String userName, String password){
        // Try to retrieve the account using the connectionHandler,
        // if it failed to retrieve it, or just failed for some reason stop this code after writing out the message
        Account account = new Account(userName, password);
        Message loginAccountMessage = connectHandler.loginAccount(account);
        if (!loginAccountMessage.isSuccessful()) {
            Console.writeLine("Failed to log into account: " + userName + " - Reason: " + loginAccountMessage.getMessage());
            return;
        }

        Console.writeLine("Successfully logged in to account: " + userName);
    }

    /**
     * Log in to account
     *
     * @param username the account to update the library of
     */
    public void updateLibrary(String username){
        Account account = getAccount(username);
        if(account == null) {
            Console.writeLine("Failed to add game to account: " + account.getUserName());
            return;
        }

        Console.writeLine("Successfully added game to account: " + account.getUserName());
    }

}
