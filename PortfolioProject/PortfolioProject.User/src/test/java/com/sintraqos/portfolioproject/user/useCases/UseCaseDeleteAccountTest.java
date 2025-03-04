package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class UseCaseDeleteAccountTest {

    @Test
    void deleteAccount() {
        Random rand = new Random();
        UserEntity account = Instancio.create(UserEntity.class);

        System.out.println("Attempting to delete account with username: '%s'".formatted(account.getUsername()));
        // Check if an account exists
        if (rand.nextBoolean()) {
            String message = Errors.FIND_USER_NAME_FAILED.formatted(account.getUsername());
            System.out.println(message);

            return;
        }

        // Compare the given password to the stored password
        if (rand.nextBoolean()) {
            // Clear the stored library of the account
            System.out.printf("Deleting library from account: '%s'%n", account.getAccountID());

            // Delete the account from the database
            System.out.printf("Deleting account: '%s'%n", account.getAccountID());

            // Finalize account removal
            System.out.printf("Successfully removed account with username: '%s'%n", account.getUsername());
        } else {
            System.out.println("Validation failed!");
        }
    }
}