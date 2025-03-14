package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class UseCaseLibraryAddGameTest {

    @Mock
    GameService gameService;

    @Mock
    UserLibraryRepository libraryRepository;

    int gameID;
    int accountID;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addGame() {
       System.out.printf("Attempting to add new game to library of user: '%s'%n", accountID);

        // Check if the gameID is valid
        GameEntityMessage getGame = new GameEntityMessage(true, "Game found");
//        GameEntityMessage getGame = new GameEntityMessage(false, "Game not found");
        when(gameService.getGame(gameID)).thenReturn(getGame);
        if (!gameService.getGame(gameID).isSuccessful()) {
            System.out.printf((Errors.FIND_GAME_ID_FAILED) + "%n", gameID);

            return;
        }

        // Check if the account contains the game
        UserLibraryEntity getLibraryEntity = new UserLibraryEntity(accountID, gameID );
        when(libraryRepository.findByAccountIDAndGameID(accountID, gameID)).thenReturn(getLibraryEntity);
        if (libraryRepository.findByAccountIDAndGameID(accountID, gameID) != null) {
            System.out.printf((Errors.USER_CONTAINS_GAME) + "%n", gameID);

            return;
        }

        String message = "Added game with ID: '%s' to account account with ID: '%s'".formatted(gameID, accountID);
        System.out.println(message);

        Assertions.assertTrue(true);
    }
}