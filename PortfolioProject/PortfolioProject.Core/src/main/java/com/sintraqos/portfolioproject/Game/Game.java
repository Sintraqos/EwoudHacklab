package com.sintraqos.portfolioproject.Game;

import lombok.Getter;

import java.time.*;

public class Game {
    @Getter
    private String gameName;
    private LocalDateTime gameAquired;
    private LocalDateTime gameLastPlayed;
    private LocalTime gamePlayTime;
    private String gameDescription;
    private String gameDeveloper;
    private String gamePublisher;

    public Game(String gameName, String gameDescription, String gameDeveloper, String gamePublisher){
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameDeveloper = gameDeveloper;
        this.gamePublisher = gamePublisher;
    }

    public void addGameTime(LocalTime gamePlayTime){
        this.gamePlayTime = gamePlayTime;
    }
}
