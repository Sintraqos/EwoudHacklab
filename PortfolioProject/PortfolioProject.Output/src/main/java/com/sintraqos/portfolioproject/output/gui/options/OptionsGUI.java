package com.sintraqos.portfolioproject.output.gui.options;

import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Component.*;

public class OptionsGUI extends GUIScreen {
    private JPanel rootPanel;
    private JPanel objectPanel;
    private JPanel buttonPanel;
    private JPanel textPanel;

    JPanel topLabel;
    JPanel gameplayButton;
    JPanel feedbackButton;
    JPanel autoPauseButton;
    JPanel graphicsButton;
    JPanel soundButton;
    JPanel closeMenuButton;

    // Setup
    public OptionsGUI() {

        setup(rootPanel);

        // Create listeners
        // Game Play
        ActionListener gamePlayListener = e -> {
            new OptionsGUI_Gameplay();
        };
        // Feedback
        ActionListener feedbackListener = e -> {
        };
        // Auto Pause
        ActionListener autoPauseListener = e -> {
        };
        // Sound
        ActionListener graphicsListener = e -> {
        };
        // Sound
        ActionListener soundListener = e -> {
        };
        // Close Menu
        ActionListener closeMenuListener = e -> {
            new MainMenuGUI();
        };

        int windowSizeX = (int) (getSettings().getWindowSize().width * getGameGUIManager().getGUIScale());
        int windowSizeY = (int) (getSettings().getWindowSize().height * getGameGUIManager().getGUIScale());

        int panelPaddingX = (int) (175 * getGameGUIManager().getGUIScale());
        int panelPaddingY = (int) (125 * getGameGUIManager().getGUIScale());
        int panelSpacing = (int) (25 * getGameGUIManager().getGUIScale());

        int topLabelSizeX = windowSizeX - (panelPaddingX * 2);
        int topLabelSizeY = getSettings().getDefaultButtonSizeY();

        int objectPanelSizeX=windowSizeX - (panelPaddingX * 2);
        int objectPanelSizeY=windowSizeY - (panelPaddingY * 2) - (topLabelSizeY * 2);

        int objectPanelPosX=panelPaddingX ;
        int objectPanelPosY=panelPaddingX + panelSpacing;

        int buttonSizeX = (int) (getSettings().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
        int buttonPadding = (int) (25 * getGameGUIManager().getGUIScale());
        int closeButtonPosX = panelPaddingX;
        int closeButtonPosY = windowSizeY - panelPaddingY - (topLabelSizeY / 2);



        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setUnscaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND, windowSizeX, windowSizeY);
        setup(rootPanel,null);

        // Top Label
        topLabel =setParent(rootPanel,  addLabel(topLabelSizeX, topLabelSizeY, "Options", ResourcePaths.LABEL_IMAGE));
        topLabel.setBounds(panelPaddingX,panelPaddingY,topLabelSizeX, topLabelSizeY);

        // Button Panel
        buttonPanel = setParent(rootPanel,  addJPanelBackground((objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY, ResourcePaths.LABEL_IMAGE));
        setLayout(buttonPanel,null);
        buttonPanel.setBounds(objectPanelPosX,objectPanelPosY ,(objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY);

        // Text Panel
        //textPanel = setParent(rootPanel,  addLabel((objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY, "Test Text", ResourcePaths.LABEL_IMAGE,SwingConstants.LEADING,SwingConstants.TOP));
        textPanel = setParent(rootPanel,  addLabel((objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY, "Test Text", ResourcePaths.LABEL_IMAGE));
        setLayout(textPanel,null);
        textPanel.setBounds(objectPanelPosX + (objectPanelSizeX / 2) + panelSpacing,objectPanelPosY ,(objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY);

        //setParent(rootPanel,addTextPane((objectPanelSizeX /2) - (panelSpacing ), objectPanelSizeY, "Test Text"));

        // Close Button
        closeMenuButton = setParent(rootPanel,  addButton(buttonSizeX  /2, topLabelSizeY, "Close", closeMenuListener));
        closeMenuButton.setBounds(closeButtonPosX,closeButtonPosY,buttonSizeX /2 , topLabelSizeY);


//        gameplayButton =setParent(buttonPanel,   addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, buttonSizeX, "Gameplay", buttonPadding, gamePlayListener));
//        feedbackButton = setParent(buttonPanel,  addButton((windowSizeX - panelPaddingX -  buttonPadding) / 2, buttonSizeX, "Feedback", buttonPadding, feedbackListener));
//        autoPauseButton =setParent(buttonPanel,   addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, buttonSizeX, "Auto-Pause", buttonPadding, autoPauseListener));
//        graphicsButton = setParent(buttonPanel,  addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, buttonSizeX, "Graphics", buttonPadding, graphicsListener));
//        soundButton =setParent(buttonPanel,   addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, buttonSizeX, "Sound", buttonPadding, soundListener));

        //objectPanel =        setParent(rootPanel,addJPanelBackground(objectPanelSizeX, objectPanelSizeY, ResourcePaths.LABEL_IMAGE));
        //setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.X_AXIS));


//        // Button list
//        buttonPanel = addJPanelBackground((windowSizeX - panelPaddingX) / 2, windowSizeY-topLabelSizeY, ResourcePaths.LABEL_IMAGE);
//        setLayout(buttonPanel, new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
//        setParent(objectPanel, buttonPanel);
//
//        // Text list
//        textPanel = addJPanelBackground((windowSizeX - panelPaddingX) / 2, windowSizeY-topLabelSizeY, ResourcePaths.LABEL_IMAGE);
//        setLayout(textPanel, new FlowLayout());
//        setParent(objectPanel, textPanel);

//        // Text Object
//        setParent(textPanel, addTextPane((windowSizeX - panelPaddingX) / 2, windowSizeY-topLabelSizeY, "TEsT TExt"));
//
//        // Button
//
//        // Create listeners
//        // Game Play
//        ActionListener gamePlayListener = e -> {
//            new OptionsGUI_Gameplay();
//        };
//        // Feedback
//        ActionListener feedbackListener = e -> {
//        };
//        // Auto Pause
//        ActionListener autoPauseListener = e -> {
//        };
//        // Sound
//        ActionListener graphicsListener = e -> {
//        };
//        // Sound
//        ActionListener soundListener = e -> {
//        };
//        // Close Menu
//        ActionListener closeMenuListener = e -> {
//            new MainMenuGUI();
//        };

//        // Create buttons
//        int fixedButtonSizeX = (int) (getSettings().getDefaultButtonSizeX() * getGameGUIManager().getGUIScale());
//        int fixedButtonSizeY = (int) (getSettings().getDefaultButtonSizeY() * getGameGUIManager().getGUIScale());
//        buttonPadding = (int) (buttonPadding * getGameGUIManager().getGUIScale());
//
//        gameplayButton = addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, fixedButtonSizeY, "Gameplay", buttonPadding, gamePlayListener, buttonPanel);
//        feedbackButton = addButton((windowSizeX - panelPaddingX -  buttonPadding) / 2, fixedButtonSizeY, "Feedback", buttonPadding, feedbackListener, buttonPanel);
//        autoPauseButton = addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, fixedButtonSizeY, "Auto-Pause", buttonPadding, autoPauseListener, buttonPanel);
//        graphicsButton = addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, fixedButtonSizeY, "Graphics", buttonPadding, graphicsListener, buttonPanel);
//        soundButton = addButton((windowSizeX - panelPaddingX - buttonPadding) / 2, fixedButtonSizeY, "Sound", buttonPadding, soundListener, buttonPanel);
//
//
//        //setAlignment(closeMenuButton,SwingConstants.CENTER, SwingConstants.CENTER, CENTER_ALIGNMENT, CENTER_ALIGNMENT, SwingConstants.CENTER, SwingConstants.CENTER);
//        // Apply settings to the window
        setWindowSize();
    }
}
