package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * UseCase for handling retrieving forumPosts
 */
@Getter
@Component
public class UseCaseGetForumPost {
    private final ForumPostRepository forumPostRepository;

    @Autowired
    public UseCaseGetForumPost(ForumPostRepository forumPostRepository) {
        this.forumPostRepository = forumPostRepository;
    }

    public ForumPostMessage getForumPosts_Game(int gameID, PageRequest pageRequest) {
//        return forumPostService.getForumPosts_Game(gameID,pageRequest);

        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_GAME_ID_FAILED.formatted(gameID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }

    public ForumPostMessage getForumPosts_Account(int accountID,PageRequest pageRequest) {
        Page<ForumPostEntity> forumPostEntities = forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID, pageRequest);

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            return new ForumPostMessage(Errors.FORUM_ACCOUNT_ID_FAILED.formatted(accountID));
        }

        return new ForumPostMessage(forumPostEntities, "Forum posts found");
    }
}
