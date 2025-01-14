package com.sintraqos.portfolioproject.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    GameEntity findByGameID(int gameID);

    GameEntity findByGameName(String gameName);

    List<GameEntity> findByGameNameContaining(String gameName); // Get all games with the given name. IE: "Mass" return all games containing "Mass" in their title
}