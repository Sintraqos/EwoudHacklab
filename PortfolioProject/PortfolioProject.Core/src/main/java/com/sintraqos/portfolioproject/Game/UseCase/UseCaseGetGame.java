package com.sintraqos.portfolioproject.Game.UseCase;

import com.sintraqos.portfolioproject.Game.GameEntityMessage;
import com.sintraqos.portfolioproject.Game.GameService;
import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for getting games from the database
 */
@Getter
@Component
public class UseCaseGetGame {
    private final GameService gameService;

    @Autowired
    public UseCaseGetGame(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * Get a game using its ID
     *
     * @param gameID the ID of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGame(int gameID) {
        return gameService.getGame(gameID);
    }

    /**
     * Get all games containing the given name
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameEntityMessage getGames(String gameName) {
        return gameService.getGames(gameName);
    }
}
