package com.sintraqos.portfolioproject.game.entities;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import lombok.Getter;

import java.util.Date;

/**
 * Use for storing game data
 */
@Getter
public class Game {
    private int gameID;
    private String gameName;
    private String gameDescription;
    private String gameDeveloper;
    private String gamePublisher;
    private Date gameAcquired;
    private Date gameLastPlayed;
    private int gamePlayTime;

    /**
     * Create a new Game object using a base from the game list, use this to store game data inside the accountLibrary
     *
     * @param baseGame the base of the game from the game list
     */
    public Game(Game baseGame) {
        this.gameID = baseGame.getGameID();
        this.gameName = baseGame.getGameName();
        this.gameDescription = baseGame.getGameDescription();
        this.gameDeveloper = baseGame.getGameDeveloper();
        this.gamePublisher = baseGame.getGamePublisher();
        gamePlayTime = 0;
    }

    public Game(GameDTO game) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
        this.gameAcquired = game.getGameAcquired();
        this.gameLastPlayed = game.getGameLastPlayed();
        this.gamePlayTime = game.getGamePlayTime();
    }

    public Game(GameDTO game, Date gameAcquired, Date gameLastPlayed, int gamePlayTime) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
        this.gameAcquired = gameAcquired;
        this.gameLastPlayed = gameLastPlayed;
        this.gamePlayTime = gamePlayTime;
    }

    public Game(GameEntity game, Date gameAcquired, Date gameLastPlayed, int gamePlayTime) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
        this.gameAcquired = gameAcquired;
        this.gameLastPlayed = gameLastPlayed;
        this.gamePlayTime = gamePlayTime;
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
