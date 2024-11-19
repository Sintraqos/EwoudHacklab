package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Messages.GameEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.GameService;
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

    private final GameService gameService;

    @Autowired
    public GameManager(GameService gameService){
        this.gameService = gameService;
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param gameID the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(int gameID) {
        return gameService.getGame(gameID);
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
        }
            return message;
    }
}
