package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
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
        this.gamePlayTime = new Time();
    }

    public GameDTO(UserLibraryEntity libraryEntity, GameEntity gameEntity) {
        this.gameID = libraryEntity.getGameID();
        this.gameName = gameEntity.getGameName();
        this.gameDescription = gameEntity.getGameDescription();
        this.gameDeveloper = gameEntity.getGameDeveloper();
        this.gamePublisher = gameEntity.getGamePublisher();
        this.gameAcquired = libraryEntity.getGameAcquired();
        this.gameLastPlayed = libraryEntity.getGameLastPlayed();
        this.gamePlayTime = new Time(libraryEntity.getGamePlayTime());
    }

    @Override
    public String toString(){
        return "%s: %s - %s".formatted(gameID, gameName, gamePlayTime);
    }
}
