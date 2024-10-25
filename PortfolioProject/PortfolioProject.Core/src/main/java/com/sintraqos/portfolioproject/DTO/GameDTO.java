package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Entities.GameEntity;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Time;
import lombok.Getter;
import java.util.Date;

/**
 * Game DTO, use for transfer of game data
 */
@Getter
public class GameDTO {
    private int gameID;
    private String gameName;
    private String gameDescription;
    private String gameDeveloper;
    private String gamePublisher;
    private Date gameAcquired;
    private Date gameLastPlayed;
    private Time gamePlayTime;

    public GameDTO(Game game) {
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
        this.gameAcquired = game.getGameAcquired();
        this.gameLastPlayed = game.getGameLastPlayed();
        this.gamePlayTime = game.getGamePlayTime();
    }

    public GameDTO(GameEntity game) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
    }
}
