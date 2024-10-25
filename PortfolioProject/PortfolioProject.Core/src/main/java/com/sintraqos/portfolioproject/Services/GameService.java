package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Entities.GameEntity;
import com.sintraqos.portfolioproject.Messages.AccountEntityMessage;
import com.sintraqos.portfolioproject.Messages.GameEntityMessage;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

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
     * Find a game using an ID
     *
     * @param gameName the ID of the account
     */
    public GameEntityMessage getGame(String gameName) {
        // Get the account
        GameEntity game = gameRepository.findGameByName(gameName);

        // If the account was found return the retrieved account
        if (game != null) {
            return new GameEntityMessage(game, "Game found");
        }
        // Otherwise return the message
        else {
            return new GameEntityMessage("Failed to find game by game ID: '%s' found".formatted(gameName));
        }
    }
}
