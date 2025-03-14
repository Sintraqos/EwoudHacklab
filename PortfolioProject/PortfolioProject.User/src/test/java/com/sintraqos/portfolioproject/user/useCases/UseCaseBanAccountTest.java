package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UseCaseBanAccountTest {

    @Mock
    UseCaseGetAccount getAccount;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void banAccount() {
        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage mockUserMessage = new UserMessage(new UserDTO(), "Account found for username Ban" );
        when(getAccount.getAccount("username Ban")).thenReturn(mockUserMessage);

        System.out.printf("Attempting to ban account: '%s'%n", "username Ban");

        // Simulate the banning of an account
        UserMessage result = handleBanAccount("username Ban", true);

        // Check that the result is successful
        assertTrue(result.isSuccessful());
        System.out.println("Successfully banned account");
    }

    @Test
    void unbanAccount() {
        // Mock the behavior of getAccount() to return a successful UserMessage
        UserMessage mockUserMessage = new UserMessage(new UserDTO(),"Account found for username Unban");
        when(getAccount.getAccount("username Unban")).thenReturn(mockUserMessage);

        System.out.printf("Attempting to unban account: '%s'%n", "username Unban");

        // Simulate the unbanning of an account
        UserMessage result = handleBanAccount("username Unban", false);

        // Check that the result is successful
        assertTrue(result.isSuccessful());
        System.out.println("Successfully unbanned account");
    }

    UserMessage handleBanAccount(String username, boolean isBanned) {
        // Retrieve the account
        UserMessage userMessage = getAccount.getAccount(username);

        // Check if retrieving the account was successful
        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());
            return userMessage;
        }

        // Set the account banned status
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setEnabled(!isBanned); // If banning, set 'enabled' to false
        user.setAccountNonLocked(!isBanned); // If banning, set 'locked' to true
        userRepository.save(user); // Save the updated user

        // Print a message depending on whether the account was banned or unbanned
        String returnMessage = (isBanned ? "Successfully banned account: '%s'" : "Successfully unbanned account: '%s'").formatted(username);

        // Return a successful message indicating the action was performed
        return new UserMessage(true, returnMessage);
    }
}
