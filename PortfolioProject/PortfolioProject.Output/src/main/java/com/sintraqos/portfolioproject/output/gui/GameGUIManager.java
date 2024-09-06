package com.sintraqos.portfolioproject.output.gui;

import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JFrameKeyboardListener;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.Console;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class GameGUIManager {

    // Get instance
    static GameGUIManager instance;

    public static GameGUIManager getInstance() {
        if (instance == null) {
            instance = new GameGUIManager();

            instance.setup();
        }

        return instance;
    }

    Font font;
    GUI_JFrameKeyboardListener frame;

    public GUI_JFrameKeyboardListener getFrame() {
        return frame;
    }

    ConcurrentHashMap<String, Image> baseSpites = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Image> modifiedSprites = new ConcurrentHashMap<>();
    ConcurrentHashMap<String, Image> portraitSprites = new ConcurrentHashMap<>();

    void setup() {
        Console.writeHeader("Setup GUI Manager");

        // Images Setup
        loadImages();

//        // Portraits Setup
        loadCompanionPortraits();
//
//        loadPlayerPortraits();

        // Create new font
        loadFont();

        // Load the needed GUI
        frame = new GUI_JFrameKeyboardListener(GameSettings.getInstance().getWindowName());

        Console.writeLine("Finished Setup GUI Manager");
        Console.writeLine();
    }

    //region Load files

    // Load Image
    void loadImages() {
        Console.writeHeader("Loading Images");

        // Title Screen
        baseSpites.put(ResourcePaths.TITLE_SCREEN_LOGO, Objects.requireNonNull(getImage(ResourcePaths.getImagePath(ResourcePaths.MAIN_MENU_DIRECTORY, ResourcePaths.TITLE_SCREEN_LOGO))));
        Console.writeLine("Loaded Title Screen Image");

        // Class Icons
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_CONSULAR, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.GUI_CLASS_ICON_CONSULAR))));
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_GUARDIAN, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.GUI_CLASS_ICON_GUARDIAN))));
        baseSpites.put(ResourcePaths.GUI_CLASS_ICON_SENTINEL, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.GUI_CLASS_ICON_SENTINEL))));
        Console.writeLine("Loaded Class Icon Images");

        // Default Portraits
        baseSpites.put(ResourcePaths.PORTRAIT_DEFAULT_MALE, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.PORTRAIT_DEFAULT_MALE))));
        baseSpites.put(ResourcePaths.PORTRAIT_DEFAULT_FEMALE, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.PORTRAIT_DEFAULT_FEMALE))));
        Console.writeLine("Loaded Default Portrait Images");

        // GUI Elements
        baseSpites.put(ResourcePaths.GUI_BACKGROUND, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.GUI_BACKGROUND))));
        baseSpites.put(ResourcePaths.BUTTON_BASE, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.BUTTON_BASE))));
        baseSpites.put(ResourcePaths.BUTTON_CLICK, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.BUTTON_CLICK))));
        baseSpites.put(ResourcePaths.BUTTON_HOVER, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.BUTTON_HOVER))));
        baseSpites.put(ResourcePaths.LABEL_IMAGE, Objects.requireNonNull(getImage(ResourcePaths.getUIImagePath(ResourcePaths.LABEL_IMAGE))));
        Console.writeLine("Loaded GUI Element Images");

        Console.writeLine("Finished Loading Images");
        Console.writeLine();
    }

    // Companion Portrait
    void loadCompanionPortraits() {
        Console.writeHeader("Loading Companion Portraits");

        HashMap<String, List<String>> companionPortraits = (HashMap<String, List<String>>) ResourcePaths.getPortraitCompanions();

        // Since we need to process a lot of files run a parallel loop, so it won't take too much time to process each file
        IntStream.range(0, companionPortraits.size()).parallel().forEach(i -> {
            List<String> currentPortraits = new ArrayList<>(companionPortraits.values()).get(i);
            IntStream.range(0, currentPortraits.size()).parallel().forEach(j -> addPortrait(Functions.getFileNameWithoutExtension(currentPortraits.get(j)), ResourcePaths.PORTRAIT_COMPANION_DIRECTORY));
        });

        Console.writeLine("Finished Loading Companion Portraits");
        Console.writeLine();
    }

    // Player Portrait

    boolean loadedPortraits;
    public boolean hasLoadedPortraits(){return loadedPortraits;}

    public void loadPlayerPortraits() {
        loadedPortraits = false;

        Console.writeHeader("Loading Player Portraits");

        loadPlayerPortraits(ResourcePaths.PORTRAIT_MALE_PREFIX);
        loadPlayerPortraits(ResourcePaths.PORTRAIT_FEMALE_PREFIX);

        Console.writeLine("Finished Loading Player Portraits");
        Console.writeLine();

        loadedPortraits = true;
    }

   void loadPlayerPortraits(String imagePrefix) {
        Console.writeLine("New portrait prefix: " + imagePrefix);
        List<String> fileNames = OutputManager.getInstance().getPortraitPathsFile().getFilePaths(imagePrefix);

        IntStream.range(0, fileNames.size()).parallel().forEach(i -> addPortrait(Functions.getFileNameWithoutExtension(fileNames.get(i)), ResourcePaths.PORTRAIT_PLAYER_DIRECTORY));

        Console.writeLine();
    }

    void addPortrait(String fileName, String locationDirectory) {
        Console.writeLine("Adding portrait: " + fileName);
        portraitSprites.put(fileName, Objects.requireNonNull(getImage(ResourcePaths.getImagePath(ResourcePaths.PORTRAIT_DIRECTORY, locationDirectory, fileName))));
    }

    public void disposePlayerPortraits() {
        portraitSprites.clear();
    }

    // Font
    void loadFont() {
        Console.writeHeader("Loading Font File");

        try {
            font = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream(ResourcePaths.getFontPath())));

            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);   // Register the font so it can be used
        } catch (FontFormatException | IOException ex) {
            throw new Functions.ExceptionHandler("Failed to get Font file", ex);
        }

        Console.writeLine("Finished Loading Font File");
        Console.writeLine();
    }

    //endregion

    //region Set Button

    public void setupButton(JButton button, int buttonWidth, int buttonHeight) {
        setImage(button, ResourcePaths.BUTTON_BASE, buttonWidth, buttonHeight);

        setButtonListeners(
                button,
                "",
                buttonWidth,
                buttonHeight,
                -1
        );
    }

    public void setupButton(JButton button, String overlayImageName, int buttonWidth, int buttonHeight, int padding) {
        setOverlappedImage(button, ResourcePaths.BUTTON_BASE, overlayImageName, buttonWidth, buttonHeight, padding);

        setButtonListeners(
                button,
                overlayImageName,
                buttonWidth,
                buttonHeight,
                padding
        );
    }

    void setButtonListeners(JButton button, String buttonOverlapName, int buttonWidth, int buttonHeight, int padding) {
        // Setup events

        // Click event
        button.addActionListener(_ -> {
            GameAudioManager.getInstance().playOneShotAudio(ResourcePaths.GUI_CLICK, Enums.audioType.AUDIO_TYPE_SFX);
            button.setForeground(StaticUtils.GUI_HOVER_TEXT_COLOR);
            new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_CLICK, buttonOverlapName, buttonWidth, buttonHeight, padding)).start();
            new Thread(new SetJButtonIcon(75, button, ResourcePaths.BUTTON_HOVER, buttonOverlapName, buttonWidth, buttonHeight, padding)).start();
        });

        // Mouse events
        button.addMouseListener(new MouseAdapter() {

            // Hover
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setForeground(StaticUtils.GUI_HOVER_TEXT_COLOR);

                new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_HOVER, buttonOverlapName, buttonWidth, buttonHeight, padding)).start();

                super.mouseEntered(e);
            }

            // Base
            @Override
            public void mouseExited(MouseEvent e) {
                button.setForeground(StaticUtils.GUI_FOREGROUND_COLOR);

                new Thread(new SetJButtonIcon(0, button, ResourcePaths.BUTTON_BASE, buttonOverlapName, buttonWidth, buttonHeight, padding)).start();

                super.mouseExited(e);
            }
        });
    }

    // Create separate task for switching icon of button, since using Thread.sleep doesn't work as desired
    static class SetJButtonIcon implements Runnable {
        int sleepTime = 0;
        JButton button;
        String baseImageName;
        String overlappedImageName;
        int buttonWidth;
        int buttonHeight;
        int padding;

        @Override
        public void run() {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new Functions.ExceptionHandler("Interrupted task", ex);
            }

            if (padding < 0 && overlappedImageName.isEmpty()) {
                GameGUIManager.getInstance().setImage(button, baseImageName, buttonWidth, buttonHeight);
            } else {
                GameGUIManager.getInstance().setOverlappedImage(button, baseImageName, overlappedImageName, buttonWidth, buttonHeight, padding);
            }
        }

        public SetJButtonIcon(int sleepTime, JButton button, String baseImageName, String overlappedImageName, int buttonWidth, int buttonHeight, int padding) {
            this.sleepTime = sleepTime;
            this.button = button;
            this.overlappedImageName = overlappedImageName;
            this.baseImageName = baseImageName;
            this.buttonWidth = buttonWidth;
            this.buttonHeight = buttonHeight;
            this.padding = padding;
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

    public void setScaledImage(JLabel label, String imageName, int imageWidth, int imageHeight) {
        label.setIcon(new ImageIcon(getScaledImage(imageName, imageWidth, imageHeight)));
        label.setOpaque(true);
    }

    public void setScaledImage(GUI_JPanelBackground panelBackground, String imageName, int imageWidth, int imageHeight) {
        panelBackground.setImage(new ImageIcon(getScaledImage(imageName, imageWidth, imageHeight)).getImage());
        panelBackground.setOpaque(true);
    }

    public void setOverlappedImage(JButton button, String baseImageName, String overlayImageName, int imageWidth, int imageHeight, int padding) {
        button.setIcon(new ImageIcon(getOverlappedImage(baseImageName, overlayImageName, imageWidth, imageHeight, padding)));
        button.setOpaque(true);
    }

    // Get Image
    Image getImage(String imagePath) {
        try {
            return ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResource(imagePath)));
        } catch (IOException exception) {
            return null;
        }
    }

    Image getImage(String imageName, int imageWidth, int imageHeight) {
        return modifiedSprites.computeIfAbsent(imageName + imageWidth + imageHeight, _ -> sliceImage(baseSpites.get(imageName), imageWidth, imageHeight));
    }

    Image getScaledImage(String imageName, int imageWidth, int imageHeight) {
        return baseSpites.get(imageName).getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH);
    }

    Image getOverlappedImage(String baseImageName, String overlayImageName, int imageWidth, int imageHeight, int padding) {
        return modifiedSprites.computeIfAbsent(baseImageName + overlayImageName + imageWidth + imageHeight, _ -> overlapImage(baseSpites.get(baseImageName), baseSpites.get(overlayImageName), imageWidth, imageHeight, padding));
    }

    //region Slice Image

    public BufferedImage sliceImage(Image image, int imageWidth, int imageHeight) {
        // First slice the image

        GameSettings settings = GameSettings.getInstance();

        // Get the image sliced, and return it into an BufferedImage array
        // Create a new buffered image which we can edit

        ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(settings.getDefaultIconSize(), settings.getDefaultIconSize(), Image.SCALE_SMOOTH));

        BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = bufferedImage.createGraphics();
        imageIcon.paintIcon(null, graphics, 0, 0);
        // And dispose of the graphics since we will overwrite it later on
        graphics.dispose();

        // Create a new array, since we know it needs to contain 9 parts a list isn't necessary
        BufferedImage[] slicedImage = new BufferedImage[9];

        // Get the needed image sizes
        int bufferedImageWidth = bufferedImage.getWidth();
        int bufferedImageHeight = bufferedImage.getHeight();
        // Get the needed slicedImage sizes
        int slicedImageWidth = bufferedImage.getWidth() / 3;
        int slicedImageHeight = bufferedImage.getHeight() / 3;

        // NOTE:
        // The reason we don't use slicedImageHeight * 3 is because we need to be certain we get all the pixels from the bottom, since 64 / 3 = 21,33, which gets rounded down to 21
        // imageHeight - slicedImageHeight gives a proper pixel position

        // Top from left to right
        slicedImage[0] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, 0, slicedImageWidth, slicedImageHeight);
        slicedImage[1] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, 0, slicedImageWidth * 2, slicedImageHeight);
        slicedImage[2] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, 0, bufferedImageWidth, slicedImageHeight);

        // Middle from left to right
        slicedImage[3] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, slicedImageHeight, slicedImageWidth, slicedImageHeight * 2);
        slicedImage[4] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, slicedImageHeight, slicedImageWidth * 2, slicedImageHeight * 2);
        slicedImage[5] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, slicedImageHeight, bufferedImageWidth, slicedImageHeight * 2);

        // Bottom from left to right
        slicedImage[6] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, 0, bufferedImageHeight - slicedImageHeight, slicedImageWidth, bufferedImageHeight);
        slicedImage[7] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, slicedImageWidth, bufferedImageHeight - slicedImageHeight, bufferedImageWidth - slicedImageWidth, bufferedImageHeight);
        slicedImage[8] = sliceImage(bufferedImage, slicedImageWidth, slicedImageHeight, bufferedImageWidth - slicedImageWidth, bufferedImageHeight - slicedImageHeight, bufferedImageWidth, bufferedImageHeight);

        // Create a new BufferedImage with the new image size
        BufferedImage combinedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        // Image Array Positions:
        // 0: Top Left
        // 1: Top Middle
        // 2: Top Right
        // 3: Middle Left
        // 4: Middle Middle
        // 5: Middle Right
        // 6: Bottom Left
        // 7: Bottom Middle
        // 8: Bottom Right

        // Since we don't want to stretch out the corners of the image, keep them their original size, this prevents weird artifacts
        // The reason for the padding is to prevent the middle parts from showing around the corners, this makes it seamless in theory
        graphics = combinedImage.getGraphics();
        graphics.drawImage(new ImageIcon(slicedImage[4].getScaledInstance(imageWidth - slicedImageHeight, imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, slicedImageHeight / 2, null);
        graphics.drawImage(new ImageIcon(slicedImage[1].getScaledInstance(imageWidth - slicedImageHeight, slicedImage[1].getHeight(), Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, 0, null);
        graphics.drawImage(new ImageIcon(slicedImage[7].getScaledInstance(imageWidth - slicedImageHeight, slicedImage[7].getHeight(), Image.SCALE_SMOOTH)).getImage(), slicedImageHeight / 2, imageHeight - slicedImage[1].getHeight(), null);
        graphics.drawImage(new ImageIcon(slicedImage[3].getScaledInstance(slicedImage[3].getWidth(), imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), 0, slicedImageHeight / 2, null);
        graphics.drawImage(new ImageIcon(slicedImage[5].getScaledInstance(slicedImage[5].getWidth(), imageHeight - slicedImageHeight, Image.SCALE_SMOOTH)).getImage(), imageWidth - slicedImage[5].getWidth(), slicedImageHeight / 2, null);

        // Finally add in the 4 corner pieces on top of the stretched parts
        graphics.drawImage(slicedImage[0], 0, 0, null);
        graphics.drawImage(slicedImage[2], imageWidth - slicedImage[2].getWidth(), 0, null);
        graphics.drawImage(slicedImage[6], 0, imageHeight - slicedImage[6].getHeight(), null);
        graphics.drawImage(slicedImage[8], imageWidth - slicedImage[2].getWidth(), imageHeight - slicedImage[8].getHeight(), null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return combinedImage;
    }

    BufferedImage sliceImage(BufferedImage bufferedImage, int imageWidth, int imageHeight, int sourceFirstX, int sourceFirstY, int dstCornerX, int dstCornerY) {
        // Create a new BufferedImage of the slice size
        BufferedImage returnImage = new BufferedImage(imageWidth, imageHeight, bufferedImage.getType());
        Graphics2D graphics = returnImage.createGraphics();
        // Get the image part of the original image, then copy that part onto the created image
        graphics.drawImage(bufferedImage, 0, 0, imageWidth, imageHeight, sourceFirstX, sourceFirstY, dstCornerX, dstCornerY, null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return returnImage;
    }

    //endregion

    //region Overlay Image

    public BufferedImage overlapImage(Image baseImageName, Image overlayImage, int imageWidth, int imageHeight, int padding) {
        BufferedImage overlappedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = overlappedImage.getGraphics();

        graphics.drawImage(sliceImage(baseImageName, imageWidth, imageHeight), 0, 0, null);
        graphics.drawImage(overlayImage.getScaledInstance(imageWidth - (padding * 2), imageHeight - (padding * 2), Image.SCALE_SMOOTH), padding, padding, null);

        // And dispose of the graphics since we don't need it anymore
        graphics.dispose();

        return overlappedImage;
    }

    //endregion

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
        baseSpites = new ConcurrentHashMap<>();
        modifiedSprites = new ConcurrentHashMap<>();
        portraitSprites = new ConcurrentHashMap<>();
        font = null;
    }

    //endregion
}
