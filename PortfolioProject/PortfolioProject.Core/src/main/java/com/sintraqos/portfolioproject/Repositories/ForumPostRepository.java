package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPostEntity, Integer> {
    ForumPostEntity findByForumPostID(int forumPostID);

    List<ForumPostEntity> findAllByGameID(int gameID);        // Get all messages with the given gameID
    List<ForumPostEntity> findAllByAccountID(int accountID);  // Get all messages that an account has posted
}