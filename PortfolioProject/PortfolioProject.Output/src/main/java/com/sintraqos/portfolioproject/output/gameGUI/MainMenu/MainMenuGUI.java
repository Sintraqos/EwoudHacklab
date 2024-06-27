package com.sintraqos.portfolioproject.output.gameGUI.MainMenu;

import com.sintraqos.portfolioproject.output.gameGUI.GameSettings;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.output.ResourcePaths;
import com.sintraqos.portfolioproject.output.gameGUI.GameGUIManager;

import javax.swing.*;
import java.awt.event.*;

public class MainMenuGUI {    // GUI Components
    private JPanel rootPanel;
    private JLabel titleScreenLogo;
    private JButton newGameButton;
    private JButton loadGameButton;
    private JButton optionsButton;
    private JButton quitGameButton;

    // Setup
    public MainMenuGUI() {
        GameGUIManager gameGUIManager = GameGUIManager.getInstance();
        OutputManager outputManager = OutputManager.getInstance();
        GameSettings settings = outputManager.getSettings();

        JFrame frame = new JFrame(settings.windowName);                 // Set window name
        frame.setContentPane(this.rootPanel);                           // Set the window object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();                                                   // Make sure everything is properly set in the layout
        frame.setSize(settings.windowSizeX, settings.windowSizeY);      // Set the window size
        frame.setLocationRelativeTo(null);                              // Center the window object
        frame.setVisible(true);

        gameGUIManager.SetImage(titleScreenLogo, ResourcePaths.imagePath + ResourcePaths.mainMenuPath + ResourcePaths.titleScreenLogoName, 634, 370);

        SetupButtons(frame);
    }

    void SetupButtons(JFrame frame) {

        GameGUIManager gameGUIManager = GameGUIManager.getInstance();

        gameGUIManager.SetupButton(newGameButton);
        gameGUIManager.SetupButton(loadGameButton);
        gameGUIManager.SetupButton(optionsButton);
        gameGUIManager.SetupButton(quitGameButton);

        // Add button listeners

        // New Game
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Load Game
        loadGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Options
        optionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // Quit Game
        quitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowEvent closeWindowEvent = new WindowEvent(frame, WindowEvent.WINDOW_CLOSING);
                frame.dispatchEvent(closeWindowEvent);
            }
        });
    }
}
