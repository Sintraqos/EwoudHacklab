package com.sintraqos.portfolioprojectAPI.game.useCases;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
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
    public GameDTO getGame(int gameID) {
        return new GameDTO(gameService.getGame(gameID));
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
        for(GameEntity game : gameService.getGames(gameName)){
            gameList.add(new GameDTO(game));
        }

        return  gameList;
    }

    public List<GameDTO> getRecentlyAddedGames(){
        List<GameDTO> gameList = new ArrayList<>();
        // TODO: Get all new releases
        for(GameEntity game : gameService.getGames("")){
            gameList.add(new GameDTO(game));
        }


        return gameList;
    }
}
