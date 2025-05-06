package com.sintraqos.portfolioproject.game.useCases;

// Project components
import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.entities.GameMessage;
import com.sintraqos.portfolioproject.shared.Errors;

// External components
import org.slf4j.Logger;
import org.instancio.Instancio;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
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
        useCaseGetGame = new UseCaseGetGame(gameRepository, logger);
    }

    String gameName = "Game Name";
    int gameID = 0;

    @Test
    void getGameID_Fail() {
        // Mock the correct method calls
        when(gameRepository.findByGameID(gameID)).thenReturn(null);
        GameMessage result = useCaseGetGame.getGame(gameID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_ID_FAILED.formatted(gameID), result.getMessage());

        // Verify results;
        verify(gameRepository).findByGameID(gameID);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameID_Success() {
        // Mock the correct method calls
        when(gameRepository.findByGameID(gameID)).thenReturn(Instancio.create(GameEntity.class));
        GameMessage result = useCaseGetGame.getGame(gameID);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Game with ID: '%s' found".formatted(gameID), result.getMessage());

        // Verify
        verify(gameRepository).findByGameID(gameID);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameName_Fail() {
        // Mock the correct method calls
        when(gameRepository.findByGameName(gameName)).thenReturn(null);
        GameMessage result = useCaseGetGame.getGame(gameName);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_NAME_FAILED.formatted(gameName), result.getMessage());

        // Verify
        verify(gameRepository).findByGameName(gameName);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGameName_Success() {
        // Mock the correct method calls
        when(gameRepository.findByGameName(gameName)).thenReturn(Instancio.create(GameEntity.class));
        GameMessage result = useCaseGetGame.getGame(gameName);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Game with name: '%s' found".formatted(gameName), result.getMessage());

        // Verify
        verify(gameRepository).findByGameName(gameName);
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void getGames_Fail() {
        // Mock the correct method calls
        when(gameRepository.findByGameNameContaining(gameName)).thenReturn(null);
        GameMessage result = useCaseGetGame.getGames(gameName);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_NAME_FAILED.formatted(gameName), result.getMessage());

        // Verify
        verify(gameRepository).findByGameNameContaining(gameName);
        verify(logger).debug(anyString());
    }

    @Test
    void getGames_Success() {
        // Mock the correct method calls
        when(gameRepository.findByGameNameContaining(gameName)).thenReturn(Instancio.createList(GameEntity.class));
        GameMessage result = useCaseGetGame.getGames(gameName);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Games containing: '%s' found".formatted(gameName), result.getMessage());

        // Verify
        verify(gameRepository).findByGameNameContaining(gameName);
        verify(logger).debug(anyString());
    }
}
