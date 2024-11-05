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

        // Loop through the handleCommand
        while (true) {
            commandHandler.handleCommand(Console.readLine().toLowerCase());
        }
    }
}
