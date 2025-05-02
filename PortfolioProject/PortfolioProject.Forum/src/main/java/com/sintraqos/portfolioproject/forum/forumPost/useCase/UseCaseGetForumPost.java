package com.sintraqos.portfolioproject.forum.forumPost.useCase;

// Project components
import com.sintraqos.portfolioproject.forum.forumPost.DAL.*;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.Errors;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

/**
 * UseCase for handling retrieving forumPosts
 */
@Getter
@Component
public class UseCaseGetForumPost {
    private final ForumPostRepository forumPostRepository;
    private final Logger logger;

    @Autowired
    public UseCaseGetForumPost(ForumPostRepository forumPostRepository,
                               Logger logger) {
        this.forumPostRepository = forumPostRepository;
        this.logger = logger;
    }

    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
        logger.debug("Attempting to get all forum posts from game");
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_GAME_ID_FAILED.formatted(gameID);
            logger.debug(message);

            return new ForumPostMessage(message);
        }

        String message="Game's forum posts found";
        logger.debug(message);
        return new ForumPostMessage(forumPostEntities, message);
    }

    public ForumPostMessage getForumPosts_Account(int accountID,PageRequest pageRequest) {
        logger.debug("Attempting to get all forum posts from account");
        Page<ForumPostEntity> forumPostEntities = (Page<ForumPostEntity>) forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_USER_ID_FAILED.formatted(accountID);
            logger.debug(message);

            return new ForumPostMessage(message);
        }

        String message="Account's forum posts found";
        logger.debug(message);
        return new ForumPostMessage(forumPostEntities, message);
    }
}
