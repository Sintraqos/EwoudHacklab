package com.sintraqos.portfolioproject.Game.UseCase;

import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameDTO;
import com.sintraqos.portfolioproject.Game.GameEntityMessage;
import com.sintraqos.portfolioproject.Game.GameService;
import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling storing a new game
 */
@Getter
@Component
public class UseCaseAddGame {
    private final GameService gameService;

    @Autowired
    public UseCaseAddGame(GameService gameService) {
        this.gameService = gameService;
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
