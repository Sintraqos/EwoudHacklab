package com.sintraqos.portfolioproject.output.gameGUI;

import com.sintraqos.portfolioproject.output.OutputManager;

import javax.swing.*;

public class GameGUI {

    // GUI Components
    private JPanel rootPanel;
    private JButton registerButton;
    private JLabel registerLabel;
    private JTextField accountNameInputField;
    private JTextField passwordInputField;
    private JLabel TESTImage;

    // input fields text
    String accountInputFieldDefaultText = "Enter Account Name...";
    String passwordInputFieldDefaultText = "Enter Password...";

    public GameGUI() {
        GameGUIManager gameGUIManager = GameGUIManager.getInstance();
        OutputManager outputManager = OutputManager.getInstance();
        GameSettings settings = outputManager.getSettings();

        // Apply settings
        JFrame frame = new JFrame(settings.windowName);                                     // Set window name
        frame.setContentPane(this.rootPanel);                                                          // Set the window object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();                                                                                           // Make sure everything is properly set in the layout
        frame.setSize(settings.windowSizeX, settings.windowSizeY);      // Set the window size
        frame.setLocationRelativeTo(null);                                                                      // Center the window object
        frame.setVisible(true);

        // Setup the components
        accountNameInputField.setToolTipText("Please enter some text here");

        gameGUIManager.SetupButton(registerButton);

        gameGUIManager.SetInputField(accountNameInputField, accountInputFieldDefaultText);
        gameGUIManager.SetInputField(passwordInputField, passwordInputFieldDefaultText);
    }
}
