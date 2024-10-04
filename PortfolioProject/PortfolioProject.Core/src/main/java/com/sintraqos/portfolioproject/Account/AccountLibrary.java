package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storage and logic for games on the Account object
 */
@Getter
public class AccountLibrary {
    private final ArrayList<Game> gameLibrary;

    /**
     * Create a new AccountLibrary object with a new list
     */
    public AccountLibrary() {
        gameLibrary = new ArrayList<>();
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param gameLibrary the previously created list
     */
    public AccountLibrary(ArrayList<Game> gameLibrary) {
        this.gameLibrary = gameLibrary;
    }

    /**
     * Add a new game to the library, if the game already is in the library don't add it
     *
     * @param game the new game to add to the list
     */
    public void addGame(Game game) {
        // Check if the library already has a game
        if (gameLibrary.contains(game)) {
            Console.writeLine("Game already in library");
            return;
        }

        // Add the game to the library
        gameLibrary.add(new Game(game));
    }
}
