package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseBanAccountTest {

    @Test
    void banAccount() {
        System.out.printf("Attempting to ban account: '%s'%n", "username Ban");
        if (handleBanAccount("username Ban", true).isSuccessful()) {
            System.out.println("Successfully banned account");
        }
        else {
            System.out.println("Failed to ban account");
        }
    }

    @Test
    void unbanAccount() {
        System.out.printf("Attempting to unban account: '%s'%n", "username Unban");
        if (handleBanAccount("username Unban", false).isSuccessful()) {
            System.out.println("Successfully unbanned account");
        }
        else {
            System.out.println("Failed to unban account");
        }
    }

    UserMessage handleBanAccount(String username, boolean isBanned) {
        // Retrieve the account
        UserMessage userMessage = Instancio.create(UserMessage.class);

        if (!userMessage.isSuccessful()) {
            System.out.println(userMessage.getMessage());
            return userMessage;
        }

        // Set the account banned status
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setEnabled(!isBanned); // Since if the account is banned and receives a 'true' statement it should be set to 'false'
        user.setAccountNonLocked(!isBanned);

        String returnMessage = (isBanned ? "Successfully banned account: '%s'".formatted(username) : "Successfully unbanned account: '%s'".formatted(username));

        System.out.println(returnMessage);

        // Return the message
        return new UserMessage(true, returnMessage);
    }
}
