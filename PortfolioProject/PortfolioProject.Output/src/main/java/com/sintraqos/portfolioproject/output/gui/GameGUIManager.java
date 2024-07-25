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
import java.util.*;
import java.util.List;

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
    HashMap<String, Image> portraitSprites = new HashMap<>();

    void setup() {
        Console.writeHeader("Initializing GUI Manager");

        // Images Setup
        Console.writeHeader("Setup images");

        loadImages();

        Console.writeLine("Finished setting up images");
        Console.writeLine();

        // Portraits Setup
        Console.writeHeader("Setup portraits");

        loadCompanionPortraits();

        loadPlayerPortraits();

        Console.writeLine("Finished setting up portraits");
        Console.writeLine();

        // Create new font
        Console.writeHeader("Setup font");

        loadFont();

        Console.writeLine("Finished setting up font");
        Console.writeLine();

        Console.writeLine("Finished setting up GUI Manager");
        Console.writeLine();

        // Load the needed GUI
        frame = new GUI_KeyboardListener(GameSettings.getInstance().getWindowName());

        activeScreen = new MainMenuGUI();
    }

    //region Load files

    // Load Image
    void loadImages(){
        // Title Screen
        baseSpites.put(ResourcePaths.TITLE_SCREEN_LOGO, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.MAIN_MENU_PATH, ResourcePaths.TITLE_SCREEN_LOGO)));

        // Class Icons
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_CONSULAR, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.GUI_CLASS_ICON_CONSULAR)));
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_GUARDIAN, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.GUI_CLASS_ICON_GUARDIAN)));
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_SENTINEL, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.GUI_CLASS_ICON_SENTINEL)));

        // Default Portraits
        baseSpites.put(ResourcePaths.PORTRAIT_DEFAULT_MALE_PATH, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.PORTRAIT_DEFAULT_MALE_PATH)));
        baseSpites.put(ResourcePaths.PORTRAIT_DEFAULT_FEMALE_PATH, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.PORTRAIT_DEFAULT_FEMALE_PATH)));

        // GUI Elements
        baseSpites.put(ResourcePaths.GUI_BACKGROUND, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.GUI_BACKGROUND)));
        baseSpites.put(ResourcePaths.BUTTON_BASE_IMAGE, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.BUTTON_BASE_IMAGE)));
        baseSpites.put(ResourcePaths.BUTTON_CLICK_IMAGE, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.BUTTON_CLICK_IMAGE)));
        baseSpites.put(ResourcePaths.BUTTON_HOVER_IMAGE, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.BUTTON_HOVER_IMAGE)));
        baseSpites.put(ResourcePaths.LABEL_IMAGE, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.UI_ELEMENT_PATH, ResourcePaths.LABEL_IMAGE)));
    }

    // Companion Portrait
    void loadCompanionPortraits(){
        Console.writeLine("Getting companion portraits");

        for (Map.Entry<String, List<String>> entry : ResourcePaths.getPortraitCompanions().entrySet()) {
            for (String portraitName : entry.getValue()) {
                addPortrait(Functions.getFileNameWithoutExtension(portraitName), ResourcePaths.PORTRAIT_COMPANION_PATH);
            }
        }

        Console.writeLine("Finished getting companion portraits");
        Console.writeLine();
    }

    // Player Portrait
    void loadPlayerPortraits(){
        Console.writeLine("Getting player portraits");

        loadPlayerPortraits(ResourcePaths.PORTRAIT_MALE_PATH);
        loadPlayerPortraits(ResourcePaths.PORTRAIT_FEMALE_PATH);

        Console.writeLine("Finished getting player portraits");
        Console.writeLine();
    }

    void loadPlayerPortraits(String imagePrefix) {
        Console.writeHeader("New portrait prefix: " + imagePrefix);

        for (String fileName : Functions.getFiles(ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_PATH), imagePrefix)) {
            addPortrait(Functions.getFileNameWithoutExtension(fileName), ResourcePaths.PORTRAIT_PLAYER_PATH);
        }
    }

    void addPortrait(String fileName, String locationDirectory){
        portraitSprites.put(fileName, Functions.loadImage(ResourcePaths.getImagePath(ResourcePaths.PORTRAIT_PATH, locationDirectory, fileName)));
        Console.writeLine("Added portrait: " + fileName);
    }

    // Font
    void loadFont(){

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream(ResourcePaths.getFontPath())));

            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);   // Register the font so it can be used
        } catch (FontFormatException | IOException ex) {
            throw new Functions.ExceptionHandler("Failed to get Font file", ex);
        }
    }

    //endregion

    //region Set Button

    public void setupButton(JButton button, int buttonWidth, int buttonHeight) {
        setImage(button, ResourcePaths.BUTTON_BASE_IMAGE, buttonWidth, buttonHeight);

        // Setup events

        // Click event
        button.addActionListener(e -> {
            GameAudioManager.getInstance().playOneShotAudio(ResourcePaths.GUI_CLICK, Enums.audioType.AUDIO_TYPE_SFX);
            button.setForeground(StaticUtils.GUI_HOVER_TEXT_COLOR);
            new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_CLICK_IMAGE, buttonWidth, buttonHeight)).start();
            new Thread(new SetJButtonIcon(75, button, ResourcePaths.BUTTON_HOVER_IMAGE, buttonWidth, buttonHeight)).start();
        });

        // Mouse events
        button.addMouseListener(new MouseAdapter() {

            // Hover
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(StaticUtils.GUI_HOVER_TEXT_COLOR);

                new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_HOVER_IMAGE, buttonWidth, buttonHeight)).start();

                super.mouseEntered(e);
            }

            // Base
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);

                new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_BASE_IMAGE, buttonWidth, buttonHeight)).start();

                super.mouseExited(e);
            }
        });
    }

    // Create separate task for switching icon of button, since using Thread.sleep doesn't work as desired
    static class SetJButtonIcon implements Runnable {
        int sleepTime = 0;
        JButton button;
        String imageName;
        int buttonWidth;
        int buttonHeight;

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new Functions.ExceptionHandler("Interrupted task", ex);
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

    //region Set Image
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

    public void setUnscaledImage(JLabel label, String imageName, int imageWidth, int imageHeight) {
        label.setIcon(new ImageIcon(getUnscaledImage(imageName, imageWidth, imageHeight)));
        label.setOpaque(true);
    }

    public void setUnscaledImage(GUI_JPanelBackground panelBackground, String imageName, int imageWidth, int imageHeight) {
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
        return baseSpites.get(imageName).getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    }

    //endregion

    //region Font

    public void setFont(JLabel label) {
        label.setFont(font);
        setFontSize(label, (int) (GameSettings.getInstance().getDefaultFontSize() * getGUIScale()));
    }

    public void setFont(JButton button) {
        button.setFont(font);
        setFontSize(button, (int) (GameSettings.getInstance().getDefaultFontSize() * getGUIScale()));
    }

    public void setFont(JTextPane textPane) {
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

    public void setWindowSize(Dimension windowSize) {
        Console.writeLine(windowSize.toString());
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

    //region Set Cursor

    void setCursor(JPanel rootPanel, String cursorImage) {
        rootPanel.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(getImage(cursorImage, 32, 32), new Point(0, 0), cursorImage));
    }

    //endregion

    //region Dispose

    public void disposeFiles() {
        baseSpites = new HashMap<>();
        scaledSprites = new HashMap<>();
        portraitSprites = new HashMap<>();
        font = null;
    }

    //endregion
}
