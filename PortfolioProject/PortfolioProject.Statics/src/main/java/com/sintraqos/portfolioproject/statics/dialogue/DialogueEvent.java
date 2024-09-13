package com.sintraqos.portfolioproject.statics.dialogue;

public interface DialogueEvent {
    // Dialogue setup event, use for initializing and setting up components
    void onDialogueSetup(DialogueTree dialogueTree);

    // Dialogue start event, use for starting dialogue tree
    void onDialogueStart(DialogueTree dialogueTree);

    // Dialogue update event, use for updating when new dialogue text
    void onDialogueUpdate(String dialogueTreeID, DialogueObject dialogueObject);

    // Dialogue end event, use for when dialogue has ended and for when we need to update the game logic with the rewards, missions etc.
    void onDialogueEnd();
}
