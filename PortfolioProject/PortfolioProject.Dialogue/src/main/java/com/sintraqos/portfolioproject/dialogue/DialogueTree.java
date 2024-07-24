package com.sintraqos.portfolioproject.dialogue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DialogueTree implements Serializable {

    String dialogueTreeID;
    String dialogueTreeLocation;
    ArrayList<DialogueObject> dialogueObjects;

    public DialogueTree(String dialogueTreeID, String dialogueTreeLocation, List<DialogueObject> dialogueTree) {
        this.dialogueTreeID = dialogueTreeID;
        this.dialogueTreeLocation = dialogueTreeLocation;
        this.dialogueObjects = (ArrayList<DialogueObject>) dialogueTree;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", dialogueTreeLocation, dialogueTreeID);
    }
}
