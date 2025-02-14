package com.sintraqos.portfolioprojectAPI.game.useCases;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.DAL.GameRepository;
import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.service.GameService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
     * Find a game using an ID
     *
     * @param gameID the ID of the account
     */
    public GameEntity getGame(int gameID) {
        return gameRepository.findByGameID(gameID);
    }

    /**
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public GameEntity getGame(String gameName) {
        return gameRepository.findByGameName(gameName);
    }

    /**
     * Find a game using a name
     *
     * @param gameName the name of the game
     */
    public List<GameEntity> getGames(String gameName) {
        return gameRepository.findByGameNameContaining(gameName);
    }
}
