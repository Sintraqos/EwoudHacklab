package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Account.*;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import lombok.Getter;

/**
 * Account DTO, use for transfer of account data
 */
@Getter
public class AccountDTO {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String username;
    private String eMail;
    private String password;
    private AccountLibraryDTO accountLibrary;

    public AccountDTO(){}

    /**
     * Create a new AccountDTO object based on Account
     *
     * @param account the Account object created beforehand
     */
    public AccountDTO(Account account) {
        this.accountID = account.getAccountID();
        this.username = account.getUsername();
        this.eMail = account.getEMail();
        this.password = account.getPassword();
        this.accountLibrary = new AccountLibraryDTO(account.getAccountLibrary());
    }

    /**
     * Create a new AccountDTO object based on AccountEntity
     *
     * @param account the AccountEntity object retrieved from the database
     */
    public AccountDTO(AccountEntity account) {
        this.accountID = account.getAccountID();
        this.username = account.getUsername();
        this.eMail = account.getEMail();
        this.password = account.getPasswordHash();
    }
}
