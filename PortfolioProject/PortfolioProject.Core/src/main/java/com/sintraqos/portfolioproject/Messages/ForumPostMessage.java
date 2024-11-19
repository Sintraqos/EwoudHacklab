package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import com.sintraqos.portfolioproject.Entities.GameEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class ForumPostMessage  extends Message {

    private List<ForumPostEntity> forumPostEntities;

    public ForumPostMessage(List<ForumPostEntity> forumPostEntities, String message) {
        super(true, message);
        this.forumPostEntities = forumPostEntities;
    }

    public ForumPostMessage(String message) {
        super(false, message);
    }
}
