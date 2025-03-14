package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class UseCaseDeleteAccountTest {

    @Mock
    UseCaseGetAccount getAccount;

    @Mock
    UserLibraryService libraryService;

    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    String username = "TEST Username";
    String password = "TEST Password";

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deleteAccount() {
        UserDTO account = Instancio.create(UserDTO.class);

        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage mockUserMessage = new UserMessage(account, "Account found for deletion");
        when(getAccount.getAccount(username)).thenReturn(mockUserMessage);

        // Mock the behavior of validateUser() to return a successful UserMessage
        UserMessage passwordCheck = new UserMessage(account, "Password validated");
        when(validateUser.comparePassword(account.getPassword(), password)).thenReturn(passwordCheck);

        // Clear the stored library of the account
        System.out.printf("Deleting library from account: '%s'%n", account.getAccountID());
        libraryService.deleteLibrary(account.getAccountID());

        // Delete the account from the database
        System.out.printf("Deleting account: '%s'%n", account.getAccountID());
        userRepository.delete(new UserEntity(account));

        // Finalize account removal
        String message = "Successfully removed account with username: '%s'".formatted(username);
        System.out.println(message);
    }
}
