package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.dialogue.DialogueGUI;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueEvent;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueObject;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueTree;

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

    DialogueListener dialogueListener;
    ResourcePaths.ResourcePathsFile audioPathsFile;
    ResourcePaths.ResourcePathsFile portraitPathsFile;

    public ResourcePaths.ResourcePathsFile getAudioPathsFile() {
        return audioPathsFile;
    }

    public ResourcePaths.ResourcePathsFile getPortraitPathsFile() {
        return portraitPathsFile;
    }

    void setup() {
        CreateOutputFiles.getInstance();

        Console.writeHeader("Setup Output Manager");

        // Then read the path files
        audioPathsFile = ResourcePaths.readPathsFile(ResourcePaths.SOUND_TRACK_DIRECTORY);
        portraitPathsFile = ResourcePaths.readPathsFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY);

        // Then set up the manager objects
        //GameAudioManager.getInstance().loadAudioFiles(Enums.currentLocation.CURRENT_LOCATION_PERAGUS);
        GameAudioManager.getInstance();
        GameGUIManager.getInstance();

        dialogueListener = new DialogueListener();
        DialogueManager.getInstance().addListener(dialogueListener);

        Console.writeLine("Finished Setup Output Manager");
        Console.writeLine();

        // Load in the main menu screen
        new MainMenuGUI();
    }

    class DialogueListener implements DialogueEvent {
        @Override
        public void onDialogueSetup(DialogueTree dialogueTree){
            new DialogueGUI();
        }

        @Override
        public void onDialogueStart(DialogueTree dialogueTree) {
        }

        @Override
        public void onDialogueUpdate(String dialogueTreeID, DialogueObject dialogueObject) {
        }

        @Override
        public void onDialogueEnd() {
        }
    }
}
