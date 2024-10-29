package com.sintraqos.portfolioproject.Repositories;

import com.sintraqos.portfolioproject.Entities.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    GameEntity findByGameID(int gameID);

    GameEntity findByGameName(String gameName);
}