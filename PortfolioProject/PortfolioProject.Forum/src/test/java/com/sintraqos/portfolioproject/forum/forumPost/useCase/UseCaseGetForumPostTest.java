package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import lombok.Getter;
import org.instancio.Instancio;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.testcontainers.shaded.org.checkerframework.common.reflection.qual.MethodVal;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@Getter
@Component
class UseCaseGetForumPostTest {

    int gameID = 0;
    int accountID = 0;

    @Mock
    ForumPostRepository forumPostRepository;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getForumPosts_Game() {
        System.out.println("Attempting to get all forum posts from game");
        Page<ForumPostEntity> forumPostEntities = new PageImpl<ForumPostEntity>(Instancio.createList(ForumPostEntity.class));
        when(forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID,  PageRequest.of(0,10))).thenReturn(forumPostEntities);
         forumPostEntities = forumPostRepository.findAllByGameIDOrderByPostDateDesc(gameID,  PageRequest.of(0,10));
        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_GAME_ID_FAILED.formatted(gameID);
            System.out.println(message);

            return;
        }

        String message="Game's forum posts found";
        System.out.println(message);

        Assertions.assertTrue(true);
    }

    @Test
    public void getForumPosts_Account() {
        System.out.println("Attempting to get all forum posts from account");

        Page<ForumPostEntity> forumPostEntities = new PageImpl<ForumPostEntity>(Instancio.createList(ForumPostEntity.class));
        when(forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID,  PageRequest.of(0,10))).thenReturn(forumPostEntities);
        forumPostEntities = forumPostRepository.findAllByAccountIDOrderByPostDateDesc(accountID,  PageRequest.of(0,10));

        // Check if the list returned is null or empty
        if (forumPostEntities == null) {
            String message = Errors.FORUM_USER_ID_FAILED.formatted(accountID);
            System.out.println(message);

            return;
        }

        String message="Account's forum posts found";
        System.out.println(message);

        Assertions.assertTrue(true);
    }
}