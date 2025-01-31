package com.sintraqos.portfolioproject.forumPost.service;

import com.sintraqos.portfolioproject.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ForumPostService {

    private final ForumPostRepository forumPostRepository;
    private final CensorService censorService;
    private final SettingsHandler settingsHandler;
    private final Logger logger;

    @Autowired
    public ForumPostService(
            ForumPostRepository forumPostRepository,
            CensorService censorService,
            SettingsHandler settingsHandler,
            Logger logger) {
        this.forumPostRepository = forumPostRepository;
        this.censorService = censorService;
        this.settingsHandler = settingsHandler;
        this.logger = logger;
    }

    /**
     * Add a new game to the database
     *
     * @param forumPostDTO the forumPost to be added
     */
    public ForumPostMessage addForumPost(ForumPostDTO forumPostDTO) {
        // Check if the message is a valid length
        int messageLength = forumPostDTO.getMessage().length();
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
                forumPostDTO.getAccountID(),
                forumPostDTO.getGameID(),
                censorService.validateString(forumPostDTO.getMessage()));
        forumPostRepository.save(forumPostEntity);

        logger.info("New message added");

        return new ForumPostMessage(true, "Added new message: '%s'".formatted(forumPostDTO.getMessage()));
    }

    /**
     * Find all forumPosts using a GameID
     *
     * @param gameID the ID of the game
     */
    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_GAME_ID_FAILED.formatted(gameID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }

    /**
     * Find all forumPosts using their UserID
     *
     * @param accountID the ID of the account
     */
    public ForumPostMessage getForumPosts_Account(int accountID, PageRequest pageRequest) {
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_ACCOUNT_ID_FAILED.formatted(accountID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }
}
