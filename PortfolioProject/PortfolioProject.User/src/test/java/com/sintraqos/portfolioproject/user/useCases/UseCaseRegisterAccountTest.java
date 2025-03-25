package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseRegisterAccountTest {

    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    @Mock
    UserLibraryService libraryService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    Logger logger;

    UseCaseRegisterAccount useCaseRegisterAccount;

    String username = "TEST Username";
    String eMail = "TEST E-Mail";
    String password = "TEST Password";

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseRegisterAccount = new UseCaseRegisterAccount(validateUser, libraryService, passwordEncoder, userRepository, logger);
    }

    @Test
    void testRegisterAccount_Fail() {
        // Mock the correct method calls
        when(validateUser.validateUser(username, eMail,password)).thenReturn(new UserMessage(Errors.USERNAME_ALREADY_IN_USE.formatted(username)));

        // Post the message using the base class
        UserMessage result = useCaseRegisterAccount.registerAccount(username,eMail,password, Enums.Role.USER);

        // Assert
        Assertions.assertEquals(Errors.USERNAME_ALREADY_IN_USE.formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testRegisterAccount_Success() {
        // Mock the correct method calls
        when(validateUser.validateUser(username, eMail,password)).thenReturn(new UserMessage(true,"User valid"));

        // Post the message using the base class
        UserMessage result = useCaseRegisterAccount.registerAccount(username,eMail,password, Enums.Role.USER);

        // Assert
        Assertions.assertEquals("Created new account: '%s'".formatted(username), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }
}