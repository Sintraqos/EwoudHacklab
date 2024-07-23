package com.sintraqos.portfolioproject.core.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogueObject implements Serializable {

    String dialogueID;
    String dialogueOwner;   // Whoever speaks the given dialogue
    String dialogueText;    // The text of the dialogue option
    Enums.dialogueEmotion dialogueEmotion = Enums.dialogueEmotion.DIALOGUE_EMOTION_NEUTRAL;
    ArrayList<String> dialogueBranches;
    DialogueConditions dialogueConditions;

    DialogueObject(){}

    public DialogueObject(String dialogueID, String dialogueOwner, String dialogueText,ArrayList<String> dialogueBranches) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
        this.dialogueBranches=dialogueBranches;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s: %s", dialogueID, dialogueOwner, dialogueText);
    }
}
