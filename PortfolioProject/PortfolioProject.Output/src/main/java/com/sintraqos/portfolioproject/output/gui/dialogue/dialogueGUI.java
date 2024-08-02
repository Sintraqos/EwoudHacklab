package com.sintraqos.portfolioproject.output.gui.dialogue;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.statics.*;

import javax.swing.*;

public class dialogueGUI extends GUIScreen{

    private JPanel rootPanel;
    JPanel personPanel;

    // Setup
    public dialogueGUI(int dialogueIndex) {
        setup(rootPanel);

        int guiPadding = (int) (25 * getGameGUIManager().getGUIScale());

        //TODO: Create panel with image for the talking entity, the current spoken dialogue and a row with all the possible choices at the bottom, each of those choice fields should be able to be disabled, and re-enabled when needed

        // Apply settings to the window
        setWindowSize();

        // Play Music
        GameAudioManager.getInstance().playAudio(ResourcePaths.OST_MAIN_MENU, Enums.audioType.AUDIO_TYPE_MUSIC);
        Console.writeLine("Loaded in Dialogue Panel");
    }

    public void playDialogueAudio(String audioName){
        GameAudioManager.getInstance().playAudio(audioName, Enums.audioType.AUDIO_TYPE_DIALOGUE);
    }

    public void playSoundTrackAudio(String audioName){
        GameAudioManager.getInstance().playAudio(audioName, Enums.audioType.AUDIO_TYPE_MUSIC);
    }
}
