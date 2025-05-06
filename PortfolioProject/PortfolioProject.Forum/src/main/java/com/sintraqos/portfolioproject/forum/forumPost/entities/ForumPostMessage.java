package com.sintraqos.portfolioproject.forum.forumPost.entities;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;

// Spring components
import org.springframework.data.domain.Page;

// External components
import lombok.Getter;

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
     * Create a new Message object, always returns false
     *
     * @param message the message the sender wishes to send back
     */
    public ForumPostMessage(String message) {
        this.isSuccessful = false;
        this.message = message;
    }

    /**
     * Create a new Message object containing a list of ForumPostEntity, always returns true
     *
     * @param message the message the sender wishes to send back
     */
    public ForumPostMessage(Page<ForumPostEntity> forumPostEntities, String message) {
        this.isSuccessful = true;
        this.message = message;
        this.forumPostEntities = forumPostEntities;
        totalPages = forumPostEntities.getTotalPages();
    }
}
