package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Entities.GameEntity;
import com.sintraqos.portfolioproject.Messages.GameEntityMessage;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return new GameEntityMessage("Game already exists");
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
            return new GameEntityMessage("Failed to find game by game ID: '%s' found".formatted(gameID));
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
            return new GameEntityMessage("Failed to find game by name: '%s' found".formatted(gameName));
        }
    }
}
