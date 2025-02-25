package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UseCase for getting games from the database
 */
@Getter
@Component
public class UseCaseGetGame {

    private final GameRepository gameRepository;
    private final Logger logger;

    @Autowired
    public UseCaseGetGame(GameRepository gameRepository,
                          Logger logger) {
        this.gameRepository = gameRepository;
        this.logger = logger;
    }

    /**
     * Get a game using its ID
     *
     * @param gameID the ID of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(int gameID) {
        logger.debug("Attempting to get game with ID: '%s'".formatted(gameID));
        // Get the account
        GameEntity game = gameRepository.findByGameID(gameID);

        // If the account was found return the retrieved account
        if (game != null) {
            String message ="Game with ID: '%s' found".formatted(gameID);
            logger.debug(message);

            return new GameEntityMessage(game, message);
        }
        // Otherwise return the message
        else {
            String message =Errors.FIND_GAME_ID_FAILED.formatted(gameID);
            logger.debug(message);

            return new GameEntityMessage(message);
        }
    }

    /**
     * Get a game using its ID
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(String gameName) {
        logger.debug("Attempting to get game with name: '%s'".formatted(gameName));

        // Get the account
        GameEntity game = gameRepository.findByGameName(gameName);

        // If the account was found return the retrieved account
        if (game != null) {
            String message ="Game with name: '%s' found".formatted(gameName);
            logger.debug(message);

            return new GameEntityMessage(game, message);
        }
        // Otherwise return the message
        else {
            String message =Errors.FIND_GAME_NAME_FAILED.formatted(gameName);
            logger.debug(message);

            return new GameEntityMessage(message);
        }
    }

    /**
     * Get all games containing the given name
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGames(String gameName) {
        List<GameEntity> games = gameRepository.findByGameNameContaining(gameName);

        if (games != null) {
            String message ="Games containing: '%s' found".formatted(gameName);
            logger.debug(message);

            return new GameEntityMessage(games, message);
        } else {
            String message =Errors.FIND_GAME_NAME_FAILED.formatted(gameName);
            logger.debug(message);

            return new GameEntityMessage(message);
        }
    }
}
