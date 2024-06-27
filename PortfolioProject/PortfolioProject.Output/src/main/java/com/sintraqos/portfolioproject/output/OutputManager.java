package com.sintraqos.portfolioproject.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.output.gameAudio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gameGUI.GameGUIManager;
import com.sintraqos.portfolioproject.output.gameGUI.GameSettings;

import java.io.*;

public class OutputManager {
    // Get instance
    static OutputManager instance;

    public static OutputManager getInstance() {
        if (instance == null) {
            instance = new OutputManager();
            instance.Setup();
        }

        return instance;
    }

    GameSettings settings;

    public GameSettings getSettings() {
        return settings;
    }

    public static GameGUIManager gameGUIManager;
    public static GameAudioManager gameAudioManager;

    void Setup() {
        // Read / Create settings file
        HandleSettings();

        gameAudioManager = new GameAudioManager().getInstance();
        gameGUIManager = new GameGUIManager().getInstance();
    }


    //region Settings

    void HandleSettings() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "GameSettings");

        settings = new GameSettings();

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                Console.StringOutput("Successfully read from settings file");
                settings.SetGameSettings(gson.fromJson(reader, GameSettings.class));

            } catch (IOException e) {
                Console.StringOutput("Failed to read from settings file");
                throw new RuntimeException();
            }
        }
        // Create a new settings file
        else {
            try (Writer writer = new FileWriter(filePath)) {
                Console.StringOutput("Successfully created new settings file");
                settings = new GameSettings();
                gson.toJson(settings, writer);
            } catch (IOException e) {
                Console.StringOutput("Failed to create new settings file");
                throw new RuntimeException();
            }
        }
    }

    //endregion
}
