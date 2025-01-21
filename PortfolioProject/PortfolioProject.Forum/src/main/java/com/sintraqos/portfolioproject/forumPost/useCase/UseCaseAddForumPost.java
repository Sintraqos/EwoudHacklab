package com.sintraqos.portfolioproject.forumPost.useCase;

import com.sintraqos.portfolioproject.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.statics.Message;
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

    public Message addForumPost(int accountID, int gameID, String message) {

        if (message.isEmpty()) {
            return new Message("Message was empty");
        }

        return forumPostService.addForumPost(new ForumPostDTO(accountID, gameID, message));
    }
}
