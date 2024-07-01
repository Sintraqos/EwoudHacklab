package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;

public class OutputManager {
    // Get instance
    static OutputManager instance;

    public static OutputManager getInstance() {
        if (instance == null) {
            instance = new OutputManager();
            instance.Setup();
        }

        return instance;
    }

    void Setup() {
        GameAudioManager.getInstance();
        GameGUIManager.getInstance();
    }
}
