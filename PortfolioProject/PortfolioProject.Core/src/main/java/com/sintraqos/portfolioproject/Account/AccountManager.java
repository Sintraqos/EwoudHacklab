package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Services.AccountService;
import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Use for user input handling for account related scripts
 */
@Getter
@Component
public class AccountManager {
    static AccountManager instance;

    public static AccountManager getInstance() {
        if (instance == null) {
            instance = new AccountManager();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of AccountController");
        AccountModel.getInstance();
        AccountView.getInstance();
    }

    ArrayList<Account> onlineAccounts = new ArrayList<>();
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
        //Create new account
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
        AccountModel.getInstance().loginAccount(username, password);
    }

    /**
     * Log out of account
     *
     * @param username the name of the account
     */
    public void logoutAccount(String username) {
        //TODO: Check if the account is online, then send an update message before logging the user out from their account

        Account account = getOnlineAccount(username);
        if (account == null) {
            Console.writeLine("Account: %s was not online".formatted(username));
            return;
        }

        updateLibrary(account.getAccountID());

        Console.writeLine("Logged out of account: %s".formatted(username));
    }

    /**
     * Log in to account
     *
     * @param accountID the account to update the library of
     */
    public void updateLibrary(int accountID) {
        AccountModel.getInstance().updateLibrary(accountID);
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
     * Create a new AccountLibrary object with a filled in list
     *
     * @param accountID the ID of the account
     */
    public AccountDTO getAccount(int accountID) {
        return AccountModel.getInstance().getAccount(accountID);
    }

    //endregion
}
