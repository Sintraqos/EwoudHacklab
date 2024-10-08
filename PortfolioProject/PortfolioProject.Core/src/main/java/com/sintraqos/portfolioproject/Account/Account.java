package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameController;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storing account data
 */
@Getter
public class Account {
    private int accountID = -1; // Default value for check if the database needs to assign a new ID
    private String userName;
    private String eMail;
    private String password;
    private AccountLibrary accountLibrary;

    /**
     * Create a new account object without any games
     *
     * @param userName the userName of the account
     * @param password the password of the account
     */
    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
        accountLibrary = new AccountLibrary();
        accountLibrary.addGame(new Game(
                69420,
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in the kingdom of Hyrule.",
                "Nintendo EPD",
                "Nintendo"));
    }

    /**
     * Create a new account object without any games
     *
     * @param userName the userName of the account
     * @param eMail    the e-mail of the account
     * @param password the password of the account
     */
    public Account(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        this.accountLibrary = new AccountLibrary();
        this.accountLibrary.addGame(new Game(
                69420,
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in the kingdom of Hyrule.",
                "Nintendo EPD",
                "Nintendo"));
    }

    /**
     * When importing the account from external location.
     * Since it isn't important to get the password or email when logged in don't fill it in
     *
     * @param accountID   the ID of the user inside the database
     * @param userName    the userName of the account
     * @param gameLibrary library the account has stored inside the database
     */
    public Account(int accountID, String userName, ArrayList<Game> gameLibrary) {
        this.accountID = accountID;
        this.userName = userName;
        this.accountLibrary = new AccountLibrary(gameLibrary);
        this.accountLibrary.addGame(new Game(
                69420,
                "The Legend of Zelda: Breath of the Wild",
                "An open-world action-adventure game set in the kingdom of Hyrule.",
                "Nintendo EPD",
                "Nintendo"));
    }
}
