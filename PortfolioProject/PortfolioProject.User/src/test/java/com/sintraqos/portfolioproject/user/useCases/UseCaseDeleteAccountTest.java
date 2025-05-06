package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;

// External components
import org.slf4j.Logger;
import org.instancio.Instancio;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseDeleteAccountTest {

    @Mock
    UserLibraryService libraryService;

    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    @Mock
    Logger logger;

    UseCaseDeleteAccount useCaseDeleteAccount;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseDeleteAccount = new UseCaseDeleteAccount(userRepository, validateUser, libraryService, logger);
    }

    String username = "TEST Username";
    String password = "TEST Password";

    @Test
    public void deleteAccount_Fail_UserDoesNotExist() {
        // Mock the correct method calls
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseDeleteAccount.deleteAccount(username,password);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    public void deleteAccount_Fail_Validation() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(validateUser.comparePassword(userEntity.getPasswordHash(), password)).thenReturn(new UserMessage(Errors.PASSWORD_MISMATCH));

        // Post the message using the base class
        UserMessage result = useCaseDeleteAccount.deleteAccount(username, password);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_MISMATCH, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    public void deleteAccount_Success() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(userRepository.findByUsername(username)).thenReturn(userEntity);
        when(validateUser.comparePassword(userEntity.getPasswordHash(), password)).thenReturn(new UserMessage(true, "User successfully validated"));

        // Post the message using the base class
        UserMessage result = useCaseDeleteAccount.deleteAccount(username, password);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully removed account with username: '%s'".formatted(username), result.getMessage());

        // Verify
        verify(logger, times(4)).debug(anyString());
    }
}
