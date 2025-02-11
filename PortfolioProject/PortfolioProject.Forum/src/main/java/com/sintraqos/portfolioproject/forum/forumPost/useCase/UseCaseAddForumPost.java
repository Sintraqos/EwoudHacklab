package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
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

    @Autowired
    public UseCaseAddForumPost(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    public ForumPostMessage addForumPost(int accountID, int gameID, String message) {

        if (message.isEmpty()) {
            return new ForumPostMessage("Message was empty");
        }

        return forumPostService.addForumPost(new ForumPostDTO(accountID, gameID, message));
    }
}
