package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Time;
import lombok.Getter;

import java.time.*;

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
    private LocalDateTime gameAcquired;
    private LocalDateTime gameLastPlayed;
    private Time gamePlayTime;

    /**
     * Create a new Game object using a base from the game list, use this to store game data inside the accountLibrary
     * @param baseGame the base of the game from the game list
     */
    public Game(Game baseGame){
        this.gameName = baseGame.getGameName();
        this.gameDescription = baseGame.getGameDescription();
        this.gameDeveloper = baseGame.getGameDeveloper();
        this.gamePublisher = baseGame.getGamePublisher();

        this.gameAcquired = LocalDateTime.now();
    }

    /**
     * Create a new Game object from incoming data from a database for example
     * @param gameID the ID the game has in the database
     * @param gameName the name of the new game
     * @param gameDescription the description of the game
     * @param gameDeveloper who has created the game
     * @param gamePublisher who has published the game
     */
    public Game(int gameID,String gameName, String gameDescription, String gameDeveloper, String gamePublisher){
        this.gameID = gameID;
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }

    /**
     * Add specified game time to this Game object
     * @param gamePlayTime how much time there should be added to the current game time
     */
    public void addGameTime(Time gamePlayTime){
        gameLastPlayed = LocalDateTime.now();
        this.gamePlayTime.addTime(gamePlayTime);
    }
}
