package com.sintraqos.portfolioproject.statics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameSettings implements java.io.Serializable {

    // Get instance
    static GameSettings instance;

    public static GameSettings getInstance() {
        if (instance == null) {
            instance = new GameSettings();
        }

        return instance;
    }

    GameSettings() {
        // Movement
        keyBindings.put("Move Forward", KeyEvent.getKeyText(KeyEvent.VK_W));
        keyBindings.put("Move Left", KeyEvent.getKeyText(KeyEvent.VK_A));
        keyBindings.put("Move Right", KeyEvent.getKeyText(KeyEvent.VK_D));
        keyBindings.put("Move Back", KeyEvent.getKeyText(KeyEvent.VK_S));

        // Miscellaneous
        keyBindings.put("Pause", KeyEvent.getKeyText(KeyEvent.VK_SPACE));

        // Menus
        keyBindings.put("Inventory", KeyEvent.getKeyText(KeyEvent.VK_I));
        keyBindings.put("Character Screen", KeyEvent.getKeyText(KeyEvent.VK_C));
        keyBindings.put("Escape Menu", KeyEvent.getKeyText(KeyEvent.VK_ESCAPE));
    }

    // Window
    private String windowName = "Game Window";
    private Dimension windowSize = new Dimension(1024, 576);

    // Font
    private int defaultFontSize = 25;

    // Images
    private int defaultIconSize = 64;
    private int defaultButtonSizeX = 512;
    private int defaultButtonSizeY = 64;

    // Audio
    private float masterVolume = 1;
    private float musicVolume = 1;
    private float sfxVolume = 1;
    private float dialogueVolume = 1;

    // Tick speed
    private int gameTickSpeed = 60;  // How many times per second the game gets updated
    private int guiTickSpeed = 60;   // How many times per second certain GUI elements gets updated

    // Input
    private Map<String, String> keyBindings = new LinkedHashMap<>();

    //region Public variables

    // Window
    public String getWindowName() {
        return windowName;
    }

    public Dimension getWindowSize() {
        return windowSize;
    }

    // Font
    public int getDefaultFontSize() {
        return defaultFontSize;
    }

    // Images
    public int getDefaultIconSize() {
        return defaultIconSize;
    }

    public int getDefaultButtonSizeX() {
        return defaultButtonSizeX;
    }

    public int getDefaultButtonSizeY() {
        return defaultButtonSizeY;
    }

    // Volume
    public float getMasterVolume() {
        return masterVolume;
    }

    public float getMusicVolume() {
        return musicVolume;
    }

    public float getSfxVolume() {
        return sfxVolume;
    }

    public float getDialogueVolume() {
        return dialogueVolume;
    }

    // Tick Speed
    public int getGameTickSpeed() {
        return gameTickSpeed;
    }

    public int getGuiTickSpeed() {
        return guiTickSpeed;
    }

    // Input
    public Map<String, String> getKeyBindings() {
        return keyBindings;
    }

    //endregion

    // Set game settings
    public void setGameSettings(GameSettings gameSettings) {
        // Window
        windowName = gameSettings.windowName;
        windowSize = gameSettings.windowSize;

        // Font
        defaultFontSize = gameSettings.defaultFontSize;

        // Images
        defaultIconSize = gameSettings.defaultIconSize;
        defaultButtonSizeX = gameSettings.defaultButtonSizeX;
        defaultButtonSizeY = gameSettings.defaultButtonSizeY;

        // Audio
        masterVolume = gameSettings.masterVolume;
        musicVolume = gameSettings.musicVolume;
        sfxVolume = gameSettings.sfxVolume;
        dialogueVolume = gameSettings.dialogueVolume;

        // Tick speed
        gameTickSpeed = gameSettings.gameTickSpeed;
        guiTickSpeed = gameSettings.guiTickSpeed;

        // Input
        keyBindings = gameSettings.keyBindings;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }

    public int getKeyBinding(String key) {
        return KeyStroke.getKeyStroke(key.toUpperCase()).getKeyCode();
    }
}
