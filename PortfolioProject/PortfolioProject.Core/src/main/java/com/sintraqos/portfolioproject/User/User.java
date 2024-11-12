package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import com.sintraqos.portfolioproject.Game.Game;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storing account data locally
 */
@Getter
public class User {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String username;
    private String eMail;
    private String password;
    private UserLibrary userLibrary;

    public User() {
    }

    /**
     * Create a new account object from a DTO object
     *
     * @param userDTO the incoming DTO object
     */
    public User(UserDTO userDTO){
        this.accountID = userDTO.getAccountID();
        this.username = userDTO.getUsername();
        this.eMail = userDTO.getEMail();
        this.password = userDTO.getPassword();
        this.userLibrary = new UserLibrary(userDTO.getAccountLibrary());
    }

    /**
     * Create a new account object from an Entity object
     *
     * @param userEntity the incoming Entity object
     */
    public User(UserEntity userEntity){
        this.accountID = userEntity.getAccountID();
        this.username = userEntity.getUsername();
        this.eMail = userEntity.getEMail();
        this.password = userEntity.getPasswordHash();
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        userLibrary = new UserLibrary();
    }

    /**
     * Create a new account object without any games
     *
     * @param username the userName of the account
     * @param eMail    the e-mail of the account
     * @param password the password of the account
     */
    public User(String username, String eMail, String password) {
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.userLibrary = new UserLibrary();
    }

    /**
     * When importing the account from external location.
     * Since it isn't important to get the password or email when logged in don't fill it in
     *
     * @param accountID   the ID of the user inside the database
     * @param username    the userName of the account
     * @param gameLibrary library the account has stored inside the database
     */
    public User(int accountID, String username, ArrayList<Game> gameLibrary) {
        this.accountID = accountID;
        this.username = username;
        this.userLibrary = new UserLibrary(gameLibrary);
    }

    public void setUserLibrary(ArrayList<Game> gameLibrary) {
        userLibrary = new UserLibrary(gameLibrary);
    }

    @Override
    public String toString()
    {
        return "%s%s".formatted(username, userLibrary);
    }
}
