package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UseCase for handling storing a new game
 */
@Getter
@Component
public class UseCaseAddGame {
    private final UseCaseGetGame getGame;
    private final GameRepository gameRepository;
    private final Logger logger;

    @Autowired
    public UseCaseAddGame(
            UseCaseGetGame getGame,
            GameRepository gameRepository,
            Logger logger
    ) {
        this.getGame = getGame;
        this.gameRepository = gameRepository;
        this.logger = logger;
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public GameEntityMessage addGame(Game game) {
        // Check if a game with the given name already exists
        if (getGame.getGames(game.getGameName()).isSuccessful()) {
            return new GameEntityMessage(Errors.GAME_EXISTS.formatted(game.getGameName()));
        }

        // Add new game
        GameEntity gameEntity = new GameEntity(game);
        return new GameEntityMessage(gameRepository.save(gameEntity), "Added new game: '%s'".formatted(game.getGameName()));
    }

    public GameEntityMessage addGames(List<GameDTO> games){
        StringBuilder returnString = new StringBuilder();

        for (GameDTO game : games) {
            GameEntityMessage message = addGame(new Game(game));

            if (!message.isSuccessful()) {
                returnString.append("\n").append(message.getMessage());
            }
        }

        if (!returnString.isEmpty()) {
            return new GameEntityMessage(returnString.toString());
        } else {
            return new GameEntityMessage(true, "Added all games successfully");
        }
    }
}
