package com.sintraqos.portfolioproject.core.gamemanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.CreateCoreFiles;
import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.connect.JSONFiles.JSONFileConnectHandler;
import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.GameSettings;

import java.io.*;

public class GameManager {

    // Get Instance
    static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
            instance.setup();
        }

        return instance;
    }

    ConnectHandler connectHandler;
    public ConnectHandler getConnectHandler(){return connectHandler;}

    void setup() {

        new CreateCoreFiles();

        // Before anything else read out the settings file
        handleSettings();

        Console.writeHeader("Setup Game Manager");

        //new MariaDBConnectHandler();
        connectHandler = new JSONFileConnectHandler();

        // TODO: Await for connection to be made, if there is no connection made after a certain amount of time stop game from starting any further

        ItemManager.getInstance();
        PlayerManager.getInstance();

        DialogueManager.getInstance();

        Console.writeLine("Finished Setup Game Manager");
        Console.writeLine();

        OutputManager.getInstance();


        DialogueManager.getInstance().TESTHandleDialogue();
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
