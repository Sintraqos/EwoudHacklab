package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Component
class UseCaseGetForumPostTest {

    @Test
    public void getForumPosts_Game() {
        System.out.println("Attempting to get all forum posts from game");
        List<ForumPostEntity> forumPostEntities = Instancio.ofList(ForumPostEntity.class).size(10).create();

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_GAME_ID_FAILED.formatted(0);
            System.out.println(message);

            return;
        }

        String message = "Game's forum posts found";
        System.out.println(message);
    }

    @Test
    public void getForumPosts_Account() {
        System.out.println("Attempting to get all forum posts from account");
        List<ForumPostEntity> forumPostEntities = Instancio.ofList(ForumPostEntity.class).size(10).create();

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_USER_ID_FAILED.formatted(0);
            System.out.println(message);

            return;
        }

        String message = "Account's forum posts found";
        System.out.println(message);
    }
}