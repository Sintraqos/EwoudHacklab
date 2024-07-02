package com.sintraqos.portfolioproject.output.gui;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_KeyboardListener;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameGUIManager {

    GUIScreen activeScreen;

    // Get instance
    static GameGUIManager instance;

    public static GameGUIManager getInstance() {
        if (instance == null) {
            instance = new GameGUIManager();

            // Setup
            instance.setup();
        }

        return instance;
    }

    Font font;
    JFrame frame;

    public JFrame getFrame() {
        return frame;
    }

    // Image - Width
    HashMap<String, Image> baseSpites = new HashMap<>();
    HashMap<String, Image> scaledSprites = new HashMap<>();

    void setup() {
        Console.StringTitleOutput("Initializing GUI Manager");

        GameSettings.getInstance();

        // UI Setup
        baseSpites.put(ResourcePaths.TITLE_SCREEN_LOGO, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.MAIN_MENU_PATH + ResourcePaths.TITLE_SCREEN_LOGO + ResourcePaths.EXTENSION_IMAGE))).getImage());
        baseSpites.put(ResourcePaths.GUI_BACKGROUND, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.UI_ELEMENT_PATH + ResourcePaths.GUI_BACKGROUND + ResourcePaths.EXTENSION_IMAGE))).getImage());
        baseSpites.put(ResourcePaths.BUTTON_BASE_IMAGE, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.UI_ELEMENT_PATH + ResourcePaths.BUTTON_BASE_IMAGE + ResourcePaths.EXTENSION_IMAGE))).getImage());
        baseSpites.put(ResourcePaths.BUTTON_CLICK_IMAGE, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.UI_ELEMENT_PATH + ResourcePaths.BUTTON_CLICK_IMAGE + ResourcePaths.EXTENSION_IMAGE))).getImage());
        baseSpites.put(ResourcePaths.BUTTON_HOVER_IMAGE, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.UI_ELEMENT_PATH + ResourcePaths.BUTTON_HOVER_IMAGE + ResourcePaths.EXTENSION_IMAGE))).getImage());
        baseSpites.put(ResourcePaths.LABEL_IMAGE, new ImageIcon(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.IMAGE_PATH + ResourcePaths.UI_ELEMENT_PATH + ResourcePaths.LABEL_IMAGE + ResourcePaths.EXTENSION_IMAGE))).getImage());

        // Create new font
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(ResourcePaths.FONT_PATH + ResourcePaths.FONT_FILE_NAME + ResourcePaths.EXTENSION_FONT)).getFile()));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);   // Register the font so it can be used
        } catch (FontFormatException | IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }

        Console.StringOutput("Finished setting up GUI Manager");

        // Load the needed GUI
        frame = new GUI_KeyboardListener(GameSettings.getInstance().getWindowName());

        activeScreen = new MainMenuGUI();
    }

    //region Set Button

    public void setupButton(JButton button, int buttonWidth, int buttonHeight) {

        setImage(button, ResourcePaths.BUTTON_BASE_IMAGE, buttonWidth, buttonHeight);

        // Setup events
        button.addMouseListener(new MouseAdapter() {
            // Click
            @Override
            public void mouseClicked(MouseEvent e) {
                button.setForeground(Statics.GUI_HOVER_TEXT_COLOR);

                ExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.submit(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_CLICK_IMAGE, buttonWidth, buttonHeight));      // Switch to click image without delay
                executor.submit(new SetJButtonIcon(75, button, ResourcePaths.BUTTON_HOVER_IMAGE, buttonWidth, buttonHeight));     // Switch to hover image with small delay
                executor.shutdown();                                                                 // Dispose of executor
                // Create new service

                // Play audio on click
                GameAudioManager.getInstance().playOneShotAudio(ResourcePaths.GUI_CLICK, Enums.audioType.AUDIO_TYPE_SFX);

                super.mouseClicked(e);
            }

            // Hover
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(Statics.GUI_HOVER_TEXT_COLOR);

                ExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.submit(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_HOVER_IMAGE, buttonWidth, buttonHeight));
                executor.shutdown();

                super.mouseEntered(e);
            }

            // Base
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(Statics.GUI_FOREGROUND_COLOR);

                ExecutorService executor = Executors.newScheduledThreadPool(1);
                executor.submit(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_BASE_IMAGE, buttonWidth, buttonHeight));
                executor.shutdown();

                super.mouseExited(e);
            }
        });
    }

    // Create seperate task for switching icon of button, since using Thread.sleep doesn't work as desired
    static class SetJButtonIcon extends Thread {
        int sleepTime = 0;
        JButton button;
        String imageName;
        int buttonWidth;
        int buttonHeight;

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            GameGUIManager.getInstance().setImage(button, imageName, buttonWidth, buttonHeight);
        }

        public SetJButtonIcon(int sleepTime, JButton button, String imageName, int buttonWidth, int buttonHeight) {
            this.sleepTime = sleepTime;
            this.button = button;
            this.imageName = imageName;
            this.buttonWidth = buttonWidth;
            this.buttonHeight = buttonHeight;
        }
    }

    //endregion

    //region Set Input Field

    public void setInputField(JTextField inputField, String defaultText) {
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

    //region Get / Set Image

    // Set Image
    public void setImage(GUI_JPanelBackground panel, String imageName, int imageWidth, int imageHeight) {
        panel.setImage(getImage(imageName, imageWidth, imageHeight));
        panel.setOpaque(true);
    }

    public void setImage(JButton button, String imageName, int imageWidth, int imageHeight) {
        button.setIcon(new ImageIcon(getImage(imageName, imageWidth, imageHeight)));
        button.setOpaque(true);
    }

    public void setImage(JLabel label, String imageName, int imageWidth, int imageHeight) {
        label.setIcon(new ImageIcon(getImage(imageName, imageWidth, imageHeight)));
        label.setOpaque(true);
    }

    public void setUnscaledImage(JLabel label, String imageName,int imageWidth, int imageHeight){
        label.setIcon(new ImageIcon(getUnscaledImage(imageName, imageWidth, imageHeight)));
        label.setOpaque(true);
    }

    public void setUnscaledImage(GUI_JPanelBackground panelBackground, String imageName,int imageWidth, int imageHeight){
        panelBackground.setImage(new ImageIcon(getUnscaledImage(imageName, imageWidth, imageHeight)).getImage());
        panelBackground.setOpaque(true);
    }

    // Get Image
    Image getImage(String imageName, int imageWidth, int imageHeight) {
        if (!scaledSprites.containsKey(imageName + imageWidth + imageHeight)) {
            scaledSprites.put(imageName + imageWidth + imageHeight, Functions.sliceImage(baseSpites.get(imageName), imageWidth, imageHeight));
        }

        return scaledSprites.get(imageName + imageWidth + imageHeight);
    }

    Image getUnscaledImage(String imageName, int imageWidth, int imageHeight) {
        return baseSpites.get(imageName).getScaledInstance(imageWidth, imageHeight, java.awt.Image.SCALE_SMOOTH);
    }

    //endregion

    //region Set Font

    public void setFont(JLabel label) {
        label.setFont(font);
        setFontSize(label, (int) (GameSettings.getInstance().getDefaultFontSize() * getGUIScale()));
    }

    public void setFont(JButton button) {
        button.setFont(font);
        setFontSize(button, (int) (GameSettings.getInstance().getDefaultFontSize() * getGUIScale()));
    }

    public  void setFont(JTextPane textPane){
        textPane.setFont(font);
        setFontSize(textPane, (int) (GameSettings.getInstance().getDefaultFontSize() * getGUIScale()));
    }

    public void setFontSize(JLabel label, int fontSize) {
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), (int) (fontSize * getGUIScale())));
    }

    public void setFontSize(JButton button, int fontSize) {
        button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), (int) (fontSize * getGUIScale())));
    }
    public void setFontSize(JTextPane textPane, int fontSize) {
        textPane.setFont(new Font(textPane.getFont().getName(), textPane.getFont().getStyle(), (int) (fontSize * getGUIScale())));
    }
    //endregion

    //region Set Window Size

    public void SetWindowSize(Dimension windowSize) {
        Console.StringOutput(windowSize.toString());
        GameSettings.getInstance().setWindowSize(windowSize);
    }

    public float getGUIScale() {
//       System.out.println("Window Size X: "  + (float) GameSettings.getInstance().getWindowSize().windowSizeX);
//        System.out.println("GUI Scale: "  + (float) GameSettings.getInstance().getWindowSize().windowSizeX / 1920);
//        System.out.println("GUI Scale: "  + 1920 / (float) GameSettings.getInstance().getWindowSize().windowSizeX);
        // return (float) GameSettings.getInstance().getWindowSize().windowSizeX / 1920;
        return 1f;
    }

    //endregion
}
