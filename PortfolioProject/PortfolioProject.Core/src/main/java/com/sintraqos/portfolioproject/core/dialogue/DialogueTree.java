package com.sintraqos.portfolioproject.core.dialogue;

import java.io.Serializable;
import java.util.ArrayList;

public class DialogueTree implements Serializable {

    ArrayList<DialogueObject> dialogueTree;

    public DialogueTree(){

    }

    public DialogueTree(ArrayList<DialogueObject> dialogueTree){
        this.dialogueTree = dialogueTree;
    }
}
