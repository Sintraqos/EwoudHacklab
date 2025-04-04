package com.sintraqos.portfolioproject.game.DAL;

import com.sintraqos.portfolioproject.caching.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    @Caching(evict = {@CacheEvict(value = CacheConfig.GAME_CACHE, key = "#gameID")})
    GameEntity findByGameID(int gameID);

    @Caching(evict = {@CacheEvict(value = CacheConfig.GAME_CACHE, key = "#gameName")})
    GameEntity findByGameName(String gameName);

    @Caching(evict = {@CacheEvict(value = CacheConfig.GAME_CACHE, key = "#gameName")})
    List<GameEntity> findByGameNameContaining(String gameName); // Get all games with the given name. IE: "Mass" return all games containing "Mass" in their title
}