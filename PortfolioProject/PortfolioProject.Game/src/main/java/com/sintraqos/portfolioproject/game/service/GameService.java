package com.sintraqos.portfolioproject.game.service;

import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.game.useCases.UseCaseAddGame;
import com.sintraqos.portfolioproject.game.useCases.UseCaseGetGame;
import com.sintraqos.portfolioproject.shared.Errors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private Logger logger;
    @Autowired
    private UseCaseGetGame getGame;
    @Autowired
    private UseCaseAddGame addGame;

    /**
     * Add a new game to the database
     * @param gameDTO the game to be added
     */
    public GameEntityMessage addGame(GameDTO gameDTO) {
        return addGame.addGame(new Game(gameDTO));
    }

    public GameEntityMessage addGames(List<GameDTO> games){
        return addGame.addGames(games);
    }

    /**
     * Find a game using an ID
     *
     * @param gameID the ID of the account
     */
    public GameEntityMessage getGame(int gameID) {
        return getGame.getGame(gameID);
    }

    /**
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public GameEntityMessage getGame(String gameName) {
        return getGame.getGame(gameName);
    }

    /**
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public GameEntityMessage getGames(String gameName) {
        return getGame.getGames(gameName);
    }
}
