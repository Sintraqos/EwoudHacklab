package com.sintraqos.portfolioproject.forumPost.service;

import com.sintraqos.portfolioproject.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.statics.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ForumPostService {

    private final ForumPostRepository forumPostRepository;

    @Autowired
    public ForumPostService(
            ForumPostRepository forumPostRepository
    ) {
        this.forumPostRepository = forumPostRepository;
    }

    /**
     * Add a new game to the database
     *
     * @param forumPostDTO the forumPost to be added
     */
    public ForumPostMessage addForumPost(ForumPostDTO forumPostDTO) {
        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(forumPostDTO.getAccountID(), forumPostDTO.getGameID(), forumPostDTO.getMessage());
        forumPostRepository.save(forumPostEntity);

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
