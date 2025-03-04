package com.sintraqos.portfolioproject.forum.caching;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String FORUM_CACHE = "forumCache";  // Use a constant string for storing the cache names

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(FORUM_CACHE);
    }
}
