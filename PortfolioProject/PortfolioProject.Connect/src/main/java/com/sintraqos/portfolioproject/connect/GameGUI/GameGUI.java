package com.sintraqos.portfolioproject.connect.GameGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class GameGUI {

    // GUI Components
    private JPanel rootPanel;
    private JButton registerButton;
    private JLabel registerLabel;
    private JTextField accountNameInputField;
    private JTextField passwordInputField;
    private JLabel TESTImage;

    // Settings
    static String windowName = "Game Window";

    // input fields text
    String accountInputFieldDefaultText = "Enter Account Name...";
    String passwordInputFieldDefaultText = "Enter Password...";

    // Images
    int defaultImageSize = 50;

    // Main paths
    String imagePath = "images/";
    String itemImagePath = "itemIcon/";

    // Image names
    String TESTImageName = "warcraft-icon-20.jpg";

    public static void main(String[] args) {
        JFrame frame = new JFrame(windowName);                  // Set window name
        frame.setContentPane(new GameGUI().rootPanel);          // Set the window object
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);                      // Center the window object
        frame.setVisible(true);
    }

    public GameGUI() {

        accountNameInputField.setToolTipText("Please enter some text here");

        SetButton(registerButton);

        SetInputField(accountNameInputField, accountInputFieldDefaultText);
        SetInputField(passwordInputField, passwordInputFieldDefaultText);

        SetImage(TESTImage, TESTImageName);
    }

    //region Set Button

    void SetButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    //endregion

    //region Set Input Field

    void SetInputField(JTextField inputField, String defaultText) {
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if (inputField.getText().equals(defaultText)) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }

            }

            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText(defaultText);
                }
            }
        });

        inputField.setFocusable(false);
    }

//endregion

    //region Set Image

    void SetImage(JLabel labelObject, String fileName) {
        SetImage(labelObject, fileName, defaultImageSize);
    }

    void SetImage(JLabel labelObject, String fileName, int imageSize) {
        SetImage(labelObject, fileName, imageSize, imageSize);
    }

    void SetImage(JLabel labelObject, String fileName, int imageWidth, int imageHeight) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        Image image = new ImageIcon(classloader.getResource(imagePath + fileName)).getImage(); // transform it
        labelObject.setIcon(new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH)));  // transform it back
    }

    //endregion
}
