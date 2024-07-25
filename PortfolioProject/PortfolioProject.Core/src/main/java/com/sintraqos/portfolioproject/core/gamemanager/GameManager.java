package com.sintraqos.portfolioproject.core.gamemanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.dataobjects.items.ItemManager;
import com.sintraqos.portfolioproject.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.GameSettings;

import java.io.*;

public class GameManager {
        Enums.gameState currentGameState;

   public GameManager() {
        Console.writeHeader("Initializing Game Manager");

        setGameState(Enums.gameState.GAME_STATE_INITIALIZE);
        // Read / Create settings file
        GameSettings.getInstance();
        handleSettings();
        ItemManager.getInstance();
        PlayerManager.getInstance();

        DialogueManager.getInstance();

       OutputManager.getInstance();

        Console.writeLine("Finished setting up Game Manager");
        Console.writeLine();
    }

    public void setGameState(Enums.gameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    //region Settings

    void handleSettings() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "GameSettings");

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                Console.writeLine("Successfully read from settings file");
                GameSettings.getInstance().setGameSettings(gson.fromJson(reader, GameSettings.class));

            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to create new settings file", ex);
            }
        }
        // Create a new settings file
        else {
            try (Writer writer = new FileWriter(filePath)) {
                Console.writeLine("Successfully created new settings file");
                gson.toJson(GameSettings.getInstance(), writer);
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to create new settings file", ex);
            }
        }
    }

    //endregion
}
