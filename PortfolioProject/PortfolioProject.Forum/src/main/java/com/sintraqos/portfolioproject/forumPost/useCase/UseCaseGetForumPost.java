package com.sintraqos.portfolioproject.forumPost.useCase;

import com.sintraqos.portfolioproject.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forumPost.service.ForumPostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling retrieving forumPosts
 */
@Getter
@Component
public class UseCaseGetForumPost {
    private final ForumPostService forumPostService;

    @Autowired
    public UseCaseGetForumPost(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
        return forumPostService.getForumPosts_Game(gameID,pageRequest);
    }

    public ForumPostMessage getForumPosts_Account(int accountID,PageRequest pageRequest) {
        return forumPostService.getForumPosts_Account(accountID,pageRequest);
    }
}
