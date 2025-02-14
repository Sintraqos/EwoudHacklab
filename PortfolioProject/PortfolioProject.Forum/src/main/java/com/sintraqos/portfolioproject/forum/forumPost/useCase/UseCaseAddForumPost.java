package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPost;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling adding a new forumPost
 */
@Getter
@Component
public class UseCaseAddForumPost {
    private final ForumPostService forumPostService;
    private final ForumPostRepository forumPostRepository;
    private final CensorService censorService;
    private final SettingsHandler settingsHandler;

    @Autowired
    public UseCaseAddForumPost(ForumPostService forumPostService,
                               ForumPostRepository forumPostRepository,
                               CensorService censorService,
                               SettingsHandler settingsHandler) {
        this.forumPostService = forumPostService;
        this.forumPostRepository = forumPostRepository;
        this.censorService = censorService;
        this.settingsHandler = settingsHandler;
    }

    public ForumPostMessage addForumPost(ForumPostDTO forumPost) {
        // Check if the message is a valid length
        int messageLength = forumPost.getMessage().length();
        // Message too short
        if (messageLength < settingsHandler.getUsernameMinLength()) {
            return new ForumPostMessage(Errors.FORUM_INVALID_LENGTH_SHORT.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getMessageMaxLength()));
        }
        // Message too long
        if (messageLength > settingsHandler.getMessageMaxLength()) {
            return new ForumPostMessage(Errors.FORUM_INVALID_LENGTH_LONG.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getMessageMaxLength()));
        }

        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(
                forumPost.getAccountID(),
                forumPost.getGameID(),
                censorService.validateString(forumPost.getMessage()));
        forumPostRepository.save(forumPostEntity);

        return new ForumPostMessage(true, "Added new message: '%s'".formatted(forumPost.getMessage()));
    }
}
