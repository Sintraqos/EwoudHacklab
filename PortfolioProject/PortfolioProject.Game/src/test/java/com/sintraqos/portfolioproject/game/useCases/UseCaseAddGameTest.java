package com.sintraqos.portfolioproject.game.useCases;

// Project components
import com.sintraqos.portfolioproject.game.DAL.*;
import com.sintraqos.portfolioproject.game.entities.*;
import com.sintraqos.portfolioproject.shared.Errors;

// External components
import org.slf4j.Logger;
import org.instancio.Instancio;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseAddGameTest {

    @Mock
    GameRepository gameRepository;

    @Mock
    UseCaseGetGame getGame;

    @Mock
    Logger logger;

    UseCaseAddGame useCaseAddGame;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseAddGame = new UseCaseAddGame(getGame, gameRepository, logger);
    }

    @Test
    void addGame_Fail() {
        // Create Game object
        Game game = Instancio.create(Game.class);

        // Mock the correct method calls
        GameMessage existingGameMessage = new GameMessage(Instancio.create(GameEntity.class), Errors.GAME_EXISTS.formatted(game.getGameName()));
        when(getGame.getGame(game.getGameName())).thenReturn(existingGameMessage);  // Mock the correct method call

        // Add the game using the base class
        GameMessage result = useCaseAddGame.addGame(game);

        // Assert
        Assertions.assertEquals(Errors.GAME_EXISTS.formatted(game.getGameName()), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void addGame_Success() {
        // Create Game object
        Game game = Instancio.create(Game.class);

        // Mock the correct method calls
        GameMessage existingGameMessage = new GameMessage(false, "");
        when(getGame.getGame(game.getGameName())).thenReturn(existingGameMessage);  // Mock the correct method call

        // Add the game using the base class
        GameMessage result = useCaseAddGame.addGame(game);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Added new game: '%s'".formatted(game.getGameName()), result.getMessage());

        // Verify
        verify(gameRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }
}
