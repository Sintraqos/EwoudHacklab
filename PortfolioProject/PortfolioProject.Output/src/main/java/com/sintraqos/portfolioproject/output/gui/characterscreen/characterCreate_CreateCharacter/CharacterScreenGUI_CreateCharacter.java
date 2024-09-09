package com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_CreateCharacter;

import com.sintraqos.portfolioproject.output.gui.characterscreen.CharacterScreenGUI_Base;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.Console;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CharacterScreenGUI_CreateCharacter extends CharacterScreenGUI_Base {
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

    ActionListener portraitListener;
    ActionListener attributesListener;
    ActionListener skillsListener;
    ActionListener featsListener;
    ActionListener nameListener;
    ActionListener playListener;
    ActionListener cancelListener;
    ActionListener backListener;

    // Setup
    public CharacterScreenGUI_CreateCharacter() {new Thread(this::setup).start();
    }

    void setup(){
        // Setup load screen
        createBase(rootPanel);

        // Create the base
        setupScreen("Character Generation", true);

        // Create listeners
        createListeners();

        // Add buttons
        portraitButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 * getGuiPadding()), getGuiHeight(), "Portrait", portraitListener),  getGuiPadding()));
        attributesButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Attributes", attributesListener),  getGuiPadding()));
        skillsButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Skills", skillsListener),  getGuiPadding()));
        featsButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Feats", featsListener),  getGuiPadding()));
        nameButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Name", nameListener),  getGuiPadding()));
        playButton = setParent(getRightPanel(), addPadding(addButton(getGuiPanelWidth() / 2 - (2 *  getGuiPadding()), getGuiHeight(), "Play", playListener),  getGuiPadding()));

        cancelButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Cancel", cancelListener));
        backButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Back", backListener));

        // Set window size
        setWindowSize();
        Console.writeLine("Loaded in Character Screen: Create Character");
    }

    void createListeners(){
        // Portrait
        portraitListener = e -> {
        };
        // Attributes
        attributesListener = e -> {
        };
        // Skills
        skillsListener = e -> {
        };
        // Feats
        featsListener = e -> {
        };
        // Name
        nameListener = e -> {
        };
        // Load in the first gameplay scene with the selected character
        playListener = e -> {
        };
        // Return to start of character create
        cancelListener = e -> {
            new MainMenuGUI();
        };
        // Return to main menu
        backListener = e -> {
            new MainMenuGUI();
        };
    }
}
