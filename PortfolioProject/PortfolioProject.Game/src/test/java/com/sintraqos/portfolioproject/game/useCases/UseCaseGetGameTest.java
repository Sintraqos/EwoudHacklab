package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.shared.Errors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseGetGameTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    Logger logger;

    UseCaseGetGame useCaseGetGame;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseGetGame = new UseCaseGetGame(gameRepository,logger);
    }

    String gameName = "Game Name";
    int gameID = 0;

    @Test
    void getGameID_Fail() {
        when(gameRepository.findByGameID(gameID)).thenReturn(null);
        GameEntityMessage result = useCaseGetGame.getGame(gameID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_ID_FAILED.formatted(gameID), result.getMessage());

        // Verify results;
        verify(gameRepository).findByGameID(gameID);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameID_Success() {
        when(gameRepository.findByGameID(gameID)).thenReturn(Instancio.create(GameEntity.class));
        GameEntityMessage result = useCaseGetGame.getGame(gameID);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Game with ID: '%s' found".formatted(gameID), result.getMessage());

        // Verify results;
        verify(gameRepository).findByGameID(gameID);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameName_Fail() {
        when(gameRepository.findByGameName(gameName)).thenReturn(null);
        GameEntityMessage result = useCaseGetGame.getGame(gameName);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_NAME_FAILED.formatted(gameName), result.getMessage());

        // Verify results;
        verify(gameRepository).findByGameName(gameName);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameName_Success() {
        when(gameRepository.findByGameName(gameName)).thenReturn(Instancio.create(GameEntity.class));
        GameEntityMessage result = useCaseGetGame.getGame(gameName);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Game with name: '%s' found".formatted(gameName), result.getMessage());

        // Verify results;
        verify(gameRepository).findByGameName(gameName);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGames_Fail() {

    }

    @Test
    void getGames_Success() {

    }
}
