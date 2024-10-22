package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Entities.GameEntity;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Game Controller, use for communication between application and database
 */
@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
private GameRepository gameRepository;

    /**
     * Add a new game
     *
     * @param gameDTO the new game to be added
     */
    @PostMapping
    public ResponseEntity<GameEntity> addGame(@RequestBody GameDTO gameDTO) {
        GameEntity game = new GameEntity(gameDTO.getGameName(), gameDTO.getGameDescription(), gameDTO.getGameDeveloper(), gameDTO.getGamePublisher());
        GameEntity savedGame = gameRepository.save(game);
        return ResponseEntity.ok(savedGame);
    }
}
