package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Game.Game;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Account {
    private final String userName;
    private String eMail;
    private String password;
    private final AccountLibrary gameLibrary;

    // New account object without any games
    public Account(String userName, String eMail, String password) {
        this.userName = userName;
        this.eMail = eMail;
        this.password = password;
        gameLibrary = new AccountLibrary();
    }

    // When importing the account from external locations
    // Since it isn't important to get the password or email when logged in don't fill it in
    public Account(String userName, ArrayList<Game> gameLibrary) {
        this.userName = userName;
        this.gameLibrary = new AccountLibrary(gameLibrary);
    }
}
