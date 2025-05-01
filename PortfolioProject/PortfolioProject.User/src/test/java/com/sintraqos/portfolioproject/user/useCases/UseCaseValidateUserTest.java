package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.shared.*;
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.entities.UserMessage;

// Spring components
import org.springframework.security.crypto.password.PasswordEncoder;

// External components
import org.slf4j.Logger;

// Test components
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UseCaseValidateUserTest {
    @Mock
    UserRepository userRepository;

    @Mock
    SettingsHandler settingsHandler;

    @Mock
    CensorService censorService;

    @Mock
    Logger logger;

    @Mock
    PasswordEncoder passwordEncoder;

    UseCaseValidateUser useCaseValidateUser;

    String username = "TEST Username";
    String usernameShort = "U";
    String usernameLong = "This username is longer than possible";
    String eMail = "valid@EMail.com";
    String eMailInvalid = "invalidEMail";
    String password = "TEST P@$$w0rd";
    String passwordShort = "P";
    String passwordLong = "This password is longer than possible";
    String passwordInvalid = "password";

    int minLength = 2;  // Min length of a string
    int maxLength = 16; // Max length of a string

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseValidateUser = new UseCaseValidateUser(userRepository, settingsHandler, censorService, logger, passwordEncoder);
    }

    //region Username

    @Test
    void testValidateUsername_Fail_UsernameTooShort() {
        // Mock the correct method calls
        when(settingsHandler.getUsernameMinLength()).thenReturn(minLength);
        when(settingsHandler.getUsernameMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateUsername(usernameShort);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.USERNAME_INVALID_LENGTH_SHORT.formatted(
                settingsHandler.getUsernameMinLength(),
                settingsHandler.getUsernameMaxLength()), result.getMessage());

        // Verify
        verify(logger, times(5)).debug(anyString());
    }

    @Test
    void testValidateUsername_Fail_UsernameTooLong() {
        // Mock the correct method calls
        when(settingsHandler.getUsernameMinLength()).thenReturn(minLength);
        when(settingsHandler.getUsernameMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateUsername(usernameLong);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.USERNAME_INVALID_LENGTH_LONG.formatted(
                settingsHandler.getUsernameMinLength(),
                settingsHandler.getUsernameMaxLength()), result.getMessage());

        // Verify
        verify(logger, times(5)).debug(anyString());
    }

    @Test
    void testValidateUsername_Fail_ContainsBannedWord() {
        // Mock the correct method calls
        when(settingsHandler.getUsernameMinLength()).thenReturn(minLength);
        when(settingsHandler.getUsernameMaxLength()).thenReturn(maxLength);
        when(censorService.containsBannedWord(username)).thenReturn(true);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateUsername(username);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.USERNAME_CONTAINS_BANNED_WORD, result.getMessage());

        // Verify
        verify(logger, times(5)).debug(anyString());
    }

    @Test
    void testValidateUsername_Fail_AlreadyUsed() {
        // Mock the correct method calls
        when(settingsHandler.getUsernameMinLength()).thenReturn(minLength);
        when(settingsHandler.getUsernameMaxLength()).thenReturn(maxLength);
        when(censorService.containsBannedWord(username)).thenReturn(false);
        when(userRepository.findByUsername(username)).thenReturn(new UserEntity());

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateUsername(username);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.USERNAME_ALREADY_IN_USE.formatted(username), result.getMessage());

        // Verify
        verify(logger, times(5)).debug(anyString());
    }

    @Test
    void testValidateUsername_Success() {
        // Mock the correct method calls
        when(settingsHandler.getUsernameMinLength()).thenReturn(minLength);
        when(settingsHandler.getUsernameMaxLength()).thenReturn(maxLength);
        when(censorService.containsBannedWord(username)).thenReturn(false);
        when(userRepository.findByUsername(username)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateUsername(username);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Username Validated", result.getMessage());

        // Verify
        verify(logger, times(4)).debug(anyString());
    }

    //endregion

    //region E-Mail

    @Test
    void testValidateEMail_Fail_InvalidFormat() {
        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateEMail(eMailInvalid);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.EMAIL_INVALID.formatted(eMailInvalid), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidateEMail_Fail_AlreadyUsed() {
        // Mock the correct method calls
        when(userRepository.findByEmail(eMail)).thenReturn(new UserEntity());

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateEMail(eMail);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.EMAIL_ALREADY_IN_USE.formatted(eMail), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidateEMail_Success() {
        // Mock the correct method calls
        when(userRepository.findByEmail(eMail)).thenReturn(null);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validateEMail(eMail);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("E-Mail Validated", result.getMessage());
    }

    //endregion

    //region Password

    @Test
    void testValidatePassword_Fail_TooShort(){
        // Mock the correct method calls
        when(settingsHandler.getPasswordMinLength()).thenReturn(minLength);
        when(settingsHandler.getPasswordMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validatePassword(passwordShort);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.PASSWORD_INVALID_LENGTH_SHORT.formatted(
                settingsHandler.getPasswordMinLength(),
                settingsHandler.getPasswordMaxLength()), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidatePassword_Fail_TooLong(){
        // Mock the correct method calls
        when(settingsHandler.getPasswordMinLength()).thenReturn(minLength);
        when(settingsHandler.getPasswordMaxLength()).thenReturn(maxLength);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validatePassword(passwordLong);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.PASSWORD_INVALID_LENGTH_LONG.formatted(
                settingsHandler.getPasswordMinLength(),
                settingsHandler.getPasswordMaxLength()), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidatePassword_Fail_SpecialCharacter(){
        // Mock the correct method calls
        when(settingsHandler.getPasswordMinLength()).thenReturn(minLength);
        when(settingsHandler.getPasswordMaxLength()).thenReturn(maxLength);
        when(settingsHandler.isPasswordContainSpecialChar()).thenReturn(true);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validatePassword(passwordInvalid);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.PASSWORD_INVALID_SPECIAL_CHAR, result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidatePassword_Fail_CapitalLetter(){
        // Mock the correct method calls
        when(settingsHandler.getPasswordMinLength()).thenReturn(minLength);
        when(settingsHandler.getPasswordMaxLength()).thenReturn(maxLength);
        when(settingsHandler.isPasswordContainSpecialChar()).thenReturn(false);
        when(settingsHandler.isPasswordContainCapital()).thenReturn(true);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validatePassword(passwordInvalid);

        // Assert
        Assertions.assertFalse(result.isSuccessful());
        Assertions.assertEquals(Errors.PASSWORD_INVALID_CAPITAL_CHAR, result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testValidatePassword_Success(){
        // Mock the correct method calls
        when(settingsHandler.getPasswordMinLength()).thenReturn(minLength);
        when(settingsHandler.getPasswordMaxLength()).thenReturn(maxLength);
        when(settingsHandler.isPasswordContainSpecialChar()).thenReturn(true);
        when(settingsHandler.isPasswordContainCapital()).thenReturn(true);

        // Post the message using the base class
        UserMessage result = useCaseValidateUser.validatePassword(password);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Password Validated", result.getMessage());
    }

    //endregion
}