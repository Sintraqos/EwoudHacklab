package com.sintraqos.portfolioproject.output.gui.mainmenu;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_SelectGenderClass.CharacterScreenGUI_SelectGenderClass;
import com.sintraqos.portfolioproject.output.gui.dialogue.DialogueGUI;
import com.sintraqos.portfolioproject.output.gui.options.OptionsGUI;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueManager;

import java.awt.event.*;

import java.awt.event.ActionListener;
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
        // Unload the unneeded GUI elements
        GameGUIManager.getInstance().disposePlayerPortraits();

        setup(rootPanel);

        // Logo
       // titleScreenLogo = setParent(rootPanel, addPadding(addUnscaledLabel((int) (640 * getGameGUIManager().getGUIScale()), (int) (320 * getGameGUIManager().getGUIScale()), "", ResourcePaths.TITLE_SCREEN_LOGO), getGuiPadding()));

        // Button

        // Create listeners
        // New Game
        ActionListener newGameListener = e -> {
            new CharacterScreenGUI_SelectGenderClass();
        };
        // Load Game
        ActionListener loadGameListener = e -> {
            //new DialogueGUI();
            DialogueManager.getInstance().TESTHandleDialogue();
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
            // Dispose all loaded files
            GameGUIManager.getInstance().disposeFiles();
            GameAudioManager.getInstance().disposeFiles();

            WindowEvent closeWindowEvent = new WindowEvent(GameGUIManager.getInstance().getFrame(), WindowEvent.WINDOW_CLOSING);
            GameGUIManager.getInstance().getFrame().dispatchEvent(closeWindowEvent);
        };

        // Create buttons
        int fixedButtonSizeX = (int) (GameSettings.getInstance().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
        int fixedButtonSizeY = (int) (GameSettings.getInstance().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());

        newGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "New Game", newGameListener), getGuiPadding()));
        loadGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Load Game", loadGameListener), getGuiPadding()));
        musicButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Music", musicListener), getGuiPadding()));
        optionsButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Options", optionsListener), getGuiPadding()));
        quitGameButton = setParent(rootPanel, addPadding(addButton(fixedButtonSizeX, fixedButtonSizeY, "Quit Game", quitGameListener), getGuiPadding()));

        // Apply settings to the window
        setWindowSize();

        // Play Audio
        GameAudioManager.getInstance().playMusicAudio(ResourcePaths.OST_MAIN_MENU);
        Console.writeLine("Loaded in main menu panel");
    }
}
