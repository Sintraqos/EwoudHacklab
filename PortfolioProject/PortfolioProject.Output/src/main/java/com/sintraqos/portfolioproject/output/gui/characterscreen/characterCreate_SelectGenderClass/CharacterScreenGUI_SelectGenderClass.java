package com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_SelectGenderClass;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.characterscreen.CharacterScreenGUI_Base;
import com.sintraqos.portfolioproject.output.gui.characterscreen.characterCreate_CreateCharacter.CharacterScreenGUI_CreateCharacter;
import com.sintraqos.portfolioproject.output.gui.mainmenu.MainMenuGUI;
import com.sintraqos.portfolioproject.statics.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CharacterScreenGUI_SelectGenderClass extends CharacterScreenGUI_Base {
    JPanel rootPanel;
    JPanel iconPanel;
    JButton iconMaleButton;
    JButton iconFemaleButton;
    JPanel classPanel;
    JButton classConsularButton;
    JButton classGuardianButton;
    JButton classSentinelButton;
    JLabel classInfoPanel;

    JPanel buttonPanel;
    JButton cancelButton;
    JButton confirmButton;

    boolean playerIsMale = false;
    Enums.playerClass playerClass = Enums.playerClass.PLAYER_CLASS_GUARDIAN;

    String classConsularInfo = "<html>Jedi Consulars Are Masters Of The Force<br/>And Spend Less Time On Combat Training<br/>(Recommended For Advanced Players Only)</html>";
    String classGuardianInfo = "<html>Jedi Guardians Focus On Combat Training<br/>And Lightsaber Mastery.</html>";
    String classSentinelInfo = "<html>Jedi Sentinels Are Well-Balanced<br/>And Possess Many Skills</html>";

    ActionListener selectMaleListener;
    ActionListener selectFemaleListener;
    ActionListener selectConsularListener;
    ActionListener selectGuardianListener;
    ActionListener selectSentinelListener;
    ActionListener cancelListener;
    ActionListener confirmListener;

    // Setup
    public CharacterScreenGUI_SelectGenderClass() {
        new Thread(this::setup).start();
    }

    void setup(){
        // Setup load screen
        createBase(rootPanel);

        // Load the needed GUI elements from the GUI Manager
        GameGUIManager.getInstance().loadPlayerPortraits();

        try {
            while (!GameGUIManager.getInstance().hasLoadedPortraits()){
                setLoadingText();
                Thread.sleep(GameSettings.getInstance().getGuiTickSpeed());
            }
        }
        catch (InterruptedException ex){
            throw new Functions.ExceptionHandler("Thread Interrupted",ex);
        }

        // Create the base
        setupScreen("Character Generation", false);

        // Set all text to titleCase
        classConsularInfo = Functions.toTitleCase(classConsularInfo);
        classGuardianInfo = Functions.toTitleCase(classGuardianInfo);
        classSentinelInfo = Functions.toTitleCase(classSentinelInfo);

        // Create listeners
        createListeners();

        int iconPadding = getGuiHeight() / 3;
        int iconSize = getGuiHeight() * 3;
        int objectPaddingY = getGuiHeight() / 3;

        // Create the three frames which hold the icons, buttons and text
        iconPanel = setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), iconSize + iconPadding + getGuiHeight()));
        setLayout(iconPanel, new GridBagLayout());

        // Male
        iconMaleButton = setParent(iconPanel, addButton(iconSize, iconSize, "", selectMaleListener, ResourcePaths.PORTRAIT_DEFAULT_MALE, iconPadding));

        // Center fill
        setParent(iconPanel, addJPanel(3 * iconSize, iconSize));

        // Female
        iconFemaleButton = setParent(iconPanel, addButton(iconSize, iconSize, "", selectFemaleListener, ResourcePaths.PORTRAIT_DEFAULT_FEMALE, iconPadding));

        // Class panel
        setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), objectPaddingY)); // Create padding object
        classPanel = setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), 3 * (iconSize + iconPadding)));
        setLayout(classPanel, new GridBagLayout());

        // Add class buttons
        classConsularButton = setParent(classPanel, addButton(iconSize, iconSize, "", selectConsularListener, ResourcePaths.GUI_CLASS_ICON_CONSULAR, iconPadding));
        setParent(classPanel, addJPanel(iconSize, iconSize));
        classGuardianButton = setParent(classPanel, addButton(iconSize, iconSize, "", selectGuardianListener, ResourcePaths.GUI_CLASS_ICON_GUARDIAN, iconPadding));
        setParent(classPanel, addJPanel(iconSize, iconSize));
        classSentinelButton = setParent(classPanel, addButton(iconSize, iconSize, "", selectSentinelListener, ResourcePaths.GUI_CLASS_ICON_SENTINEL, iconPadding));

        // Class info panel
        classInfoPanel = setParent(getObjectPanel(), addLabel((getGuiWidth() * 2)+ iconSize, getGuiHeight() * 3, "", ResourcePaths.LABEL_IMAGE));
        setClassInfoPanel();

        // Add buttons
        setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), objectPaddingY)); // Create padding object
        buttonPanel = setParent(getObjectPanel(), addJPanel(getGuiPanelWidth(), getGuiHeight()));
        setLayout(buttonPanel, new GridBagLayout());
        cancelButton = setParent(buttonPanel, addButton(getGuiWidth(), getGuiHeight(), "Cancel", cancelListener));
        setParent(buttonPanel, addJPanel(iconSize, getGuiHeight()));
        confirmButton = setParent(buttonPanel, addButton(getGuiWidth(), getGuiHeight(), "Confirm", confirmListener));

        // Set window size
        setWindowSize();

        // Play audio
        GameAudioManager.getInstance().playAudio(ResourcePaths.OST_CHARACTER_CREATE, Enums.audioType.AUDIO_TYPE_MUSIC);
        Console.writeLine("Loaded in Character Screen: Select Gender and Class panel");
    }

    void createListeners(){
        // Select Male
        selectMaleListener = e -> playerIsMale = true;
        // Select Female
        selectFemaleListener = e -> playerIsMale = false;
        // Select Female
        selectConsularListener = e -> {
            playerClass = Enums.playerClass.PLAYER_CLASS_CONSULAR;
            setClassInfoPanel();
        };
        // Select Female
        selectGuardianListener = e -> {
            playerClass = Enums.playerClass.PLAYER_CLASS_GUARDIAN;
            setClassInfoPanel();
        };
        // Select Female
        selectSentinelListener = e -> {
            playerClass = Enums.playerClass.PLAYER_CLASS_SENTINEL;
            setClassInfoPanel();
        };
        // Return to main menu
        cancelListener = e -> new MainMenuGUI();
        // Continue to character creation
        confirmListener = e -> new CharacterScreenGUI_CreateCharacter();
    }

    void setClassInfoPanel() {
        switch (playerClass) {
            case PLAYER_CLASS_CONSULAR:
                classInfoPanel.setText(classConsularInfo);
                break;
            case PLAYER_CLASS_GUARDIAN:
                classInfoPanel.setText(classGuardianInfo);
                break;
            case PLAYER_CLASS_SENTINEL:
                classInfoPanel.setText(classSentinelInfo);
                break;
        }
    }
}