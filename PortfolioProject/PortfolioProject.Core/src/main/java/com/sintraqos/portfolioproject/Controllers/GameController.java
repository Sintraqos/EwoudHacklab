package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Entities.GameEntity;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Get all games
     */
    @GetMapping
    public List<GameEntity> getGames() {
        return gameRepository.findAll();
    }

    /**
     * Get game by ID
     *
     * @param id the gameID
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameEntity> getGameByID(@PathVariable Integer id) {
        return gameRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
