package com.sintraqos.portfolioproject.Messages;

import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class ForumPostMessage  extends Message {

    private Page<ForumPostEntity> forumPostEntities;
    private int totalPages;

    public ForumPostMessage(Page<ForumPostEntity> forumPostEntities, String message) {
        super(true, message);
        this.forumPostEntities = forumPostEntities;
        totalPages = forumPostEntities.getTotalPages();
    }

    public ForumPostMessage(String message) {
        super(false, message);
    }
}
