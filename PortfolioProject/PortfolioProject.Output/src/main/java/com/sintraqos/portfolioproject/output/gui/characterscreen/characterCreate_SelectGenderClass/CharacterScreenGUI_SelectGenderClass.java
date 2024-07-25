package com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_SelectGenderClass;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.output.gui.characterscreen.CharacterScreenGUI_Base;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharacterScreenGUI_SelectGenderClass extends CharacterScreenGUI_Base {
    JPanel rootPanel;
    JPanel iconPanel;
    JButton iconMaleBackground;
    JLabel iconMale;
    JButton iconFemaleBackground;
    JLabel iconFemale;
    JPanel classPanel;
    JPanel classInfoPanel;

    JButton cancelButton;

    // Setup
    public CharacterScreenGUI_SelectGenderClass() {
        createBase(rootPanel, "Character Generation", false);

        // Create listeners
        // Select Male
        ActionListener selectMaleListener = e -> {
            new MainMenuGUI();
        };
        // Select Female
        ActionListener selectFemaleListener = e -> {
            new MainMenuGUI();
        };
        // Return to main menu
        ActionListener cancelListener = e -> {
            new MainMenuGUI();
        };

        int iconPadding = 0;

        // Create the three frames which hold the icons, buttons and text
        iconPanel = setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), getGuiHeight() * 2));

        // Male
        iconMaleBackground = setParent(iconPanel, addButton(getGuiHeight() * 2, getGuiHeight() * 2, "", selectMaleListener));
        iconMale = setParent(iconMaleBackground, addLabel((getGuiHeight() * 2) - iconPadding, (getGuiHeight() * 2) - iconPadding, "", ResourcePaths.PORTRAIT_DEFAULT_MALE_PATH, false));
        //iconMale.setMinimumSize(new Dimension((getGuiHeight() * 2) - iconPadding,(getGuiHeight() * 2) - iconPadding));

        // Female
        iconFemaleBackground = setParent(iconPanel, addButton(getGuiHeight() * 2, getGuiHeight() * 2, "", selectFemaleListener));
        iconFemale = setParent(iconFemaleBackground, addLabel((getGuiHeight() * 2) - iconPadding, (getGuiHeight() * 2) - iconPadding, "", ResourcePaths.PORTRAIT_DEFAULT_FEMALE_PATH, false));

        // Class Panel
        classPanel = setParent(getObjectPanel(), addPadding(addLabel(getGuiPanelWidth(), getGuiHeight() * 2, "class", ResourcePaths.LABEL_IMAGE), 0, getGuiHeight() / 2));
        classInfoPanel = setParent(getObjectPanel(), addPadding(addLabel(getGuiPanelWidth(), getGuiHeight() * 3, "class info", ResourcePaths.LABEL_IMAGE), 0, getGuiHeight() / 2));


        // Add buttons
        cancelButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Cancel", cancelListener));

        setWindowSize();
    }
}