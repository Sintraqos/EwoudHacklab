package com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_SelectGenderClass;

import com.sintraqos.portfolioproject.output.gui.characterscreen.CharacterScreenGUI_Base;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharacterScreenGUI_SelectGenderClass extends CharacterScreenGUI_Base {
    JPanel rootPanel;
    JPanel iconPanel;
    JButton iconMaleButton;
    JLabel iconMale;
    JButton iconFemaleButton;
    JLabel iconFemale;
    JLabel classPanel;
    JLabel classInfoPanel;

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

        int iconPadding = getGuiHeight() / 3;
        int iconSize = getGuiHeight() * 3;
        int objectPaddingX = 0;
        int objectPaddingY = getGuiHeight() / 3;

        // Create the three frames which hold the icons, buttons and text
        iconPanel = setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), iconSize + iconPadding + getGuiHeight()));
        setLayout(iconPanel, new GridBagLayout());

        // Male
        iconMaleButton = setParent(iconPanel, addButton(iconSize, iconSize, "", selectMaleListener,ResourcePaths.PORTRAIT_DEFAULT_MALE_PATH,iconPadding ));

        // Center fill
        setParent(iconPanel, addJPanel(getGuiPanelWidth() - (3 * (iconSize + iconPadding)), iconSize));

        // Female
        iconFemaleButton = setParent(iconPanel, addButton(iconSize, iconSize, "", selectFemaleListener,ResourcePaths.PORTRAIT_DEFAULT_FEMALE_PATH,iconPadding ));

        // Class Panel
        classPanel = setParent(getObjectPanel(), addLabel(getGuiPanelWidth(), getGuiHeight() * 2, "Class", ResourcePaths.LABEL_IMAGE));
        classInfoPanel = setParent(getObjectPanel(), addLabel(getGuiPanelWidth(), getGuiHeight() * 3, "Class Info", ResourcePaths.LABEL_IMAGE));

        // Add buttons
        cancelButton = setParent(getObjectPanel(), addButton(getGuiWidth(), getGuiHeight(), "Cancel", cancelListener));

        setWindowSize();
    }
}