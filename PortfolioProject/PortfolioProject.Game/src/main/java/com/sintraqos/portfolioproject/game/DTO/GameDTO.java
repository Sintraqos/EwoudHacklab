package com.sintraqos.portfolioproject.game.DTO;

import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import lombok.Getter;

import java.util.Date;

/**
 * Game DTO, use for transfer of game data
 */
@Getter
public class GameDTO {
    private final int gameID;
    private final String gameName;
    private final String gameDescription;
    private final String gameDeveloper;
    private final String gamePublisher;
    private Date gameAcquired;
    private Date gameLastPlayed;
    private final int gamePlayTime;

    public GameDTO(){
        this.gameID = 0;
        this.gameName = "";
        this.gameDescription = "";
        this.gameDeveloper = "";
        this.gamePublisher = "";
        this.gameAcquired = null;
        this.gameLastPlayed = null;
        this.gamePlayTime = 0;
    }

    public GameDTO(Game game)
    {
        this.gameID = game.getGameID();
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
        this.gamePlayTime = 0;
    }

    public GameDTO(
            GameEntity gameEntity,
            Date gameAcquired,
            Date gameLastPlayed,
            int gamePlayTime
    ){
        this.gameID = gameEntity.getGameID();
        this.gameName = gameEntity.getGameName();
        this.gameDescription = gameEntity.getGameDescription();
        this.gameDeveloper = gameEntity.getGameDeveloper();
        this.gamePublisher = gameEntity.getGamePublisher();
        this.gameAcquired = gameAcquired;
        this.gameLastPlayed = gameLastPlayed;
        this.gamePlayTime = gamePlayTime;
    }

    @Override
    public String toString(){
        return "%s: %s - %s".formatted(gameID, gameName, gamePlayTime);
    }
}
