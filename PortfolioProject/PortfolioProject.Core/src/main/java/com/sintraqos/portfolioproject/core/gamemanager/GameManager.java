package com.sintraqos.portfolioproject.core.gamemanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.dataobjects.items.ItemManager;
import com.sintraqos.portfolioproject.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.GameSettings;

import java.io.*;

public class GameManager {

    // Get instance
    static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    Enums.gameState currentGameState;

    GameManager() {
        Console.StringTitleOutput("Initializing Game Manager");

        setGameState(Enums.gameState.GAME_STATE_INITIALIZE);
        // Read / Create settings file
        GameSettings.getInstance();
        HandleSettings();
        ItemManager.getInstance();
        PlayerManager.getInstance();
        OutputManager.getInstance();

        DialogueManager.getInstance();

        Console.StringOutput("Finished setting up Game Manager");
        Console.StringOutput();
    }

    public void setGameState(Enums.gameState currentGameState) {
        this.currentGameState = currentGameState;
    }

    //region Settings

    void HandleSettings() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "GameSettings");

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                Console.StringOutput("Successfully read from settings file");
                GameSettings.getInstance().setGameSettings(gson.fromJson(reader, GameSettings.class));

            } catch (IOException e) {
                Console.StringOutput("Failed to read from settings file");
                throw new RuntimeException();
            }
        }
        // Create a new settings file
        else {
            try (Writer writer = new FileWriter(filePath)) {
                Console.StringOutput("Successfully created new settings file");
                gson.toJson(GameSettings.getInstance(), writer);
            } catch (IOException e) {
                Console.StringOutput("Failed to create new settings file");
                throw new RuntimeException();
            }
        }
    }

    //endregion
}
