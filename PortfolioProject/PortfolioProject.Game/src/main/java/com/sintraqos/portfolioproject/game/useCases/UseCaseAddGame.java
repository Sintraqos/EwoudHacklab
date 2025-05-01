package com.sintraqos.portfolioproject.game.useCases;

// Project components
import com.sintraqos.portfolioproject.game.DAL.*;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.*;
import com.sintraqos.portfolioproject.shared.Errors;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

// Java components
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
            Logger logger) {
        this.getGame = getGame;
        this.gameRepository = gameRepository;
        this.logger = logger;
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public GameMessage addGame(Game game) {
        logger.debug("Attempting to add new game: '%s'".formatted(game.getGameName()));

        // Check if a game with the given name already exists
        if (getGame.getGame(game.getGameName()).isSuccessful()) {
            logger.debug(Errors.GAME_EXISTS.formatted(game.getGameName()));
            return new GameMessage(Errors.GAME_EXISTS.formatted(game.getGameName()));
        }

        // Add new game
        GameEntity gameEntity = new GameEntity(game);
        String message = "Added new game: '%s'".formatted(game.getGameName());
        logger.debug(message);
        return new GameMessage(gameRepository.save(gameEntity), message);
    }

    public GameMessage addGames(List<GameDTO> games){
        StringBuilder returnString = new StringBuilder();

        for (GameDTO game : games) {
            GameMessage message = addGame(new Game(game));

            if (!message.isSuccessful()) {
                logger.debug("Failed to add new game: '%s'".formatted(message.getMessage()));
                returnString.append("\n").append(message.getMessage());
            }
        }

        if (!returnString.isEmpty()) {
            return new GameMessage(returnString.toString());
        } else {
            logger.debug("Added all games successfully");
            return new GameMessage(true, "Added all games successfully");
        }
    }
}
