package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;

import java.util.List;

@Getter
public class GameEntityMessage extends Message {

    private GameEntity entity;
    private List< GameEntity> entities;

    public GameEntityMessage(GameEntity entity, String message) {
        super(true, message);
        this.entity = entity;
    }

    public GameEntityMessage(List< GameEntity> entities, String message) {
        super(true, message);
        this.entities = entities;
    }

    public GameEntityMessage(String message) {
        super(false, message);
    }
}
