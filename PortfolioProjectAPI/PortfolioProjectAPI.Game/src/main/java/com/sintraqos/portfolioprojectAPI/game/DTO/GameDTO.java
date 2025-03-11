package com.sintraqos.portfolioprojectAPI.game.DTO;

import com.sintraqos.portfolioprojectAPI.game.DAL.GameEntity;
import com.sintraqos.portfolioprojectAPI.game.entities.Game;
import lombok.Getter;

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

    public GameDTO(Game game)
    {
        this.gameID = game.getGameID();
        this.gameName = game.getGameName();
        this.gameDescription = game.getGameDescription();
        this.gameDeveloper = game.getGameDeveloper();
        this.gamePublisher = game.getGamePublisher();
    }

    public GameDTO(GameEntity game) {
        if (game == null) {
            this.gameID = -1;
            this.gameName = "";
            this.gameDescription = "";
            this.gameDeveloper = "";
            this.gamePublisher = "";
        } else {
            this.gameID = game.getGameID();
            this.gameName = game.getGameName();
            this.gameDescription = game.getGameDescription();
            this.gameDeveloper = game.getGameDeveloper();
            this.gamePublisher = game.getGamePublisher();
        }
    }

    @Override
    public String toString(){
        return "%s: %s".formatted(gameID, gameName);
    }
}
