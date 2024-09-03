package com.sintraqos.portfolioproject.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DialogueObject implements Serializable {

    String dialogueID;
    String dialogueOwner;   // Whoever speaks the given dialogue
    String dialogueText;    // The text of the dialogue option
    Enums.dialogueEmotion dialogueEmotion = Enums.dialogueEmotion.DIALOGUE_EMOTION_NEUTRAL; // Change sprite of the speaking person to whichever emotion is needed for the dialogue
    List<String> dialogueBranches = new ArrayList<>();
    transient DialogueConditions dialogueConditions;

    public List<String> getDialogueBranches(){return dialogueBranches;}

    DialogueObject(){}

    public DialogueObject(String dialogueID, String dialogueOwner, String dialogueText, List<String> dialogueBranches) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
        this.dialogueBranches= dialogueBranches;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s: %s", dialogueID, dialogueOwner, dialogueText);
    }
}
