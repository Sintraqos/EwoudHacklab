package com.sintraqos.portfolioproject.game.entities;

// Project components
import com.sintraqos.portfolioproject.game.DAL.GameEntity;

// External components
import lombok.Getter;

// Java components
import java.util.List;

@Getter
public class GameMessage {

    private GameEntity entity;
    private List< GameEntity> entities;
    boolean isSuccessful;
    String message;

    /**
     * Create a new Message
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public GameMessage(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object, always returns false
     *
     * @param message the message the sender wishes to send back
     */
    public GameMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    /**
     * Create a new Message object containing a GameEntity, always returns true
     *
     * @param entity the entity the sender wishes to send back
     * @param message the message the sender wishes to send back
     */
    public GameMessage(GameEntity entity, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.entity = entity;
    }

    /**
     * Create a new Message object containing a list of GameEntity, always returns true
     *
     * @param entities the entities the sender wishes to send back
     * @param message the message the sender wishes to send back
     */
    public GameMessage(List< GameEntity> entities, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.entities = entities;
    }
}
