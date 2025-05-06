package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.*;

// External components
import org.slf4j.Logger;
import org.instancio.Instancio;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseBanAccountTest {

    @Mock
    UseCaseGetAccount getAccount;

    @Mock
    UserRepository userRepository;

    @Mock
    Logger logger;

    UseCaseBanAccount useCaseBanAccount;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseBanAccount = new UseCaseBanAccount(getAccount, userRepository, logger);
    }

    @Test
    void banAccount_Fail() {
        // Create Game object
        User user = Instancio.create(User.class);

        // Mock the correct method calls
        when(getAccount.getAccount(user.getUsername())).thenReturn(new UserMessage(Errors.USER_BANNED.formatted(user.getUsername()))); // Mock the correct method call

        // Ban the user using the base class
        UserMessage result = useCaseBanAccount.banAccount(user.getUsername());

        // Assert
        Assertions.assertEquals(Errors.USER_BANNED.formatted(user.getUsername()), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void banAccount_Success() {
        // Create User object
        User user = Instancio.create(User.class);

        // Mock the correct method calls
        when(getAccount.getAccount(user.getUsername())).thenReturn(new UserMessage(Instancio.create(UserDTO.class),"Account data retrieved")); // Mock the correct method call

        // Ban the user using the base class
        UserMessage result = useCaseBanAccount.banAccount(user.getUsername());

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully banned account: '%s'".formatted(user.getUsername()), result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void unbanAccount_Fail() {
        // Create User object
        User user = Instancio.create(User.class);

        // Mock the correct method calls
        when(getAccount.getAccount(user.getUsername())).thenReturn(new UserMessage(Errors.USER_BANNED.formatted(user.getUsername()))); // Mock the correct method call

        // Ban the user using the base class
        UserMessage result = useCaseBanAccount.unbanAccount(user.getUsername());

        // Assert
        Assertions.assertEquals(Errors.USER_BANNED.formatted(user.getUsername()), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void unbanAccount_Success() {
        // Create User object
        User user = Instancio.create(User.class);

        // Mock the correct method calls
        when(getAccount.getAccount(user.getUsername())).thenReturn(new UserMessage(Instancio.create(UserDTO.class),"Account data retrieved")); // Mock the correct method call

        // Ban the user using the base class
        UserMessage result = useCaseBanAccount.unbanAccount(user.getUsername());

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully unbanned account: '%s'".formatted(user.getUsername()), result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }
}
