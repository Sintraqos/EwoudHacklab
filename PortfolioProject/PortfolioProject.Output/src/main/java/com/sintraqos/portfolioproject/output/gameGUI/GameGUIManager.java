package com.sintraqos.portfolioproject.output.gameGUI;

import com.sintraqos.portfolioproject.output.gameGUI.MainMenu.MainMenuGUI;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.output.ResourcePaths;
import com.sintraqos.portfolioproject.statics.Enums;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameGUIManager {

    // Get instance
    static GameGUIManager instance;

    public static GameGUIManager getInstance() {
        if (instance == null) {
            instance = new GameGUIManager();

            // Setup
            instance.Setup();

            // Load the needed GUI
            new MainMenuGUI();
            //Main.gameAudioManager.PlayAudio(ResourcePaths.audioPath + ResourcePaths.soundTrackPath + ResourcePaths.mainMenuOSTName);
        }

        return instance;
    }

    Image buttonBaseImage;
    Image buttonClickImage;
    Image buttonHoverImage;
    Font font;

    GameSettings settings;

    void Setup() {
        settings = OutputManager.getInstance().getSettings();

        // UI Setup
        // Get button images
        buttonBaseImage = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.imagePath + ResourcePaths.uiElementPath + ResourcePaths.buttonBaseImageName))).getImage();
        buttonClickImage = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.imagePath + ResourcePaths.uiElementPath + ResourcePaths.buttonClickImageName))).getImage();
        buttonHoverImage = new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.imagePath + ResourcePaths.uiElementPath + ResourcePaths.buttonHoverImageName))).getImage();

        // Create new font
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.fontPath + ResourcePaths.fontFileName).getFile()));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);   // Register the font so it can be used
        } catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }

    //region Set Button

    public void SetupButton(JButton button) {
        // Set the button icon to the default image
        SetImage(button, buttonBaseImage);

        // Set the button font
        SetFont(button, font);
        SetFontSize(button, settings.defaultFontSize);

        // Setup events
        button.addMouseListener(new MouseAdapter() {
            // Click
            @Override
            public void mouseClicked(MouseEvent e) {

                ExecutorService executor = Executors.newScheduledThreadPool(1);          // Create new service
                executor.submit(new SetJButtonIcon(0, button, buttonClickImage));       // Switch to click image without delay
                executor.submit(new SetJButtonIcon(75, button, buttonHoverImage));     // Switch to hover image with small delay
                executor.shutdown();                                                                 // Dispose of executor

                // Play audio on click
                OutputManager.gameAudioManager.PlayOneShotAudio(ResourcePaths.guiClickName, Enums.audioType.AUDIO_TYPE_SFX);

                super.mouseClicked(e);
            }

            // Hover
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                ExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.submit(new SetJButtonIcon(0, button, buttonHoverImage));
                executor.shutdown();
            }

            // Base
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                ExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.submit(new SetJButtonIcon(0, button, buttonBaseImage));
                executor.shutdown();
            }
        });
    }

    // Create seperate task for switching icon of button, since using Thread.sleep doesn't work as desired
    static class SetJButtonIcon extends Thread {
        int sleepTime = 0;
        JButton button;
        Image image;

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            GameGUIManager.getInstance().SetImage(button, image);
        }

        public SetJButtonIcon(int sleepTime, JButton button, Image image) {
            this.sleepTime = sleepTime;
            this.button = button;
            this.image = image;
        }
    }

    //endregion

    //region Set Input Field

    public void SetInputField(JTextField inputField, String defaultText) {
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

    //region ---- JLabel

    // From Path
    public void SetImage(JLabel label, String filePath) {
        SetImage(label, filePath, settings.defaultIconSize);
    }

    public void SetImage(JLabel label, String filePath, int imageSize) {
        SetImage(label, filePath, imageSize, imageSize);
    }

    public void SetImage(JLabel label, String filePath, int imageWidth, int imageHeight) {
        label.setIcon(new ImageIcon(new ImageIcon(Thread.currentThread().getContextClassLoader().getResource(filePath)).getImage().getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH)));  // transform it back
    }

    // From Loaded Image
    public void SetImage(JLabel label, Image image) {
        SetImage(label, image, settings.defaultIconSize);
    }

    public void SetImage(JLabel label, Image image, int imageSize) {
        SetImage(label, image, imageSize, imageSize);
    }

    public void SetImage(JLabel label, Image image, int imageWidth, int imageHeight) {
        label.setIcon(new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH)));
    }

    //endregion

    //region ---- JButton

    public void SetImage(JButton button, Image image) {

        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(image));
    }

    public void SetImage(JButton button, Image image, int imageSize) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(image.getScaledInstance(imageSize, imageSize, java.awt.Image.SCALE_SMOOTH)));
    }

    public void SetImage(JButton button, Image image, int imageWidth, int imageHeight) {
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setIcon(new ImageIcon(image.getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH)));
    }

    //endregion

    //endregion

    //region Set Font

    //region ---- JLabel

    public void SetFont(JLabel label, String font){
        SetFont(label, font, settings.defaultFontSize);
    }

    public void SetFont(JLabel label, String font, int fontSize){
        System.out.println(font);
        try {
            label.setFont(Font.createFont(fontSize, Thread.currentThread().getContextClassLoader().getResourceAsStream(font)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    //endregion

    //region ---- JButton

    public void SetFont(JButton button, Font font){
        button.setFont(font);
    }

    //endregion

    //region ---- Font Size

    public void SetFontSize(JLabel label, int fontSize){
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), fontSize));
    }

    public void SetFontSize(JButton button, int fontSize){
        button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), fontSize));
    }

    //endregion

    //endregion
}

