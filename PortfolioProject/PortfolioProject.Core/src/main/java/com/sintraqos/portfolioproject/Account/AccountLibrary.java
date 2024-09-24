package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Logger.Console;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AccountLibrary {
    private final ArrayList<Game> gameLibrary;

    public AccountLibrary() {
        gameLibrary = new ArrayList<>();
    }

    public AccountLibrary(ArrayList<Game> gameLibrary) {
        this.gameLibrary = gameLibrary;
    }

    public void addGame(Game game) {
        // Check if the library already has a game
        if (gameLibrary.contains(game)) {
            Console.writeLine("Game already in library");
            return;
        }

        // Add the game to the library
        gameLibrary.add(game);
    }
}
