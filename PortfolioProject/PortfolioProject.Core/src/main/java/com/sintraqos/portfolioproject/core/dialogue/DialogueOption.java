package com.sintraqos.portfolioproject.core.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

public class DialogueOption extends DialogueObject {

    Enums.alignment dialogueAlignment = Enums.alignment.ALIGNMENT_NEUTRAL;

    public DialogueOption(String dialogueID, String dialogueOwner, String dialogueText, Enums.alignment dialogueAlignment) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
        this.dialogueAlignment = dialogueAlignment;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] - %s: %s", dialogueID, dialogueAlignment.name(), dialogueOwner, dialogueText);
    }
}
