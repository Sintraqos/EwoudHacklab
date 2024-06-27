package com.sintraqos.portfolioproject.output.gameGUI;

public class GameSettings implements java.io.Serializable {

    // Window
    public String windowName = "Game Window";
    public int windowSizeX = 1600;
    public int windowSizeY = 1200;

    // Font
    public int defaultFontSize = 25;

    // Images
    public int defaultIconSize = 50;
    public int defaultButtonSizeX = 200;
    public int defaultButtonSizeY = 50;

    // Audio
    public float masterVolume = 1;
    public float musicVolume = 1;
    public float sfxVolume = 1;
    public float dialogueVolume = 1;

    public void SetGameSettings(GameSettings gameSettings) {
        // Window
        windowName = gameSettings.windowName;
        windowSizeX = gameSettings.windowSizeX;
        windowSizeY = gameSettings.windowSizeY;

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
    }
}
