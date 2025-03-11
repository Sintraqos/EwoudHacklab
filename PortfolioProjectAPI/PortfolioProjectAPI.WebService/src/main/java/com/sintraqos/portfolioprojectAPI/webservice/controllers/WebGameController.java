package com.sintraqos.portfolioprojectAPI.webservice.controllers;

import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.service.GameService;
import com.sintraqos.portfolioprojectAPI.game.useCases.UseCaseGetGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class WebGameController {

    private final GameService gameService;

    @Autowired
    public WebGameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{gameID}")
    public GameDTO getGame(@PathVariable int gameID) {
        return gameService.getGame(gameID);
    }

    @GetMapping
    public List<GameDTO> getGames() {
        return gameService.getRecentlyAddedGames();
    }
}
