package com.sintraqos.portfolioproject.forum.forumPost.service;

import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.useCase.UseCaseAddForumPost;
import com.sintraqos.portfolioproject.forum.forumPost.useCase.UseCaseGetForumPost;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ForumPostService {
    private final UseCaseAddForumPost addForumPost;
    private final UseCaseGetForumPost getForumPost;
    private final Logger logger;

    @Autowired
    public ForumPostService(
            UseCaseAddForumPost addForumPost,
            UseCaseGetForumPost getForumPost,
            Logger logger) {
        this.addForumPost = addForumPost;
        this.getForumPost = getForumPost;
        this.logger = logger;
    }

    /**
     * Add a new game to the database
     *
     * @param accountID the ID of the account which sent the post
     * @param gameID    the ID of the game under which the post was sent
     * @param message   the message of the post
     */
    public ForumPostMessage addForumPost(int accountID, int gameID, String message) {
        if (message.isEmpty()) {
            return new ForumPostMessage("Message was empty");
        }
        return  addForumPost.addForumPost(new ForumPostDTO(accountID, gameID, message));
    }

    /**
     * Find all forumPosts using a GameID
     *
     * @param gameID the ID of the game
     */
    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
        return getForumPost.getForumPosts_Game(gameID, pageRequest);
    }

    /**
     * Find all forumPosts using their UserID
     *
     * @param accountID the ID of the account
     */
    public ForumPostMessage getForumPosts_Account(int accountID, PageRequest pageRequest) {
        return getForumPost.getForumPosts_Account(accountID, pageRequest);
    }
}
