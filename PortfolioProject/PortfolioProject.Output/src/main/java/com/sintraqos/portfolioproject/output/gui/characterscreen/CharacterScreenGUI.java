package com.sintraqos.portfolioproject.output.gui.characterscreen;

import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CharacterScreenGUI extends CharacterScreenGUI_Base {
    JPanel rootPanel;

    ArrayList<JLabel> numberLabels;
    JPanel portraitButton;
    JPanel attributesButton;
    JPanel skillsButton;
    JPanel featsButton;
    JPanel nameButton;
    JPanel playButton;
    JButton cancelButton;
    JButton backButton;

    // Setup
    public CharacterScreenGUI() {
        createBase(rootPanel,"Character Generation");

        // Create listeners
        // Portrait
        ActionListener portraitListener = e -> {
        };
        // Attributes
        ActionListener attributesListener = e -> {
        };
        // Skills
        ActionListener skillsListener = e -> {
        };
        // Feats
        ActionListener featsListener = e -> {
        };
        // Name
        ActionListener nameListener = e -> {
        };
        // Load in the first gameplay scene with the selected character
        ActionListener playListener = e -> {
        };
        // Return to start of character create
        ActionListener cancelListener = e -> {
            new MainMenuGUI();
        };
        // Return to main menu
        ActionListener backListener = e -> {
            new MainMenuGUI();
        };

        // Add buttons
        portraitButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 * getGuiPadding()), getGuiHeight(), "Portrait", portraitListener),  getGuiPadding()));
        attributesButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Attributes", attributesListener),  getGuiPadding()));
        skillsButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Skills", skillsListener),  getGuiPadding()));
        featsButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Feats", featsListener),  getGuiPadding()));
        nameButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Name", nameListener),  getGuiPadding()));
        playButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Play", playListener),  getGuiPadding()));

        cancelButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Cancel", cancelListener));
        backButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Back", backListener));

        setWindowSize();
    }
}
