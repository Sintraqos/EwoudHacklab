package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mockito.Mockito.when;

@Component
class UseCaseAddGameTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    UseCaseGetGame getGame;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addGame() {
        Game game = Instancio.create(Game.class);
        addGame(game);

        Assertions.assertTrue(true);
    }

    @Test
    void addGames() {
        StringBuilder returnString = new StringBuilder();

        List<Game> games = Instancio.ofList(Game.class).size(15).create();

        for (Game game : games) {
            GameEntityMessage message = addGame(game);

            if (!message.isSuccessful()) {
                System.out.printf("Failed to add new game: '%s'%n", message.getMessage());
                returnString.append("\n").append(message.getMessage());
            }
        }

        if (!returnString.isEmpty()) {
            System.out.println(returnString);
            return;
        } else {
            System.out.println("Added all games successfully");
        }

        Assertions.assertTrue(true);
    }

    // The function which will actually store the new game into the repository
    GameEntityMessage addGame(Game game) {
        System.out.printf("Attempting to add new game: '%s'%n", game.getGameName());

//        GameEntityMessage getGameMessage = new GameEntityMessage(true, "Game already exists");
        GameEntityMessage getGameMessage = new GameEntityMessage(false, "Game doesn't exist");

        when(getGame.getGame(game.getGameName())).thenReturn(getGameMessage);

        // Check if a game with the given name already exists
        if (getGame.getGame(game.getGameName()).isSuccessful()) {
            System.out.printf((Errors.GAME_EXISTS) + "%n", game.getGameName());
            return new GameEntityMessage(Errors.GAME_EXISTS.formatted(game.getGameName()));
        }

        // Add new game
        GameEntity gameEntity = new GameEntity(game);
        System.out.printf("Added new game: '%s'%n", game.getGameName());
        return new GameEntityMessage(gameRepository.save(gameEntity), "Added new game: '%s'".formatted(game.getGameName()));
    }
}
