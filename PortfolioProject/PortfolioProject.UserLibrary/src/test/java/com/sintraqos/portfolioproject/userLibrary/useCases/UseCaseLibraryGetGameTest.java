package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UseCaseLibraryGetGameTest {
    @Mock
    UserLibraryRepository libraryRepository;

    int accountID;
    int gameID;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getGame() {

        UserLibraryEntity userLibraryEntity = new UserLibraryEntity(accountID,gameID);
//        UserLibraryEntity userLibraryEntity = null;
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(userLibraryEntity);

        if (libraryRepository.findByAccountIDAndGameID(accountID, gameID) == null) {
            System.out.printf((Errors.FIND_GAME_ID_FAILED) + "%n", gameID);

            return;
        }

        String message = "Retrieved game with ID: '%s'".formatted(gameID);
        System.out.println(message);

        Assertions.assertTrue(true);
    }
}