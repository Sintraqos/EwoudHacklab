package com.sintraqos.portfolioproject.output;

import com.google.gson.Gson;
import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.*;
import java.util.Objects;

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

        // First read the path files
        audioPathsFile = readPathsFile(ResourcePaths.AUDIO_DIRECTORY);
        portraitPathsFile = readPathsFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY);

        // Then setup the manager objects
        GameAudioManager.getInstance();
        GameGUIManager.getInstance();
    }

    ResourcePaths.ResourcePathsFile readPathsFile(String pathType) {

        String dataPath = ResourcePaths.getFilePath(pathType);
        Console.writeLine(dataPath);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(dataPath)))) {
            return new Gson().fromJson(reader, ResourcePaths.ResourcePathsFile.class);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Error reading " + dataPath, ex);
        }
    }
}
