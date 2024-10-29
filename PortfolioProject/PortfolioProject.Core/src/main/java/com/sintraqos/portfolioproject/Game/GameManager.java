package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Messages.AccountEntityMessage;
import com.sintraqos.portfolioproject.Messages.GameEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.GameService;
import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Use for user input handling for all game related scripts
 */
@Getter
@Component
public class GameManager {

    private final ArrayList<Game> gameLibrary = new ArrayList<>();

    @Autowired
    private GameService gameService;

    /**
     * Create a new Game object using a base from the game list
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameDTO getGame(String gameName) {
        return new GameDTO(gameService.getGame(gameName).getEntity());
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public Message addGame(Game game) {

        // Add new game
        GameEntityMessage message = gameService.addGame(new GameDTO(game));
        if (!message.isSuccessful()) {
            return new Message("Failed to add game with name: '%s', reason: '%s'".formatted(game.getGameName(), message.getMessage()));
        } else {
            gameLibrary.add(game);
            return message;
        }
    }

    public ArrayList<Game> getGameLibrary() {
        return new ArrayList<>();
    }
}
