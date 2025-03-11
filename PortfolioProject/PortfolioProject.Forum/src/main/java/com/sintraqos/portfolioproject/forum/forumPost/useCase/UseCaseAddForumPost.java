package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling adding a new forumPost
 */
@Getter
@Component
public class UseCaseAddForumPost {
    private final ForumPostRepository forumPostRepository;
    private final CensorService censorService;
    private final SettingsHandler settingsHandler;
    private final Logger logger;

    @Autowired
    public UseCaseAddForumPost(ForumPostRepository forumPostRepository,
                               CensorService censorService,
                               SettingsHandler settingsHandler, Logger logger) {
        this.forumPostRepository = forumPostRepository;
        this.censorService = censorService;
        this.settingsHandler = settingsHandler;
        this.logger = logger;
    }

    public ForumPostMessage addForumPost(ForumPostDTO forumPost) {
        // Check if the message is a valid length
        int messageLength = forumPost.getMessage().length();

        String baseMessage = "Failed to add message from user with ID: '%s'. Reason: %s";

        // Message too short
        if (messageLength < settingsHandler.getUsernameMinLength()) {
            String message = Errors.FORUM_INVALID_LENGTH_SHORT.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getMessageMaxLength());
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }
        // Message too long
        if (messageLength > settingsHandler.getMessageMaxLength()) {
            String message = Errors.FORUM_INVALID_LENGTH_LONG.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getMessageMaxLength());
            logger.warn(baseMessage.formatted(forumPost.getAccountID(), message));

            return new ForumPostMessage(message);
        }

        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(
                forumPost.getAccountID(),
                forumPost.getGameID(),
                censorService.validateString(forumPost.getMessage()));
        forumPostRepository.save(forumPostEntity);

        logger.debug("Added new message: '%s'".formatted(forumPost.getMessage()));

        return new ForumPostMessage(true, "Added new message: '%s'".formatted(forumPost.getMessage()));
    }
}
