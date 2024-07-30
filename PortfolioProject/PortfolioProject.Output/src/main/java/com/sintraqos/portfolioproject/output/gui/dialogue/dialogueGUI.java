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

        // Logo
        //personPanel = setParent(rootPanel, addPadding(addUnscaledLabel((int) (640 * getGameGUIManager().getGUIScale()), (int) (320 * getGameGUIManager().getGUIScale()), "", ResourcePaths.TITLE_SCREEN_LOGO), guiPadding));

        // Button

        // Create listeners
//        // New Game
//        ActionListener newGameListener = e -> {
//            new CharacterScreenGUI();
//        };
//        // Load Game
//        ActionListener loadGameListener = e -> {
//        };
//        // Music
//        ActionListener musicListener = e -> {
//        };
//        // Options
//        ActionListener optionsListener = e -> {
//            new OptionsGUI();
//        };
//        // Quit Game
//        ActionListener quitGameListener = e -> {
//            // Dispose all loaded files
//            GameGUIManager.getInstance().disposeFiles();
//            GameAudioManager.getInstance().disposeFiles();
//
//            WindowEvent closeWindowEvent = new WindowEvent(GameGUIManager.getInstance().getFrame(), WindowEvent.WINDOW_CLOSING);
//            GameGUIManager.getInstance().getFrame().dispatchEvent(closeWindowEvent);
//        };

        // Create buttons
//        int fixedButtonSizeX = (int) (getSettings().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
//        int fixedButtonSizeY = (int) (getSettings().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());
//
//        newGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "New Game", newGameListener), guiPadding));
//        loadGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Load Game", loadGameListener), guiPadding));
//        musicButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Music", musicListener), guiPadding));
//        optionsButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Options", optionsListener), guiPadding));
//        quitGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Quit Game", quitGameListener), guiPadding));

        // Apply settings to the window
        setWindowSize();

        // Play Music
        GameAudioManager.getInstance().playAudio(ResourcePaths.OST_MAIN_MENU, Enums.audioType.AUDIO_TYPE_MUSIC);
    }

    public void playDialogueAudio(String audioName){
        GameAudioManager.getInstance().playAudio(audioName, Enums.audioType.AUDIO_TYPE_DIALOGUE);
    }

    public void playSoundTrackAudio(String audioName){
        GameAudioManager.getInstance().playAudio(audioName, Enums.audioType.AUDIO_TYPE_MUSIC);
    }
}
