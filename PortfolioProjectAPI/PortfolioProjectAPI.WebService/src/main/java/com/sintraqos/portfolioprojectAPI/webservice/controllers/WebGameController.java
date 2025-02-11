package com.sintraqos.portfolioprojectAPI.webservice.controllers;

import com.sintraqos.portfolioprojectAPI.game.DTO.GameDTO;
import com.sintraqos.portfolioprojectAPI.game.useCases.UseCaseGetGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class WebGameController {

    private final UseCaseGetGame useCaseGetGame;

    @Autowired
    public WebGameController(UseCaseGetGame useCaseGetGame) {
        this.useCaseGetGame = useCaseGetGame;
    }

    @GetMapping("/{gameID}")
    public GameDTO getGame(@PathVariable int gameID) {
        return  useCaseGetGame.getGame(gameID);
    }

    @GetMapping
    public List<GameDTO> getGames(@RequestParam(required = false) String gameName) {
        if (gameName == null) {
            return useCaseGetGame.getRecentlyAddedGames();
        }

        return useCaseGetGame.getGames(gameName);
    }
}
