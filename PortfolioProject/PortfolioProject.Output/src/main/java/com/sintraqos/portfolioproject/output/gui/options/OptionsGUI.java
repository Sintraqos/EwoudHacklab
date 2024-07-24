package com.sintraqos.portfolioproject.output.gui.options;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class OptionsGUI extends GUIScreen {
    private JPanel rootPanel;
    private JPanel contentPanel;
    private JPanel objectPanel;
    private JPanel buttonPanel;
    private JPanel textPanel;

    JLabel topLabel;
    JPanel gameplayButton;
    JPanel feedbackButton;
    JPanel autoPauseButton;
    JPanel graphicsButton;
    JPanel soundButton;
    JButton closeMenuButton;

    // Setup
    public OptionsGUI() {

        setup(rootPanel);

        // Create listeners
        // Game Play
        ActionListener gamePlayListener = e -> {
            //new OptionsGUI_Gameplay();
        };
        // Feedback
        ActionListener feedbackListener = e -> {
        };
        // Auto Pause
        ActionListener autoPauseListener = e -> {
        };
        // Graphics
        ActionListener graphicsListener = e -> {
        };
        // Sound
        ActionListener soundListener = e -> {
        };
        // Close Menu
        ActionListener closeMenuListener = e -> {
            new MainMenuGUI();
        };

        // Set sizes
        int windowSizeX = GameSettings.getInstance().getWindowSize().width;
        int windowSizeY = GameSettings.getInstance().getWindowSize().height;

        int panelPaddingX = (int) (175 * getGameGUIManager().getGUIScale());
        int panelPaddingY = (int) (75 * getGameGUIManager().getGUIScale());

        int guiHeight = (int) (GameSettings.getInstance().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());
        int guiWidth = (int) (GameSettings.getInstance().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
        int guiPadding = (int) (5 * getGameGUIManager().getGUIScale());

        int guiPanelWidth = windowSizeX - (panelPaddingX * 2);
        int guiPanelHeight = windowSizeY - (panelPaddingY * 2) - (guiHeight * 2);

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setUnscaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND, windowSizeX, windowSizeY);
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(guiPanelWidth, guiPanelHeight));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        // Add the label to the top
        topLabel = setParent(objectPanel, addLabel(guiPanelWidth, guiHeight, "Options", ResourcePaths.LABEL_IMAGE));
        // Create empty
        setParent(objectPanel, addJPanel(guiPanelWidth, guiPadding));
        contentPanel = setParent(objectPanel, addJPanel(guiPanelWidth, guiPanelHeight - (2 * (guiHeight + guiPadding))));
        setLayout(contentPanel, new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        buttonPanel = setParent(contentPanel, addJPanelBackground(guiPanelWidth / 2, guiPanelHeight, ResourcePaths.LABEL_IMAGE));
        setLayout(buttonPanel, new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        textPanel = setParent(contentPanel, addJPanelBackground(guiPanelWidth / 2, guiPanelHeight, ResourcePaths.LABEL_IMAGE));

        // Add buttons
        gameplayButton = setParent(buttonPanel, addPadding(addButton(guiPanelWidth / 2 - (2 * guiPadding), guiHeight, "Gameplay", gamePlayListener), guiPadding));
        feedbackButton = setParent(buttonPanel, addPadding(addButton(guiPanelWidth / 2 - (2 * guiPadding), guiHeight, "Feedback", feedbackListener), guiPadding));
        autoPauseButton = setParent(buttonPanel, addPadding(addButton(guiPanelWidth / 2 - (2 * guiPadding), guiHeight, "Auto-Pause", autoPauseListener), guiPadding));
        graphicsButton = setParent(buttonPanel, addPadding(addButton(guiPanelWidth / 2 - (2 * guiPadding), guiHeight, "Graphics", graphicsListener), guiPadding));

        closeMenuButton = setParent(objectPanel, addButton(guiWidth, guiHeight, "Close", closeMenuListener));

        setWindowSize();
    }
}
