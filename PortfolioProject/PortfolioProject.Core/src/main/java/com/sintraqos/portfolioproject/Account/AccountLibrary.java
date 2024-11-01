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
    private ArrayList<Game> gameLibrary = new ArrayList<>();

    /**
     * Create a new AccountLibrary object
     */
    public AccountLibrary() {
        gameLibrary = new ArrayList<>();
    }

    public AccountLibrary(AccountLibraryDTO accountLibraryDTO) {
        for (GameDTO gameDTO : accountLibraryDTO.getGameLibrary()) {
            gameLibrary.add(new Game(gameDTO));
        }
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
    public void addGame(GameDTO game) {
        // Check if the library already has a game
        if (gameLibrary.contains(game)) {
            Console.writeLine("Game: %s already in library".formatted(game.getGameName()));
            return;
        }

        // Add the game to the library
        gameLibrary.add(new Game(game));
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (Game game : gameLibrary) {
            result.append("\n%s: %s - %s".formatted(
                    game.getGameID(),
                    game.getGameName(),
                    game.getGamePlayTime().toString()));
        }

        return result.toString();
    }
}
