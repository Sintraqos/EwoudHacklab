package com.sintraqos.portfolioproject.output.gui.dialogue;

import com.sintraqos.portfolioproject.output.audio.GameAudioManager;
import com.sintraqos.portfolioproject.output.gui.GUIScreen;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.output.gui.guicomponents.GUI_JPanelBackground;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueEvent;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueObject;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueTree;

import javax.swing.*;

public class DialogueGUI extends GUIScreen {
    DialogueManager dialogueManager;

    private JPanel rootPanel;
    JPanel personPanel;
    JLabel topLabel;
    JLabel subtitles;
    private JPanel objectPanel;

    // Setup
    public DialogueGUI() {
        new DialogueListener().setup(this);
        Console.writeLine("Setup Dialogue Panel");

        // Setup load screen
        setup(rootPanel);
        dialogueManager = DialogueManager.getInstance();

        // Background
        rootPanel = new GUI_JPanelBackground();
        getGameGUIManager().setScaledImage((GUI_JPanelBackground) rootPanel, ResourcePaths.GUI_BACKGROUND, getWindowSizeX(), getWindowSizeY());
        setup(rootPanel);

        // Create panel that contains all gui elements
        objectPanel = setParent(rootPanel, addJPanel(getGuiPanelWidth(), getGuiPanelHeight()));
        setLayout(objectPanel, new BoxLayout(objectPanel, BoxLayout.Y_AXIS));

        // Add the label to the top
        subtitles = setParent(objectPanel, addLabel(getGuiPanelWidth(), getGuiHeight() * 2, "Dialogue Subtitles", ResourcePaths.LABEL_IMAGE));
        // TODO: Add a scroll view to the bottom of the screen and fill it when needed with the given variables

//        setup(rootPanel);
//
//        int guiPadding = (int) (25 * getGameGUIManager().getGUIScale());
//
//        //TODO: Create panel with image for the talking entity, the current spoken dialogue and a row with all the possible choices at the bottom, each of those choice fields should be able to be disabled, and re-enabled when needed
//
        // Apply settings to the window
        setWindowSize();

        Console.writeLine("Loaded in Dialogue Panel");

        new Thread(this::startDialogue).start();
    }

    void startDialogue() {
        dialogueManager.handleDialogueStart();
    }

    void setDialogueOptions() {
        for (DialogueObject dialogueObject : dialogueManager.getCurrentBranches()) {
            Console.writeLine("Dialogue Option: " + dialogueObject.getDialogueID());
        }
    }

    void setDialogueText(String dialogueOwner, String dialogueText) {

        String subtitlesText ="<html>"+ dialogueOwner + ": " + dialogueText + "</html>"; // Convert the string to an HTML lines
        Console.writeLine(dialogueOwner + ": " + dialogueText);
        subtitles.setText(subtitlesText);

        //TODO: check the max length of characters the textbox will hold, then add the remaining text to a new line

        //<html>Text line 1<br/>Text line 2, after newLine.</html>
    }

    class DialogueListener implements DialogueEvent {
        DialogueGUI dialogueGUI;

        public void setup(DialogueGUI dialogueGUI) {
            this.dialogueGUI = dialogueGUI;
            DialogueManager.getInstance().addListener(this);
        }

        @Override
        public void onDialogueSetup(DialogueTree dialogueTree) {

        }

        @Override
        public void onDialogueStart(DialogueTree dialogueTree) {
        }

        @Override
        public void onDialogueUpdate(String dialogueTreeID, DialogueObject dialogueObject) {
            dialogueGUI.setDialogueText(dialogueObject.getDialogueOwner(), dialogueObject.getDialogueText());
        }

        @Override
        public void onDialogueEnd() {
            dialogueManager.removeListener(this);
        }
    }
}
