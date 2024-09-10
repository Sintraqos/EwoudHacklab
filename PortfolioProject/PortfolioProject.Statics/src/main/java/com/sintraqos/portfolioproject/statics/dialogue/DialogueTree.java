package com.sintraqos.portfolioproject.statics.dialogue;

import com.sintraqos.portfolioproject.statics.Enums;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DialogueTree implements Serializable {

    String dialogueTreeID;
    Enums.currentLocation dialogueTreeLocation;
    ArrayList<DialogueObject> dialogueObjects;

    public String getDialogueTreeID() {
        return dialogueTreeID;
    }

    public ArrayList<DialogueObject> getDialogueObjects() {
        return dialogueObjects;
    }

    public Enums.currentLocation getDialogueTreeLocation() {
        return dialogueTreeLocation;
    }

    public DialogueTree(String dialogueTreeID, Enums.currentLocation dialogueTreeLocation, List<DialogueObject> dialogueTree) {
        this.dialogueTreeID = dialogueTreeID;
        this.dialogueTreeLocation = dialogueTreeLocation;
        this.dialogueObjects = (ArrayList<DialogueObject>) dialogueTree;
    }

    public List<DialogueObject> getCurrentDialogue(List<String> dialogueBranches) {
        List<DialogueObject> dialogueObjects = new ArrayList<>();
        // TODO: loop through all the branches, then check which dialogue object has the same ID

        for (String currentBranch : dialogueBranches) {
            dialogueObjects.add(this.dialogueObjects.stream()
                    .filter(teacher -> teacher.getDialogueID().equalsIgnoreCase(currentBranch))
                    .findFirst().orElse(null));
        }

        return dialogueObjects;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", dialogueTreeLocation, dialogueTreeID);
    }
}
