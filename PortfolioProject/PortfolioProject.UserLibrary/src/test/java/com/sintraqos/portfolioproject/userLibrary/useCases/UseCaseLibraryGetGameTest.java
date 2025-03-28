package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseLibraryGetGameTest {
    @Mock
    UserLibraryRepository libraryRepository;

    @Mock
    Logger logger;

    UseCaseLibraryGetGame useCaseLibraryGetGame;

    int accountID;
    int gameID;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseLibraryGetGame = new UseCaseLibraryGetGame(libraryRepository,logger);
    }

    @Test
    void testGetGame_Fail() {
        // Mock the correct method calls
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(null);

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryGetGame.getGame(accountID,gameID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.FIND_LIBRARY_FAILED.formatted(accountID, gameID), result.getMessage());
    }

    @Test
    void testGetGame_Success(){
        // Mock the correct method calls
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(new UserLibraryEntity());

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryGetGame.getGame(accountID,gameID);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Retrieved game with ID: '%s'".formatted(gameID), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }
}