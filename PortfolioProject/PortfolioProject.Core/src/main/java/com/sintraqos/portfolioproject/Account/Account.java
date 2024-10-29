package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Game.Game;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storing account data locally
 */
@Getter
public class Account {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String username;
    private String eMail;
    private String password;
    private AccountLibrary accountLibrary;

    public Account() {
    }

    /**
     * Create a new account object from a DTO object
     *
     * @param accountDTO the incoming DTO object
     */
    public Account(AccountDTO accountDTO){
        this.accountID = accountDTO.getAccountID();
        this.username = accountDTO.getUsername();
        this.eMail = accountDTO.getEMail();
        this.password = accountDTO.getPassword();
        this.accountLibrary = new AccountLibrary(accountDTO.getAccountLibrary());
    }

    /**
     * Create a new account object from an Entity object
     *
     * @param accountEntity the incoming Entity object
     */
    public Account(AccountEntity accountEntity){
        this.accountID = accountEntity.getAccountID();
        this.username = accountEntity.getUsername();
        this.eMail = accountEntity.getEMail();
        this.password = accountEntity.getPasswordHash();
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        accountLibrary = new AccountLibrary();
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param eMail    the e-mail of the account
     * @param password the password of the account
     */
    public Account(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.accountLibrary = new AccountLibrary();
    }

    /**
     * When importing the account from external location.
     * Since it isn't important to get the password or email when logged in don't fill it in
     *
     * @param accountID   the ID of the user inside the database
     * @param username    the userName of the account
     * @param gameLibrary library the account has stored inside the database
     */
    public Account(int accountID, String username, ArrayList<Game> gameLibrary) {
        this.accountID = accountID;
        this.username = username;
        this.accountLibrary = new AccountLibrary(gameLibrary);
    }
}
