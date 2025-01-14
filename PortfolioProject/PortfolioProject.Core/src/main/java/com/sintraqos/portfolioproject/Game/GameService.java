package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    /**
     * Add a new game to the database
     * @param gameDTO the game to be added
     */
    public GameEntityMessage addGame(GameDTO gameDTO) {
        // Check if a game with the given name already exists
        if (gameRepository.findByGameName(gameDTO.getGameName()) != null) {
            return new GameEntityMessage(Errors.GAME_EXISTS.formatted(gameDTO.getGameName()));
        }

        GameEntity game = new GameEntity(gameDTO.getGameName(), gameDTO.getGameDescription(), gameDTO.getGameDeveloper(), gameDTO.getGamePublisher());
        return new GameEntityMessage(gameRepository.save(game), "Added new game: '%s'".formatted(gameDTO.getGameName()));
    }

    /**
     * Find a game using an ID
     *
     * @param gameID the ID of the account
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
     * Find a game using a name
     *
     * @param gameName the name of the game
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
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public GameEntityMessage getGames(String gameName) {
       List<GameEntity> games = gameRepository.findByGameNameContaining(gameName);

        if (games != null) {
            return new GameEntityMessage(games, "Games found");
        }
        else {
            return new GameEntityMessage(Errors.FIND_GAME_NAME_FAILED.formatted(gameName));
        }
    }
}
