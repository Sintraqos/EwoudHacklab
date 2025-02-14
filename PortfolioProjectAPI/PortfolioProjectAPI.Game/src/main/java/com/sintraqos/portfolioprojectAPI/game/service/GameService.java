package com.sintraqos.portfolioprojectAPI.game.service;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.DAL.GameRepository;
import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.entities.Game;
import com.sintraqos.portfolioprojectAPI.game.useCases.UseCaseAddGame;
import com.sintraqos.portfolioprojectAPI.game.useCases.UseCaseGetGame;
import org.instancio.Instancio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final UseCaseAddGame addGame;
    private final UseCaseGetGame getGame;

    @Autowired
    public GameService(GameRepository gameRepository,
                       UseCaseGetGame getGame,
                       UseCaseAddGame addGame) {
        this.gameRepository = gameRepository;
        this.getGame = getGame;
        this.addGame = addGame;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // Add a list of new games when the application is ready
        List<Game> list = Instancio.ofList(Game.class).size(100).create();
        addGames(list);
    }

    /**
     * Get a game using its ID
     *
     * @param gameID the ID of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public GameDTO getGame(int gameID) {
        return new GameDTO(getGame.getGame(gameID));
    }

    /**
     * Get all games containing the given name
     *
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public List<GameDTO> getGames(String gameName) {
        List<GameDTO> gameList = new ArrayList<>();
        // TODO: Get all new releases
        for(GameEntity game : getGame.getGames(gameName)){
            gameList.add(new GameDTO(game));
        }

        return  gameList;
    }

    public List<GameDTO> getRecentlyAddedGames(){
        List<GameDTO> gameList = new ArrayList<>();
        // TODO: Get all new releases
        for(GameEntity game : getGame.getGames("")){
            gameList.add(new GameDTO(game));
        }


        return gameList;
    }

    /**
     * Add a new game to the database
     *
     * @param gameDTO the game to be added
     */
    public boolean addGame(GameDTO gameDTO) {
        return addGame.addGame(new Game(gameDTO));
    }

    /**
     * Add the given list to the database
     */
    public void addGames(List<Game> games) {
        for (Game game : games) {
            addGame.addGame(game);
        }
    }
}
