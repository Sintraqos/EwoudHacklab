package com.sintraqos.portfolioproject.game.service;

// Project components
import com.sintraqos.portfolioproject.game.entities.*;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.useCases.*;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Java components
import java.util.List;

@Service
public class GameService {

    @Autowired
    private UseCaseGetGame getGame;
    @Autowired
    private UseCaseAddGame addGame;

    /**
     * Add a new game to the database
     * @param gameDTO the game to be added
     */
    public GameMessage addGame(GameDTO gameDTO) {
        return addGame.addGame(new Game(gameDTO));
    }

    public GameMessage addGames(List<GameDTO> games){
        return addGame.addGames(games);
    }

    /**
     * Find a game using an ID
     *
     * @param gameID the ID of the account
     */
    public GameMessage getGame(int gameID) {
        return getGame.getGame(gameID);
    }

    /**
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public GameMessage getGames(String gameName) {
        return getGame.getGames(gameName);
    }
}
