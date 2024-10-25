package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.GameEntity;
import lombok.Getter;

@Getter
public class GameEntityMessage extends Message {

    private GameEntity entity;

    public GameEntityMessage(GameEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }

    public GameEntityMessage(GameEntity entity, boolean isSuccessful, String message) {
        super(isSuccessful, message);
        this.entity = entity;
    }

    public GameEntityMessage(String message) {
        super(false, message);
    }
}
