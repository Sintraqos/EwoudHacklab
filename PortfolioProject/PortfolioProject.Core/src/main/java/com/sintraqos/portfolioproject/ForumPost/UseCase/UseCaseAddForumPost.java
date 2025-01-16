package com.sintraqos.portfolioproject.ForumPost.UseCase;

import com.sintraqos.portfolioproject.ForumPost.ForumPostDTO;
import com.sintraqos.portfolioproject.ForumPost.ForumPostService;
import com.sintraqos.portfolioproject.Messages.Message;
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
