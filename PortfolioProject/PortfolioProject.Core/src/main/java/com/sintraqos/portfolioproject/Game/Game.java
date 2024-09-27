package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Time;
import lombok.Getter;

import java.time.*;

public class Game {
    @Getter
    private int gameID;
    @Getter
    private String gameName;
    @Getter
    private String gameDescription;
    @Getter
    private String gameDeveloper;
    @Getter
    private String gamePublisher;
    @Getter
    private LocalDateTime gameAcquired;
    @Getter
    private LocalDateTime gameLastPlayed;
    @Getter
    private Time gamePlayTime;

    /**
     * Create a new Game object using a base from the game list
     * @param baseGame the base of the game from the game list
     * @param gameAcquired the date of when the user acquired the game
     * @param gameLastPlayed when was the last time the user has played the game
     * @param gamePlayTime how much time has the user spend playing the game
    */
    public Game(Game baseGame, LocalDateTime gameAcquired, LocalDateTime gameLastPlayed, Time gamePlayTime){
        this.gameName = baseGame.getGameName();
        this.gameDescription = baseGame.getGameDescription();
        this.gameDeveloper = baseGame.getGameDeveloper();
        this.gamePublisher = baseGame.getGamePublisher();

        this.gameAcquired = gameAcquired;
        this.gameLastPlayed = gameLastPlayed;
        this.gamePlayTime = gamePlayTime;
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
        this.gamePlayTime.addTime(gamePlayTime);
    }
}
