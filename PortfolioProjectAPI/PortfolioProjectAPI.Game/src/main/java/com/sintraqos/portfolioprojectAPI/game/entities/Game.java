package com.sintraqos.portfolioprojectAPI.game.entities;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import lombok.Getter;

import java.util.Date;

/**
 * Use for storing game data
 */
@Getter
public class Game {
    private int gameID;
    private final String gameName;
    private final String gameDescription;
    private final String gameDeveloper;
    private final String gamePublisher;

    public Game(GameDTO game) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
    }

    /**
     * Create a new Game object from incoming data from a database for example
     *
     * @param gameName        the name of the new game
     * @param gameDescription the description of the game
     * @param gameDeveloper   who has created the game
     * @param gamePublisher   who has published the game
     */
    public Game(String gameName, String gameDescription, String gameDeveloper, String gamePublisher) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }
}
