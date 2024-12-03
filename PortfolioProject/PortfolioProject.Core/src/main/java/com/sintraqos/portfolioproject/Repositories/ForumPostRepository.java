package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Caching.CacheConfig;
import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumPostRepository extends JpaRepository<ForumPostEntity, Integer> {

//    @Caching(evict = {@CacheEvict(value = CacheConfig.FORUM_CACHE, key = "#gameID")})
    Page<ForumPostEntity> findAllByGameID(int gameID, Pageable pageable);        // Get all messages with the given gameID

//    @Caching(evict = {@CacheEvict(value = CacheConfig.FORUM_CACHE, key = "#accountID")})
    Page<ForumPostEntity> findAllByAccountID(int accountID, Pageable pageable);  // Get all messages that an account has posted
}