package com.sintraqos.portfolioprojectAPI.game.useCases;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.DAL.GameRepository;
import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.entities.Game;
import com.sintraqos.portfolioprojectAPI.game.service.GameService;
import lombok.Getter;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling storing a new game
 */
@Getter
@Component
public class UseCaseAddGame {
    private final GameRepository gameRepository;
    private final Logger logger;
    private final Faker faker;

    @Autowired
    public UseCaseAddGame(
            GameRepository gameRepository,
            Logger logger
    ) {
        this.gameRepository = gameRepository;
        this.logger = logger;
        faker = new Faker();
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public boolean addGame(Game game) {
        // Check if a game with the given name already exists
        if (gameRepository.findByGameName(game.getGameName()) != null) {
            return false;
        }

        GameEntity gameEntity = new GameEntity(game.getGameName(), game.getGameDescription(), game.getGameDeveloper(), game.getGamePublisher());
        gameRepository.save(gameEntity);
        return true;
    }
}
