package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Messages.AccountEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.AccountService;
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

   private final ArrayList<AccountDTO> onlineAccounts = new ArrayList<>();

    @Autowired
    private AccountService accountService;

    /**
     * Create a new account
     *
     * @param username the name of the new account
     * @param eMail    the e-Mail address of the new account
     * @param password the password of the new account
     */
    public Message createAccount(String username, String eMail, String password) {
        //Create new account
        AccountEntityMessage message = accountService.createAccount(username, eMail, password);
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
    public Message loginAccount(String username, String password) {

        if(getOnlineAccount(username) != null){
            return new Message("Failed to login with username: '%s', Reason: 'Account already online'".formatted(username));
        }

        AccountEntityMessage message = accountService.loginAccount(username, password);

        if (message.isSuccessful()) {
            // Add the account to the online list
            onlineAccounts.add(new AccountDTO(message.getEntity()));

            return message;
        } else {
            return new Message("Failed to log in to account with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }

    /**
     * Log out of account
     *
     * @param username the name of the account
     */
    public void logoutAccount(String username) {
        //TODO: Check if the account is online, then send an update message before logging the user out from their account

        AccountDTO account = getOnlineAccount(username);
        if (account == null) {
            return;
        }

        updateLibrary(account.getAccountID());
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
    public AccountDTO getOnlineAccount(String username) {
        return onlineAccounts.stream()
                .filter(account -> account.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    /**
     * Get account by ID
     *
     * @param accountID the ID of the account
     */
    public AccountDTO getOnlineAccount(int accountID) {
        return onlineAccounts.stream()
                .filter(account -> account.getAccountID() == accountID)
                .findFirst().orElse(null);
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param username the username of the account
     */
    public AccountDTO getAccount(String username) {
        return new AccountDTO(accountService.getAccount(username).getEntity());
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param accountID the ID of the account
     */
    public AccountDTO getAccount(int accountID) {
        return new AccountDTO(accountService.getAccount(accountID).getEntity());
    }

    //endregion
}
