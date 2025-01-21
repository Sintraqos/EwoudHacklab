package com.sintraqos.portfolioproject.forumPost.entities;

import com.sintraqos.portfolioproject.forumPost.DAL.ForumPostEntity;
import lombok.Getter;
import org.springframework.data.domain.Page;

@Getter
public class ForumPostMessage   {
    boolean isSuccessful;
    String message;
    private Page<ForumPostEntity> forumPostEntities;
    private int totalPages;

    /**
     * Create a new Message object
     *
     * @param isSuccessful if the action was successful
     * @param message the message the sender wishes to send back
     */
    public ForumPostMessage(boolean isSuccessful, String message) {
        this.isSuccessful = isSuccessful;
        this.message = message;
    }

    /**
     * Create a new Message object
     *
     * @param message the message the sender wishes to send back
     */
    public ForumPostMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    public ForumPostMessage(Page<ForumPostEntity> forumPostEntities, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.forumPostEntities = forumPostEntities;
        totalPages = forumPostEntities.getTotalPages();
    }
}
