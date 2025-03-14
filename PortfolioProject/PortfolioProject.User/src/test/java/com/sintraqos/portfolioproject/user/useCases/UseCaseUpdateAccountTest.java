package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import org.instancio.Instancio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;

class UseCaseUpdateAccountTest {
    @Mock
    UserRepository userRepository;

    @Mock
    UseCaseValidateUser validateUser;

    @Mock
    UseCaseGetAccount getAccount;

    @Mock
    PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    int accountID = 0;
    String currentUsername = "TEST Current Username";
    String newUsername = "TEST New Username";
    String currentPassword = "Password";
    String newPassword = " Password";
    String currentEMail = "TEST Current E-Mail";
    Enums.Role accountRole = Enums.Role.USER;

    @Test
    void changeUsername() {
        System.out.printf("Attempting to change the username of account: '%s'%n", currentUsername);

        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage userMessage = new UserMessage(Instancio.create(UserEntity.class), "Account validated");

        // Check if the user is valid
        when(validateUser.validateUser(currentUsername, currentEMail, passwordEncoder.encode(currentPassword))).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            Assertions.fail();
            return;
        }

        // Retrieve the account
        when(getAccount.getAccount(currentUsername)).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            Assertions.fail();
            return;
        }

        UserEntity user = userMessage.getUserEntity();
        when(userRepository.findByUsername(newUsername)).thenReturn(null);
        if (userRepository.findByUsername(newUsername) != null) {
            System.out.printf((Errors.USERNAME_ALREADY_IN_USE) + "%n", newUsername);

            Assertions.fail();
            return;
        }

        // Return the message
        UserMessage updateAccount = handleUpdateAccount(user, newUsername, user.getEmail(), user.getPassword(), user.getRole());
        System.out.println(updateAccount.getMessage());

        Assertions.assertTrue(updateAccount.isSuccessful());
    }

    @Test
    void changeEMail() {
        System.out.printf("Attempting to change the E-Mail of account: '%s'%n", currentUsername);

        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage userMessage = new UserMessage(Instancio.create(UserEntity.class), "Account validated");

        // Retrieve the account
        when(getAccount.getAccount(currentUsername)).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            Assertions.fail();
            return;
        }

        when(validateUser.comparePassword(passwordEncoder.encode(currentPassword), passwordEncoder.encode(newPassword))).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            Assertions.fail();
            return;
        }

        // Check if the user is valid
        when(validateUser.validateEMail(currentEMail)).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            Assertions.fail();
            return;
        }

        UserEntity user = userMessage.getUserEntity();

        // Return the message
        UserMessage updateAccount = handleUpdateAccount(user, currentUsername, currentEMail, user.getPassword(), user.getRole());
        System.out.println(updateAccount.getMessage());

        Assertions.assertTrue(updateAccount.isSuccessful());
    }

    @Test
    void changePassword() {
        System.out.printf("Attempting to change the password of account: '%s'%n", currentUsername);

        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage userMessage = new UserMessage(Instancio.create(UserEntity.class), "Account validated");

        // Retrieve the account
        when(getAccount.getAccount(currentUsername)).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            return;
        }

        // Check if the user is valid
        when(validateUser.validatePassword(newPassword)).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            return;
        }

        // Compare the password that was given to the stored password
        when(validateUser.comparePassword(passwordEncoder.encode(currentPassword), passwordEncoder.encode(newPassword))).thenReturn(userMessage);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            return;
        }

        // Get the entity from the message
        UserEntity user = userMessage.getUserEntity();

        // Return the message
        UserMessage updateAccount =  handleUpdateAccount(user, currentUsername, user.getEmail(), newPassword, user.getRole());
        System.out.println(updateAccount.getMessage());

        Assertions.assertTrue(updateAccount.isSuccessful());
    }

    @Test
    void changeRole() {
        System.out.printf("Attempting to change the role of account with ID: '%s'%n", accountID);
        // Retrieve the user from the database
        UserMessage userMessage = getAccount.getAccount(accountID);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            return;
        }

        // Since the user needs to be an admin to update the roles of other users check if the user has a valid role
        if (userMessage.getUserDTO().getRole() == Enums.Role.USER) {
            String message = "Invalid role";
            System.out.println(message);

            return;
        }

        // Check if the given password is valid
        UserMessage passwordCheck = validateUser.comparePassword(userMessage.getUserDTO().getPassword(), currentPassword);
        if (!passwordCheck.isSuccessful()) {
            System.out.println(passwordCheck.getMessage());

            return;
        }

        // Retrieve the user which role to update from the database
        userMessage = getAccount.getAccount(accountID);
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());

            return;
        }

        // Update the role inside the userRepository
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setRole(accountRole);
        userRepository.save(user);

        UserMessage updateMessage = new UserMessage("Role successfully updated to: '%s' for account with ID: %s".formatted(accountRole, accountID));
        System.out.println(updateMessage.getMessage());

        Assertions.assertTrue(updateMessage.isSuccessful());
    }

    UserMessage handleUpdateAccount(UserEntity user, String username, String eMail, String password, Enums.Role role) {
        user.setUsername(username);
        user.setEmail(eMail);
        user.setPasswordHash(password);
        user.setRole(role);
        userRepository.save(user);

        return new UserMessage(true, "Successfully updated account");
    }
}