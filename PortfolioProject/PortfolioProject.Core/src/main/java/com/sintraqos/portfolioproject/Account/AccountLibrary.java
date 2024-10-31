package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.DTO.AccountLibraryDTO;
import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storage and logic for games on the Account object
 */
@Getter
public class AccountLibrary {
    private final ArrayList<GameDTO> gameLibrary;

    /**
     * Create a new AccountLibrary object with a new list
     */
    public AccountLibrary() {
        gameLibrary = new ArrayList<>();
    }

    public AccountLibrary(AccountLibraryDTO accountLibraryDTO)
    {
        this.gameLibrary = accountLibraryDTO.getGameLibrary();
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param gameLibrary the previously created list
     */
    public AccountLibrary(ArrayList<GameDTO> gameLibrary) {
        this.gameLibrary = gameLibrary;
    }

    /**
     * Add a new game to the library, if the game already is in the library don't add it
     *
     * @param game the new game to add to the list
     */
    public void addGame(GameDTO game) {
        // Check if the library already has a game
        if (gameLibrary.contains(game)) {
            Console.writeLine("Game: %s already in library".formatted(game.getGameName()));
            return;
        }

        // Add the game to the library
        gameLibrary.add(game);
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder("Game Library:\n");
        for (GameDTO gameDTO : gameLibrary) {
            result.append("%s: %s - %s".formatted(gameDTO.getGameID(), gameDTO.getGameName(), gameDTO.getGamePlayTime().toString())).append("\n"); // Append each item and a newline
        }

        return result.toString();
    }
}
