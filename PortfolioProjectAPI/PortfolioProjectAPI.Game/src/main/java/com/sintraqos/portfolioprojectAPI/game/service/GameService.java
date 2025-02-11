package com.sintraqos.portfolioprojectAPI.game.service;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.DAL.GameRepository;
import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.entities.Game;
import org.instancio.Instancio;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // Add a list of new games when the application is ready
        List<Game> list = Instancio.ofList(Game.class).size(10).create();
        addGames(list);
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

    /**
     * Add a new game to the database
     * @param gameDTO the game to be added
     */
    public boolean addGame(GameDTO gameDTO) {
        // Check if a game with the given name already exists
        if (gameRepository.findByGameName(gameDTO.getGameName()) != null) {
            return false;
        }

        GameEntity game = new GameEntity(gameDTO.getGameName(), gameDTO.getGameDescription(), gameDTO.getGameDeveloper(), gameDTO.getGamePublisher());
        gameRepository.save(game);
        return true;

    }

    /**
     * Add the given list to the database
     */
    public void addGames(List<Game> games) {
        for (Game game : games) {
            addGame(new GameDTO(game));
        }
    }
}
