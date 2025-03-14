package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

class UseCaseGetAccountTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserLibraryRepository libraryRepository;

    @Mock
    GameRepository gameRepository;

    String username = "TEST Username";
    int accountID = 0;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAccount_Username() {
        System.out.printf("Attempting to get account with username: '%s'%n", username);
        // Get the account
        UserEntity userEntity = new UserEntity();
        when(userRepository.findByUsername(username)).thenReturn(userEntity);

        // Create the library of the user
        ArrayList<GameDTO> gameList = new ArrayList<>();
        System.out.println("Creating new library for account");

        List<UserLibraryEntity> userLibraryEntities = new ArrayList<>();
        when(libraryRepository.findByAccountID(userEntity.getAccountID())).thenReturn(userLibraryEntities);

        for (UserLibraryEntity userLibraryEntity : userLibraryEntities) {
            System.out.printf("Adding game with ID: '%s' to user library%n", userLibraryEntity.getGameID());
            GameEntity game = Instancio.create(GameEntity.class);
            when(gameRepository.findByGameID(0)).thenReturn(game);
            gameList.add(new GameDTO(
                    game,
                    userLibraryEntity.getGameAcquired(),
                    userLibraryEntity.getGameLastPlayed(),
                    userLibraryEntity.getGamePlayTime()));
        }

        // Return the found user
        System.out.println("Created user successfully");
        System.out.printf("Added: %s games to their library%n", gameList.size());
    }

    @Test
    void testGetAccount_AccountID() {
        System.out.printf("Attempting to get account with ID: '%s'%n", accountID);
        // Get the account
        UserEntity userEntity = userRepository.findByAccountID(accountID);

        // If the account was found return the retrieved account
        String message;
        if (userEntity != null) {
            message = "Account found with ID";
        }
        // Otherwise return the message
        else {
            message = Errors.FIND_USER_ID_FAILED.formatted(accountID);
        }

        System.out.println(message);
    }

    @Test
    void getAccounts() {
        System.out.printf("Attempting to get accounts containing: '%s'%n", username);
        List<UserEntity> accounts = userRepository.findByUsernameContaining(username);

        String message;
        if (accounts != null) {
            message = "Account found with username";
        } else {
            message = Errors.FIND_USER_NAME_FAILED.formatted(username);
        }

        System.out.println(message);
    }
}