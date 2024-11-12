package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.User.*;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import lombok.Getter;

/**
 * Account DTO, use for transfer of account data
 */
@Getter
public class UserDTO {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String username;
    private String eMail;
    private String password;
    private UserLibraryDTO accountLibrary;

    public UserDTO() {
    }

    /**
     * Create a new AccountDTO object based on Account
     *
     * @param user the Account object created beforehand
     */
    public UserDTO(User user) {
        this.accountID = user.getAccountID();
        this.username = user.getUsername();
        this.eMail = user.getEMail();
        this.password = user.getPassword();
        this.accountLibrary = new UserLibraryDTO(user.getUserLibrary());
    }

    /**
     * Create a new AccountDTO object based on AccountEntity
     *
     * @param account the AccountEntity object retrieved from the database
     */
    public UserDTO(UserEntity account) {
        this.accountID = account.getAccountID();
        this.username = account.getUsername();
        this.eMail = account.getEMail();
        this.password = account.getPasswordHash();
        this.accountLibrary = new UserLibraryDTO();
    }
}
