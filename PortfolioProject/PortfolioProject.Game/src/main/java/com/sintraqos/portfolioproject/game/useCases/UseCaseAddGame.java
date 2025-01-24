package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.game.service.GameService;
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
    public GameEntityMessage addGame(Game game) {

        // Add new game
        GameEntityMessage message = gameService.addGame(new GameDTO(game));
        if (!message.isSuccessful()) {
            return new GameEntityMessage("Failed to add game with name: '%s', reason: '%s'".formatted(game.getGameName(), message.getMessage()));
        }
        return message;
    }
}
