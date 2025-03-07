package com.sintraqos.portfolioproject.userLibrary.entities;

import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.userLibrary.DTO.UserLibraryDTO;
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
                    game.getGamePlayTime()));
        }

        return result.toString();
    }
}
