package com.sintraqos.portfolioproject.forum.forumPost.DAL;

// Project components

import com.sintraqos.portfolioproject.caching.CacheConfig;

// Spring components
import org.springframework.cache.annotation.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Java components
import java.util.List;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPostEntity, Integer> {

    // Method to find all forum posts for a specific game ID in reversed order
    @Caching(evict = {@CacheEvict(value = CacheConfig.FORUM_CACHE, key = "#gameID")})
    Page<ForumPostEntity> findAllByGameIDOrderByPostDateDesc(int gameID, Pageable pageable); // Get all messages with the given gameID in reversed order

    // Method to find all forum posts for a specific account ID in reversed order
    // No need to cache it, since we don't want to keep this information for every user every time a user posts a message
    List<ForumPostEntity> findAllByAccountIDOrderByPostDateDesc(int accountID, Pageable pageable); // Get all messages that an account has posted in reversed order
}