package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import org.instancio.Instancio;
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
class UseCaseUpdateAccountTest {
    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    @Mock
    UseCaseGetAccount getAccount;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    Logger logger;

    UseCaseUpdateAccount useCaseUpdateAccount;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
        useCaseUpdateAccount = new UseCaseUpdateAccount(userRepository, getAccount, validateUser, passwordEncoder, logger);
    }

    int accountID = 0;
    int adminAccountID = 0;
    String currentUsername = "TEST Current Username";
    String newUsername = "TEST New Username";
    String currentPassword = "Password";
    String newPassword = " Password";
    String newEMail = "TEST New E-Mail";

    //region Username

    @Test
    void testChangeUsername_Fail_Validation() {
        // Mock the correct method calls
        when(validateUser.validateUsername(newUsername)).thenReturn(new UserMessage(Errors.USERNAME_CONTAINS_BANNED_WORD));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeUsername(currentUsername, newUsername, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.USERNAME_CONTAINS_BANNED_WORD, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeUsername_Fail_UserDoesNotExist() {
        // Mock the correct method calls
        when(validateUser.validateUsername(newUsername)).thenReturn(new UserMessage(true, "User valid"));
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername)));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeUsername(currentUsername, newUsername, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeUsername_Fail_Password() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(validateUser.validateUsername(newUsername)).thenReturn(new UserMessage(true, "User valid"));
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(Errors.PASSWORD_INCORRECT));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeUsername(currentUsername, newUsername, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_INCORRECT, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeUsername_Fail_UsernameUsed() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(validateUser.validateUsername(newUsername)).thenReturn(new UserMessage(true, "User valid"));
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, Errors.PASSWORD_MATCH));
        when(userRepository.findByUsername(newUsername)).thenReturn(Instancio.create(UserEntity.class));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeUsername(currentUsername, newUsername, currentPassword);

        // Assert
        Assertions.assertEquals("Username: '%s' is already in use".formatted(newUsername), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeUsername_Success() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(validateUser.validateUsername(newUsername)).thenReturn(new UserMessage(true, "User valid"));
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, Errors.PASSWORD_MATCH));
        when(userRepository.findByUsername(newUsername)).thenReturn(null);

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeUsername(currentUsername, newUsername, currentPassword);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully updated account", result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }

    //endregion

    //region E-Mail

    @Test
    void testChangeEmail_Fail_UserDoesNotExist() {
        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername)));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeEMail(currentUsername, newEMail, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeEmail_Fail_Password() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(Errors.PASSWORD_INCORRECT));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeEMail(currentUsername, newEMail, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_INCORRECT, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeEmail_Fail_Validation() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, Errors.PASSWORD_MATCH));
        when(validateUser.validateEMail(newEMail)).thenReturn(new UserMessage(Errors.EMAIL_ALREADY_IN_USE));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeEMail(currentUsername, newEMail, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.EMAIL_ALREADY_IN_USE, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeEmail_Success() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, "Password valid"));
        when(validateUser.validateEMail(newEMail)).thenReturn(new UserMessage(true, "Email valid"));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeEMail(currentUsername, newEMail, currentPassword);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully updated account", result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }

    //endregion

    //region Password

    @Test
    void testChangePassword_Fail_UserDoesNotExist() {
        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername)));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changePassword(currentUsername, currentPassword, newPassword);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_NAME_FAILED.formatted(currentUsername), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangePassword_Fail_Validation(){
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.validatePassword(newPassword)).thenReturn(new UserMessage(Errors.PASSWORD_INVALID_CAPITAL_CHAR));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changePassword(currentUsername, currentPassword, newPassword);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_INVALID_CAPITAL_CHAR, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangePassword_Fail_SameAsOldPassword() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.validatePassword(newPassword)).thenReturn(new UserMessage(true, "Password valid"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(Errors.PASSWORD_MATCH));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changePassword(currentUsername, currentPassword, newPassword);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_MATCH, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangePassword_Success() {
        // Create UserEntity object
        UserEntity userEntity = Instancio.create(UserEntity.class);

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(userEntity), userEntity, "User found"));
        when(validateUser.validatePassword(newPassword)).thenReturn(new UserMessage(true, "Password valid"));
        when(validateUser.comparePassword(userEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, "Password is new"));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changePassword(currentUsername, currentPassword, newPassword);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Successfully updated account", result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }

    //endregion

    //region Role

    @Test
    void testChangeRole_Fail_AdminDoesNotExist() {
        // Mock the correct method calls
        when(getAccount.getAccount(adminAccountID)).thenReturn(new UserMessage(Errors.FIND_USER_ID_FAILED.formatted(adminAccountID)));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeRole(adminAccountID, currentPassword, accountID, Enums.Role.ADMIN);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_ID_FAILED.formatted(adminAccountID), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testChangeRole_Fail_InvalidRole() {
        // Create UserEntity object
        UserEntity adminEntity = Instancio.create(UserEntity.class);
        adminEntity.setRole(Enums.Role.USER); // Since we need to test it for an invalid admin role set it to Role.USER beforehand

        // Mock the correct method calls
        when(getAccount.getAccount(adminAccountID)).thenReturn(new UserMessage(new UserDTO(adminEntity), adminEntity, "User found"));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeRole(adminAccountID, currentPassword, accountID, Enums.Role.ADMIN);

        // Assert
        Assertions.assertEquals(Errors.USER_INVALID_ROLE.formatted(Enums.Role.ADMIN.toString()), result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeRole_Fail_UserDoesNotExist() {
        // Create UserEntity object
        UserEntity adminEntity = Instancio.create(UserEntity.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        adminEntity.setRole(Enums.Role.ADMIN); // Since we need to test it with a valid admin role set it to Role.ADMIN beforehand
        userEntity.setRole(Enums.Role.USER);

        // Mock the correct method calls
        when(getAccount.getAccount(adminAccountID)).thenReturn(new UserMessage(new UserDTO(adminEntity), adminEntity, "User found"));
        when(getAccount.getAccount(accountID)).thenReturn(new UserMessage(Errors.FIND_USER_ID_FAILED.formatted(adminAccountID)));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeRole(adminAccountID, currentPassword, accountID, Enums.Role.ADMIN);

        // Assert
        Assertions.assertEquals(Errors.FIND_USER_ID_FAILED.formatted(adminAccountID), result.getMessage());

        // Verify
        verify(logger).debug(anyString());
    }

    @Test
    void testChangeRole_Fail_Password() {
        // Create UserEntity object
        UserEntity adminEntity = Instancio.create(UserEntity.class);
        adminEntity.setRole(Enums.Role.USER); // Since we need to test it for an invalid admin role set it to Role.USER beforehand

        // Mock the correct method calls
        when(getAccount.getAccount(currentUsername)).thenReturn(new UserMessage(new UserDTO(adminEntity), adminEntity, "User found"));
        when(validateUser.comparePassword(adminEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(Errors.PASSWORD_INCORRECT));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeEMail(currentUsername, newEMail, currentPassword);

        // Assert
        Assertions.assertEquals(Errors.PASSWORD_INCORRECT, result.getMessage());

        // Verify
        verify(logger, times(2)).debug(anyString());
    }

    @Test
    void testChangeRole_Success() {
        // Create UserEntity object
        UserEntity adminEntity = Instancio.create(UserEntity.class);
        adminEntity.setRole(Enums.Role.ADMIN); // Since we need to test it with a valid admin role set it to Role.ADMIN beforehand

        // Mock the correct method calls
        when(getAccount.getAccount(adminAccountID)).thenReturn(new UserMessage(new UserDTO(adminEntity), adminEntity, "User found"));
        when(validateUser.comparePassword(adminEntity.getPasswordHash(), currentPassword)).thenReturn(new UserMessage(true, Errors.PASSWORD_MATCH));

        // Update the user using the base class
        UserMessage result = useCaseUpdateAccount.changeRole(adminAccountID, currentPassword, accountID, Enums.Role.ADMIN);

        // Assert
        Assertions.assertTrue(result.isSuccessful());
        Assertions.assertEquals("Role successfully updated to: '%s' for account with ID: %s".formatted(Enums.Role.ADMIN, adminAccountID), result.getMessage());

        // Verify
        verify(userRepository).save(any());
        verify(logger, times(2)).debug(anyString());
    }

    //endregion
}
