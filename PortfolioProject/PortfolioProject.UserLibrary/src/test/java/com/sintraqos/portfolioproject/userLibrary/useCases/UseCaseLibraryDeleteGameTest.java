package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseLibraryDeleteGameTest {

    @Mock
    UserLibraryRepository libraryRepository;

    @Mock
    Logger logger;

    UseCaseLibraryDeleteGame useCaseLibraryDeleteGame;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseLibraryDeleteGame = new UseCaseLibraryDeleteGame(libraryRepository,logger);
    }

    int accountID;

    @Test
    void testDeleteLibrary_Fail() {
        // Mock the correct method calls
        when(libraryRepository.findByAccountID(accountID)).thenReturn(new ArrayList<>());

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryDeleteGame.deleteLibrary(accountID);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.LIBRARY_CLEAR.formatted(accountID), result.getMessage());
    }

    @Test
    void testDeleteLibrary_Success() {
        // Mock the correct method calls
        when(libraryRepository.findByAccountID(accountID)).thenReturn(Instancio.createList(UserLibraryEntity.class));

        // Post the message using the base class
        UserLibraryEntityMessage result = useCaseLibraryDeleteGame.deleteLibrary(accountID);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Removed all games from account with ID: '%s'".formatted(accountID), result.getMessage());

        // Verify
        verify(libraryRepository.save(any()));
        verify(logger).debug(anyString());
    }
}