package com.sintraqos.portfolioproject.output.gameGUI.CharacterScreen;

import com.sintraqos.portfolioproject.output.gameGUI.GameGUIManager;
import com.sintraqos.portfolioproject.output.gameGUI.GameSettings;
import com.sintraqos.portfolioproject.output.OutputManager;

import javax.swing.*;

public class CharacterScreenGUI {
    // GUI Components
    private JPanel rootPanel;

    // Setup
    public CharacterScreenGUI() {
        GameGUIManager gameGUIManager = GameGUIManager.getInstance();
        OutputManager outputManager = OutputManager.getInstance();
        GameSettings settings = outputManager.getSettings();

        JFrame frame = new JFrame(settings.windowName);                                     // Set window name
        frame.setContentPane(this.rootPanel);                                               // Set the window object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();                                                                                           // Make sure everything is properly set in the layout
        frame.setSize(settings.windowSizeX, settings.windowSizeY);      // Set the window size
        frame.setLocationRelativeTo(null);                                                                      // Center the window object
        frame.setVisible(true);
    }
}
