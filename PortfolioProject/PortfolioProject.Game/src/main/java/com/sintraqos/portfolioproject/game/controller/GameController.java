package com.sintraqos.portfolioproject.game.controller;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
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

    private GameRepository gameRepository;

    @Autowired
    public GameController(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

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
