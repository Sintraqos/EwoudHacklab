package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;

// External components
import org.slf4j.Logger;
import org.instancio.Instancio;

// Test components
import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class UseCaseGetAccountTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UserLibraryRepository libraryRepository;

    @Mock
    GameRepository gameRepository;

    @Mock
    Logger logger;

    UseCaseGetAccount useCaseGetAccount;

    String username = "TEST Username";
    int accountID = 0;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseGetAccount = new UseCaseGetAccount(userRepository, libraryRepository, gameRepository, logger);
    }

    @Test
    void testGetAccount_Username_Fail() {
        // Mock the correct method calls
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccount(username);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetAccount_Username_Success() {
        // Mock the correct method calls
        when(userRepository.findByUsername(username)).thenReturn(Instancio.create(UserEntity.class));

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccount(username);

        // Assert
        Assertions.assertEquals("Account data retrieved", result.getMessage());

        // Verify
        verify(logger, times(3)).debug(anyString());
    }

    @Test
    void testGetAccount_AccountID_Fail() {
        // Mock the correct method calls
        when(userRepository.findByAccountID(accountID)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccount(accountID);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_ID_FAILED.formatted(accountID), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetAccount_AccountID_Success() {
        // Mock the correct method calls
        when(userRepository.findByAccountID(accountID)).thenReturn(Instancio.create(UserEntity.class));

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccount(accountID);

        // Assert
        Assertions.assertEquals("Account data retrieved", result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetAccounts_Fail() {
        // Mock the correct method calls
        when(userRepository.findByUsernameContaining(username)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccounts(username);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testGetAccounts_Success() {
        // Mock the correct method calls
        when(userRepository.findByUsernameContaining(username)).thenReturn(Instancio.createList(UserEntity.class));

        // Post the message using the base class
        UserMessage result = useCaseGetAccount.getAccounts(username);

        // Assert
        Assertions.assertEquals("Accounts found containing: '%s'".formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }
}