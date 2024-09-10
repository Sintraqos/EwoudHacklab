package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.output.gui.dialogue.dialogueGUI;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

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
        CreateOutputFiles.getInstance();

        Console.writeHeader("Setup Output Manager");

        // Then read the path files
        audioPathsFile = ResourcePaths.readPathsFile(ResourcePaths.SOUND_TRACK_DIRECTORY);
        portraitPathsFile = ResourcePaths.readPathsFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY);

        // Then set up the manager objects
        GameAudioManager.getInstance().loadAudioFiles(Enums.currentLocation.CURRENT_LOCATION_PERAGUS);
        GameGUIManager.getInstance();

        Console.writeLine("Finished Setup Output Manager");
        Console.writeLine();

        // Load in the main menu screen
        //new MainMenuGUI();




        new dialogueGUI();
    }
}
