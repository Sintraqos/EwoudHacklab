package com.sintraqos.portfolioproject.ForumPost;

import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;
import org.springframework.data.domain.Page;

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
