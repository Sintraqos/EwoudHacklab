package com.sintraqos.portfolioproject.userLibrary.useCases;

// Project components
import com.sintraqos.portfolioproject.game.entities.GameMessage;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.*;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;

// External components
import org.slf4j.Logger;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseLibraryAddGameTest {

    @Mock
    GameService gameService;

    @Mock
    UserLibraryRepository libraryRepository;

    @Mock
    Logger logger;

    UseCaseLibraryAddGame useCaseLibraryAddGame;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseLibraryAddGame = new UseCaseLibraryAddGame(libraryRepository,gameService,logger);
    }

    int gameID;
    int accountID;

    @Test
    void testAddGame_Fail_GameDoesNotExist() {
        // Mock the correct method calls
        when(gameService.getGame(gameID)).thenReturn(new GameMessage("Game not found"));

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryAddGame.addGame(accountID,gameID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_GAME_ID_FAILED.formatted(gameID), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testAddGame_Fail_UserHasGame() {
        // Mock the correct method calls
        when(gameService.getGame(gameID)).thenReturn(new GameMessage(true,"Game found"));
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(new UserLibraryEntity());

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryAddGame.addGame(accountID,gameID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.USER_CONTAINS_GAME.formatted(gameID), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testAddGame_Success(){
        // Mock the correct method calls
        when(gameService.getGame(gameID)).thenReturn(new GameMessage(true,"Game found"));
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(null);

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryAddGame.addGame(accountID,gameID);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Added game with ID: '%s' to account account with ID: '%s'".formatted(gameID, accountID), result.getMessage());

        // Verify
        verify(libraryRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }
}