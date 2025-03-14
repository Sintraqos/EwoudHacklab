package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.shared.Errors;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.when;

class UseCaseGetGameTest {

    @Mock
    GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    String gameName = "Game Name";
    int gameID = 0;

    @Test
    void getGame_ID() {
        System.out.printf("Attempting to get game with ID: '%s'%n", gameID);

//        when(gameRepository.findByGameID(gameID)).thenReturn(null);
        when(gameRepository.findByGameID(gameID)).thenReturn(new GameEntity());

        GameEntity game = gameRepository.findByGameID(gameID);

        // If the account was found return the retrieved account
        String message;
        if (game != null) {
            message = "Game with ID: '%s' found".formatted(gameID);
        }
        // Otherwise return the message
        else {
            message = Errors.FIND_GAME_ID_FAILED.formatted(gameID);
        }

        System.out.println(message);
    }

    @Test
    void getGame_Name() {
        System.out.printf("Attempting to get game with name: '%s'%n", gameName);

//        when(gameRepository.findByGameName(gameName)).thenReturn(null);
        when(gameRepository.findByGameName(gameName)).thenReturn(new GameEntity());

        // Get the account
        GameEntity game = gameRepository.findByGameName(gameName);

        // If the account was found return the retrieved account
        String message;
        if (game != null) {
            message = "Game with name: '%s' found".formatted(gameName);
        }
        // Otherwise return the message
        else {
            message = Errors.FIND_GAME_NAME_FAILED.formatted(gameName);
        }
        System.out.println(message);
    }

    @Test
    void getGames() {
        System.out.printf("Attempting to get games containing name: '%s'%n", gameName);

//        when(gameRepository.findByGameNameContaining(gameName)).thenReturn(null);
        when(gameRepository.findByGameNameContaining(gameName)).thenReturn(Instancio.createList(GameEntity.class));

        List<GameEntity> games = gameRepository.findByGameNameContaining(gameName);
        String message;
        if (games != null) {
             message ="Games containing: '%s' found".formatted(gameName);
        } else {
             message =Errors.FIND_GAME_NAME_FAILED.formatted(gameName);
        }
        System.out.println(message);
    }
}
