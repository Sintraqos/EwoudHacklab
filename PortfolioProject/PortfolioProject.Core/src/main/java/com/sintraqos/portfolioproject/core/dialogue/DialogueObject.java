package com.sintraqos.portfolioproject.core.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

public class DialogueObject {

    String dialogueID;
    String dialogueOwner;   // Whoever speaks the given dialogue
    String dialogueText;    // The text of the dialogue option
    Enums.dialogueEmotion dialogueEmotion = Enums.dialogueEmotion.DIALOGUE_EMOTION_NEUTRAL;

    DialogueObject(){}

    public DialogueObject(String dialogueID, String dialogueOwner, String dialogueText) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
    }

    @Override
    public String toString() {
        return String.format("[%s] - %s: %s", dialogueID, dialogueOwner, dialogueText);
    }
}
