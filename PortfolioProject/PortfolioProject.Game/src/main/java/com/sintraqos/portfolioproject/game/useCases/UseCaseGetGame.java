package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
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

    @Autowired
    public UseCaseGetGame(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * Get a game using its ID
     *
     * @param gameID the ID of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(int gameID) {
        // Get the account
        GameEntity game = gameRepository.findByGameID(gameID);

        // If the account was found return the retrieved account
        if (game != null) {
            return new GameEntityMessage(game, "Game found");
        }
        // Otherwise return the message
        else {
            return new GameEntityMessage(Errors.FIND_GAME_ID_FAILED.formatted(gameID));
        }
    }

    /**
     * Get a game using its ID
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(String gameName) {
        // Get the account
        GameEntity game = gameRepository.findByGameName(gameName);

        // If the account was found return the retrieved account
        if (game != null) {
            return new GameEntityMessage(game, "Game found");
        }
        // Otherwise return the message
        else {
            return new GameEntityMessage(Errors.FIND_GAME_NAME_FAILED.formatted(gameName));
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
            return new GameEntityMessage(games, "Games found");
        } else {
            return new GameEntityMessage(Errors.FIND_GAME_NAME_FAILED.formatted(gameName));
        }
    }
}
