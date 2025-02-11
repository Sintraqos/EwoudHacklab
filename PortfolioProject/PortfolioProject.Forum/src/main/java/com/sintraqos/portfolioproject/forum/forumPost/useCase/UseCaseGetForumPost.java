package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
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
