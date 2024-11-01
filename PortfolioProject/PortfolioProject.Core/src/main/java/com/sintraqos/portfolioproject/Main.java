package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Statics.CommandHandler;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication(scanBasePackages = "com.sintraqos.portfolioproject")
public class Main {

    @Autowired
    private AccountManager accountManager;
    @Autowired
    private GameManager gameManager;
    @Autowired
    private CommandHandler commandHandler;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * After the initialization of Spring-Boot this will start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        Console.writeLine("Finished initializing Spring-Boot");
        Console.writeLine("#################################");

//        // Create test Library
//        createGameLibrary();
//        Console.writeLine("#################################");
//        Console.writeLine("Create a test account and test login");
//
//        // Since the commands all return back a message write those out in the console for now
//
//        // Register
//        Console.writeLine("-- Register");
//        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());
//        Console.writeLine(accountManager.createAccount("Username", "e@mail.com", "password").getMessage());
//
//        // Login
//        Console.writeLine("-- Login");
//        Console.writeLine(accountManager.loginAccount("Username", "wrongPassword").getMessage());
//        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
//        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
//
//        // Logout And Login
//        Console.writeLine("-- Logout And Login");
//        Console.writeLine(accountManager.logoutAccount("Username").getMessage());
//        Console.writeLine(accountManager.loginAccount("Username", "password").getMessage());
//
//        // Add And Remove Game From Account Library
//        Console.writeLine("-- Add And Remove Game From Account Library");
//        Console.writeLine(accountManager.addGame("Username", 1).getMessage());
//        Console.writeLine(accountManager.addGame("Username", 2).getMessage());
//        Console.writeLine(accountManager.getGame("Username", 2).getMessage());    // Get game with ID
//        Console.writeLine(accountManager.getGames("Username").getMessage());            // Get all games
//        // Remove Account
//        Console.writeLine("-- Remove Account");
//        Console.writeLine(accountManager.deleteAccount("Username", "wrongPassword").getMessage());
//        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());
//        Console.writeLine(accountManager.deleteAccount("Username", "password").getMessage());

        // Loop through the handleCommand
        while (true) {
            commandHandler.handleCommand(Console.readLine().toLowerCase());
        }
    }
}
