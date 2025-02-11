package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.scheduler.ScheduleEventHandler;
import lombok.Getter;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UseCase for handling storing a new game
 */
@Getter
@Component
public class UseCaseAddGame {
    private final GameService gameService;
    private final Logger logger;
    private final Faker faker;

    @Autowired
    public UseCaseAddGame(
            GameService gameService,
            Logger logger
    ) {
        this.gameService = gameService;
        this.logger = logger;
        faker = new Faker();
    }

    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public GameEntityMessage addGame(Game game) {

        // Add new game
        GameEntityMessage message = gameService.addGame(new GameDTO(game));
        if (!message.isSuccessful()) {
            return new GameEntityMessage("Failed to add game with name: '%s', reason: '%s'".formatted(game.getGameName(), message.getMessage()));
        }
        return message;
    }

    /**
     * When a schedule event gets invoked, retrieve games from the API
     */
    @EventListener
    public void handleScheduleTickEvent(ScheduleEventHandler event) {
        logger.info("Handling ScheduleTickEvent");
        // TODO: Retrieve game information from the API, then add those games.
//        List<Game> list = Instancio.ofList(Game.class).size(10).create();
//        GameEntityMessage addGameMessage = gameService.addGames(list);
//        if(!addGameMessage.isSuccessful()){
//            logger.warn("Warning: {}", addGameMessage.getMessage());
//        }
    }
}
