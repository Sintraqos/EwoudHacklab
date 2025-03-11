package com.sintraqos.portfolioproject.game.entities;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class GameEntityMessage {

    private GameEntity entity;
    private List< GameEntity> entities;
    boolean isSuccessful;
    String message;

    /**
     * Create a new Message object
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public GameEntityMessage(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public GameEntityMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param entity the entity the sender wishes to send back
     * @param message the message the sender wishes to send back
     */
    public GameEntityMessage(GameEntity entity, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.entity = entity;
    }

    public GameEntityMessage(List< GameEntity> entities, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.entities = entities;
    }
}
