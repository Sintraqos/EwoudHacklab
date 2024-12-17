package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.DTO.UserLibraryDTO;
import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Use for storage and logic for games on the Account object
 */
@Getter
public class UserLibrary {
    private ArrayList<Game> gameLibrary = new ArrayList<>();

    /**
     * Create a new AccountLibrary object
     */
    public UserLibrary() {
        gameLibrary = new ArrayList<>();
    }

    public UserLibrary(UserLibraryDTO userLibraryDTO) {
        for (GameDTO gameDTO : userLibraryDTO.getGameLibrary()) {
            gameLibrary.add(new Game(gameDTO));
        }
    }

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param gameLibrary the previously created list
     */
    public UserLibrary(ArrayList<Game> gameLibrary) {
        this.gameLibrary = gameLibrary;
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
