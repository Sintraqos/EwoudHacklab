package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostRepository;
import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.stereotype.Component;

import java.util.Random;

@ExtendWith(MockitoExtension.class)
class UseCaseAddForumPostTest {

    @Mock
    ForumPostRepository forumPostRepository;

    @Mock
    CensorService censorService;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    int minLength = 2;  // Min length of a string
    int maxLength = 32; // Max length of a string

    @Test
    void addForumPost() {
        ForumPostDTO forumPost = Instancio.create(ForumPostDTO.class);

        // Check if the message is a valid length
        int messageLength = forumPost.getMessage().length();

        String baseMessage = "Failed to add message from user with ID: '%s'. Reason: %s";

        // Message too short
        if (messageLength < minLength) {
            String message = Errors.FORUM_INVALID_LENGTH_SHORT.formatted(minLength, maxLength);
            System.out.printf((baseMessage) + "%n", forumPost.getAccountID(), message);

            return;
        }
        // Message too long
        if (messageLength > maxLength) {
            String message = Errors.FORUM_INVALID_LENGTH_LONG.formatted(minLength, maxLength);
            System.out.printf((baseMessage) + "%n", forumPost.getAccountID(), message);

            return;
        }

        // Create a new ForumPostEntity object and save that in the database
        ForumPostEntity forumPostEntity = new ForumPostEntity(
                forumPost.getAccountID(),
                forumPost.getGameID(),
                censorService.validateString(forumPost.getMessage()));
        forumPostRepository.save(forumPostEntity);

        System.out.printf("Added new message: '%s'%n", forumPost.getMessage());

        Assertions.assertTrue(true);
    }
}