package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
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

    public GameDTO(AccountLibraryEntity libraryEntity, GameDTO gameDTO) {
        this.gameID = libraryEntity.getGameID();
        this.gameName = gameDTO.getGameName();
        this.gameDescription = gameDTO.getGameDescription();
        this.gameDeveloper = gameDTO.getGameDeveloper();
        this.gamePublisher = gameDTO.getGamePublisher();
        this.gameAcquired = libraryEntity.getGameAcquired();
        this.gameLastPlayed = libraryEntity.getGameLastPlayed();
        this.gamePlayTime = new Time(libraryEntity.getGamePlayTime());
    }

    public GameDTO(GameEntity game) {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
        this.gamePlayTime = new Time();
    }

    @Override
    public String toString(){
        return "%s: %s - %s".formatted(gameID, gameName, gamePlayTime);
    }
}
