package com.sintraqos.portfolioproject.output.gui.mainmenu;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.options.OptionsGUI;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.awt.event.*;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

public class MainMenuGUI extends GUIScreen {    // GUI Components
    private JPanel rootPanel;
    JPanel titleScreenLogo;
    JPanel newGameButton;
    JPanel loadGameButton;
    JPanel optionsButton;
    JPanel quitGameButton;

    int guiPadding = 25;

    // Setup
    public MainMenuGUI() {
        setup(rootPanel);

        // Logo
        titleScreenLogo = setParent(rootPanel,addUnscaledLabel((int) (640 * getGameGUIManager().getGUIScale()), (int) (320 * getGameGUIManager().getGUIScale()), "", ResourcePaths.TITLE_SCREEN_LOGO, (int) (guiPadding * getGameGUIManager().getGUIScale())));

        // Button

        // Create listeners
        // New Game
        ActionListener newGameListener = e -> {
        };
        // Load Game
        ActionListener loadGameListener = e -> {
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
        int fixedButtonPadding = (int) (guiPadding * getGameGUIManager().getGUIScale());

        newGameButton =         setParent(rootPanel,addButton(fixedButtonSizeX, fixedButtonSizeY, "New Game", fixedButtonPadding, newGameListener));
        loadGameButton =         setParent(rootPanel,addButton(fixedButtonSizeX, fixedButtonSizeY, "Load Game", fixedButtonPadding, loadGameListener));
        optionsButton =         setParent(rootPanel,addButton(fixedButtonSizeX, fixedButtonSizeY, "Options", fixedButtonPadding, optionsListener));
        quitGameButton =         setParent(rootPanel,addButton(fixedButtonSizeX, fixedButtonSizeY, "Quit Game", fixedButtonPadding, quitGameListener));

        // Apply settings to the window
        setWindowSize();

        // Play Audio
        getAudioManager().playAudio(ResourcePaths.OST_MAIN_MENU, Enums.audioType.AUDIO_TYPE_MUSIC);
    }
}
