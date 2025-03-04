package com.sintraqos.portfolioproject.forum.forumPost.useCase;

import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.shared.Errors;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
class UseCaseAddForumPostTest {

    @Test
    void addForumPost() {
        ForumPostDTO forumPost = new ForumPostDTO(0, 0, "Test Message");
        // Check if the message is a valid length
        int messageLength = forumPost.getMessage().length();

        String baseMessage = "Failed to add message from user with ID: '%s'. Reason: %s";

        // Message too short
        if (messageLength < 2) {
            String message = Errors.FORUM_INVALID_LENGTH_SHORT.formatted(2, 10);
            System.out.printf((baseMessage) + "%n", forumPost.getAccountID(), message);

            return;
        }
        // Message too long
        if (messageLength > 255) {
            String message = Errors.FORUM_INVALID_LENGTH_LONG.formatted(2, 10);
            System.out.printf((baseMessage) + "%n", forumPost.getAccountID(), message);

            return;
        }

        Random rand = new Random();
        if (!rand.nextBoolean()) {
            System.out.printf("Failed to add new message: '%s'%n", forumPost.getMessage());
        } else {
            System.out.printf("Added new message: '%s'%n", forumPost.getMessage());
        }
    }
}