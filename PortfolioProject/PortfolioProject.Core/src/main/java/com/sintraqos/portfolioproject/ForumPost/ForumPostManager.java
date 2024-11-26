package com.sintraqos.portfolioproject.ForumPost;

import com.sintraqos.portfolioproject.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.Messages.ForumPostMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Services.ForumPostService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

/**
 * Use for user input handling for all forumPost related scripts
 */
@Getter
@Component
public class ForumPostManager {

    private final ForumPostService forumPostService;

    @Autowired
    public ForumPostManager(ForumPostService forumPostService){
        this.forumPostService = forumPostService;
    }

    public ForumPostMessage getForumPosts_Game(int gameID){
        return forumPostService.getForumPosts_Game(gameID);
    }

    public ForumPostMessage getForumPosts_Account(int accountID){
        return forumPostService.getForumPosts_Account(accountID);
    }

    public Message addForumPost(int accountID, int gameID, String message, Timestamp postDate ) {
        return forumPostService.addForumPost(new ForumPostDTO(accountID,gameID,message,postDate));
    }
}
