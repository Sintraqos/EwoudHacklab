package com.sintraqos.portfolioproject.output;

import com.google.gson.*;
import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.*;

public class OutputManager {
    // Get instance
    static OutputManager instance;

    public static OutputManager getInstance() {
        if (instance == null) {
            instance = new OutputManager();

            instance.setup();
        }

        return instance;
    }

    ResourcePaths.ResourcePathsFile audioPathsFile;
    ResourcePaths.ResourcePathsFile portraitPathsFile;

    public ResourcePaths.ResourcePathsFile getAudioPathsFile() {return audioPathsFile;}
    public ResourcePaths.ResourcePathsFile getPortraitPathsFile() {return portraitPathsFile;}

    void setup() {

        // Read out the settings file
        handleSettings();

        // Then read the path files
        audioPathsFile = Functions.readPathsFile(ResourcePaths.AUDIO_DIRECTORY);
        portraitPathsFile = Functions.readPathsFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY);

        // Then setup the manager objects
        GameAudioManager.getInstance();
        GameGUIManager.getInstance();
    }

    void handleSettings() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "GameSettings.json";

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                GameSettings.getInstance().setGameSettings(gson.fromJson(reader, GameSettings.class));

            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to read from settings file", ex);
            }
        }
        // Create a new settings file
        else {
            try (Writer writer = new FileWriter(filePath)) {
                gson.toJson(GameSettings.getInstance(), writer);
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to create new settings file", ex);
            }
        }
    }
}
