package com.sintraqos.portfolioproject.output.gui.mainmenu;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.options.OptionsGUI;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.awt.event.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MainMenuGUI extends GUIScreen {    // GUI Components
    private JPanel rootPanel;
    JPanel titleScreenLogo;
    JPanel newGameButton;
    JPanel loadGameButton;
    JPanel musicButton;
    JPanel optionsButton;
    JPanel quitGameButton;

    // Setup
    public MainMenuGUI() {
        setup(rootPanel);

        int guiPadding = (int) (25 * getGameGUIManager().getGUIScale());

        // Logo
        titleScreenLogo = setParent(rootPanel, addPadding(addUnscaledLabel((int) (640 * getGameGUIManager().getGUIScale()), (int) (320 * getGameGUIManager().getGUIScale()), "", ResourcePaths.TITLE_SCREEN_LOGO), guiPadding));

        // Button

        // Create listeners
        // New Game
        ActionListener newGameListener = e -> {
        };
        // Load Game
        ActionListener loadGameListener = e -> {
        };
        // Music
        ActionListener musicListener = e -> {
        };
        // Options
        ActionListener optionsListener = e -> {
            new OptionsGUI();
        };
        // Quit Game
        ActionListener quitGameListener = e -> {
            WindowEvent closeWindowEvent = new WindowEvent(GameGUIManager.getInstance().getFrame(), WindowEvent.WINDOW_CLOSING);
            GameGUIManager.getInstance().getFrame().dispatchEvent(closeWindowEvent);
        };

        // Create buttons
        int fixedButtonSizeX = (int) (getSettings().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
        int fixedButtonSizeY = (int) (getSettings().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());

        newGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "New Game", newGameListener), guiPadding));
        loadGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Load Game", loadGameListener), guiPadding));
        musicButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Music", musicListener), guiPadding));
        optionsButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Options", optionsListener), guiPadding));
        quitGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Quit Game", quitGameListener), guiPadding));

        // Apply settings to the window
        setWindowSize();

        // Play Audio
        getAudioManager().playAudio(ResourcePaths.OST_MAIN_MENU, Enums.audioType.AUDIO_TYPE_MUSIC);
    }
}