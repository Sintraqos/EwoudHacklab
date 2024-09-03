package com.sintraqos.portfolioproject.statics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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
    private Dimension windowSize = new Dimension(1920, 1080);

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

    // Output
    private boolean logActive = true;

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

    // Output
    public boolean getLogActive() {
        return logActive;
    }

    //endregion

    // Set game settings
    public void setGameSettings(GameSettings gameSettings) {
        // Window
        instance.windowName = gameSettings.windowName;
        instance.windowSize = gameSettings.windowSize;

        // Font
        instance.defaultFontSize = gameSettings.defaultFontSize;

        // Images
        instance.defaultIconSize = gameSettings.defaultIconSize;
        instance.defaultButtonSizeX = gameSettings.defaultButtonSizeX;
        instance.defaultButtonSizeY = gameSettings.defaultButtonSizeY;

        // Audio
        instance.masterVolume = gameSettings.masterVolume;
        instance.musicVolume = gameSettings.musicVolume;
        instance.sfxVolume = gameSettings.sfxVolume;
        instance.dialogueVolume = gameSettings.dialogueVolume;

        // Tick speed
        instance.gameTickSpeed = gameSettings.gameTickSpeed;
        instance.guiTickSpeed = gameSettings.guiTickSpeed;

        // Input
        instance.keyBindings = gameSettings.keyBindings;

        // Output
        instance.logActive = gameSettings.logActive;
    }

    public void setWindowSize(Dimension windowSize) {
        this.windowSize = windowSize;
    }

    public int getKeyBinding(String key) {
        return KeyStroke.getKeyStroke(key.toUpperCase()).getKeyCode();
    }
}
