package com.sintraqos.portfolioproject.forum.forumPost.useCase;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.*;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.shared.*;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

/**
 * UseCase for handling adding a new forumPost
 */
@Getter
@Component
public class UseCaseAddForumPost {
    private final ForumPostRepository forumPostRepository;
    private final UseCaseValidateForumPost validateForumPost;
    private final CensorService censorService;
    private final SettingsHandler settingsHandler;
    private final Logger logger;

    @Autowired
    public UseCaseAddForumPost(ForumPostRepository forumPostRepository,
                               CensorService censorService,
                               UseCaseValidateForumPost validateForumPost,
                               SettingsHandler settingsHandler,
                               Logger logger) {
        this.forumPostRepository = forumPostRepository;
        this.censorService = censorService;
        this.validateForumPost = validateForumPost;
        this.settingsHandler = settingsHandler;
        this.logger = logger;
    }

    public ForumPostMessage addForumPost(ForumPostDTO forumPost) {
        ForumPostMessage validatedForumPost = validateForumPost.validateForumPost(forumPost);

        // Check if the forum post was validated
        if (!validatedForumPost.isSuccessful()) {
            logger.warn(forumPost.getMessage());
            return validatedForumPost;
        }

        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(
                forumPost.getAccountID(),
                forumPost.getGameID(),
                validatedForumPost.getMessage());
        forumPostEntity = forumPostRepository.save(forumPostEntity);

        // Check if the save was successful
        if (forumPostEntity.getForumPostID() >= 0) {
            String message = "Added new message: '%s'".formatted(forumPost.getMessage());
            logger.debug(message);
            return new ForumPostMessage(true, "Added new message: '%s'".formatted(forumPost.getMessage()));
        } else {
            logger.debug(forumPost.getMessage());
            return new ForumPostMessage(forumPost.getMessage());
        }
    }
}
