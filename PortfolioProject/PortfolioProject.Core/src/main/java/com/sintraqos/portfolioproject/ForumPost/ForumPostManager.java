package com.sintraqos.portfolioproject.ForumPost;

import com.sintraqos.portfolioproject.Messages.Message;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * Use for user input handling for all forumPost related scripts
 */
@Getter
@Component
public class ForumPostManager {

    private final ForumPostService forumPostService;

    @Autowired
    public ForumPostManager(ForumPostService forumPostService) {
        this.forumPostService = forumPostService;
    }

    public ForumPostMessage getForumPosts_Game(int gameID,PageRequest pageRequest) {
        return forumPostService.getForumPosts_Game(gameID,pageRequest);
    }

    public ForumPostMessage getForumPosts_Account(int accountID,PageRequest pageRequest) {
        return forumPostService.getForumPosts_Account(accountID,pageRequest);
    }

    public Message addForumPost(int accountID, int gameID, String message) {

        if (message.isEmpty()) {
            return new Message("Message was empty");
        }

        return forumPostService.addForumPost(new ForumPostDTO(accountID, gameID, message));
    }
}
