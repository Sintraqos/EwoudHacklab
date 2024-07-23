package com.sintraqos.portfolioproject.core.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.util.ArrayList;

public class DialogueOption extends DialogueObject {

    Enums.alignment dialogueAlignment = Enums.alignment.ALIGNMENT_NEUTRAL;
    public DialogueOption(String dialogueID, String dialogueOwner, String dialogueText,String dialogueBranch) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
        dialogueAlignment = Enums.alignment.ALIGNMENT_NEUTRAL;
        this.dialogueBranches=new ArrayList<>(){{add(dialogueBranch);}};
    }

    public DialogueOption(String dialogueID, String dialogueOwner, String dialogueText, String dialogueBranch, DialogueConditions dialogueConditions) {
        this.dialogueID = dialogueID;
        this.dialogueOwner = dialogueOwner;
        this.dialogueText = dialogueText;
        dialogueAlignment = Enums.alignment.ALIGNMENT_NEUTRAL;
        this.dialogueBranches=new ArrayList<>(){{add(dialogueBranch);}};
        this.dialogueConditions = dialogueConditions;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] - %s: %s", dialogueID, dialogueAlignment.name(), dialogueOwner, dialogueText);
    }
}
