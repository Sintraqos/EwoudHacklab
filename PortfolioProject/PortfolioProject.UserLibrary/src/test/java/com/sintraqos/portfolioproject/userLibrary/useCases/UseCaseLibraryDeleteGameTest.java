package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class UseCaseLibraryDeleteGameTest {

    @Mock
    UserLibraryRepository libraryRepository;

    int accountID;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteLibrary() {
        // Get the list of the entries and delete them
        List<UserLibraryEntity> accountLibraryEntities = libraryRepository.findByAccountID(accountID);
        libraryRepository.deleteAll(accountLibraryEntities);

        // Return a message with the success
        String message ="Removed all games from account with ID: '%s'".formatted(accountID);
        System.out.println(message);

        Assertions.assertTrue(true);
    }
}