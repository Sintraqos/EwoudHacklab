package com.sintraqos.portfolioproject.statics.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.util.ArrayList;

public class DialogueOption extends DialogueObject {

    Enums.alignment dialogueAlignment = Enums.alignment.ALIGNMENT_NEUTRAL;

    public DialogueOption(String dialogueID, String dialogueOwner, String dialogueText, String dialogueBranch) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;

        ArrayList<String> dialogueBranches = new ArrayList<>();
        dialogueBranches.add(dialogueBranch);
        this.dialogueBranches = dialogueBranches;
    }

    public DialogueOption(String dialogueID, String dialogueOwner, String dialogueText, String dialogueBranch, DialogueConditions dialogueConditions) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;

        ArrayList<String> dialogueBranches = new ArrayList<>();
        dialogueBranches.add(dialogueBranch);
        this.dialogueBranches = dialogueBranches;

        this.dialogueConditions = dialogueConditions;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] - %s: %s", dialogueID, dialogueAlignment.name(), dialogueOwner, dialogueText);
    }
}
