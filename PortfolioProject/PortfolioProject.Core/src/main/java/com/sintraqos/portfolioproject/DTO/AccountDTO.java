package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Account.*;
import lombok.Getter;

@Getter
public class AccountDTO {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String username;
    private String eMail;
    private String password;
    private AccountLibrary accountLibrary;

    public AccountDTO(){}

    public AccountDTO(Account account) {
        this.accountID = account.getAccountID();
        this.username = account.getUsername();
        this.eMail = account.getEMail();
        this.password = account.getPassword();
        this.accountLibrary = account.getAccountLibrary();
    }

    public AccountDTO(String username) {
        this.username = username;
    }

    public AccountDTO(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
    }

    public AccountDTO(String username, String eMail, String password, AccountLibrary accountLibrary) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.accountLibrary = accountLibrary;
    }

    public AccountDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AccountDTO(int accountID, String username, String eMail, String password, AccountLibrary accountLibrary) {
        this.accountID = accountID;
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.accountLibrary = accountLibrary;
    }
}
