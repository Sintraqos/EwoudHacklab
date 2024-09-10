package com.sintraqos.portfolioproject.output.gui.dialogue;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.statics.*;

import javax.swing.*;

public class dialogueGUI extends GUIScreen{

    private JPanel rootPanel;
    JPanel personPanel;
    JLabel topLabel;
    JLabel subtitles;
    private JPanel objectPanel;

    // Setup
    public dialogueGUI() {
        setup(rootPanel);

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setScaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND, getWindowSizeX(), getWindowSizeY());
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(getGuiPanelWidth(), getGuiPanelHeight()));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));


        // Add the label to the top
        subtitles = setParent(objectPanel, addLabel(getGuiPanelWidth(), getGuiHeight(), "Options", ResourcePaths.LABEL_IMAGE));

//        setup(rootPanel);
//
//        int guiPadding = (int) (25 * getGameGUIManager().getGUIScale());
//
//        //TODO: Create panel with image for the talking entity, the current spoken dialogue and a row with all the possible choices at the bottom, each of those choice fields should be able to be disabled, and re-enabled when needed
//
//        // Apply settings to the window
//        setWindowSize();
//
//        // Play Music
//        GameAudioManager.getInstance().playAudio(ResourcePaths.OST_MAIN_MENU, Enums.audioType.AUDIO_TYPE_MUSIC);
//        Console.writeLine("Loaded in Dialogue Panel");
    }
}
