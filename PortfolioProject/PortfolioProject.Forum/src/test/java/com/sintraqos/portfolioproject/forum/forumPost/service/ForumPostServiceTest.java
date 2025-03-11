package com.sintraqos.portfolioproject.forum.forumPost.service;

import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.useCase.UseCaseAddForumPost;
import com.sintraqos.portfolioproject.forum.forumPost.useCase.UseCaseGetForumPost;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
class ForumPostServiceTest {

    private final UseCaseAddForumPost addForumPost;
    private final UseCaseGetForumPost getForumPost;

    @Autowired
    public ForumPostServiceTest(
            UseCaseAddForumPost addForumPost,
            UseCaseGetForumPost getForumPost) {
        this.addForumPost = addForumPost;
        this.getForumPost = getForumPost;
    }

    @Test
    void addForumPost() {
        if (addForumPost.addForumPost(new ForumPostDTO(0, 0, "Test Message")).isSuccessful()) {
            System.out.println("Successfully added forum post");
        } else {
            System.out.println("Failed to add forum post");
        }
    }

    @Test
    void getForumPosts_Game() {
        if (getForumPost.getForumPosts_Game(0, PageRequest.of(0,10)).isSuccessful()) {
            System.out.println("Successfully retrieved forum posts");
        } else {
            System.out.println("Failed to retrieve forum post");
        }
    }

    @Test
    void getForumPosts_Account() {
        if (getForumPost.getForumPosts_Account(0, PageRequest.of(0,10)).isSuccessful()) {
            System.out.println("Successfully retrieved forum posts");
        } else {
            System.out.println("Failed to retrieve forum post");
        }
    }
}