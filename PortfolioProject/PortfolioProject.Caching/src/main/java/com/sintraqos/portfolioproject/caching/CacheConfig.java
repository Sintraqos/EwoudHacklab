package com.sintraqos.portfolioproject.caching;

// Spring components
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfig {

    public static final String FORUM_CACHE = "forumCache";
    public static final String USER_CACHE = "userCache";
    public static final String GAME_CACHE = "gameCache";

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(FORUM_CACHE, USER_CACHE, GAME_CACHE);
    }
}
