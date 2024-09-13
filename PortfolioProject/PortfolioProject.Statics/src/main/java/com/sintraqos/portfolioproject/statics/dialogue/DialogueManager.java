package com.sintraqos.portfolioproject.statics.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.Console;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DialogueManager {
    // Get instance
    static DialogueManager instance;

    public static DialogueManager getInstance() {
        if (instance == null) {
            instance = new DialogueManager();

            instance.setup();
        }

        return instance;
    }

    HashMap<String, DialogueTree> dialogueTrees;

    //region Setup

    void setup() {
        Console.writeHeader("Setup Dialogue Manager");
        // Setup components and variables
        dialogueTrees = new HashMap<>();
        listeners = new ArrayList<>();

        // Get the dialogue trees
        // Peragus - Kreia
        addDialogueTree(ResourcePaths.DIRECTORY_PERAGUS, ResourcePaths.PERAGUS_KREIA_DIALOGUE);

        Console.writeLine("Finished Setup Dialogue Manager");
        Console.writeLine();
    }

    void addDialogueTree(String location, String[] fileNames){
        // Loop through the array of filenames
        for(String fileName : fileNames){
            // Find the dialogue tree
            DialogueTree dialogueTree = getDialogueTree(location, fileName);

            // Add the new tree to the list
            dialogueTrees.put(fileName, dialogueTree);
        }
    }

    DialogueTree getDialogueTree(String location, String fileName) {
        // Get the file from the given input, throw exception when the file doesn't exist
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Functions.class.getResourceAsStream(ResourcePaths.getDialogueFile(location, fileName))))) {
            // Create new DialogueTree from the given JSON file
            DialogueTree dialogueTree = new Gson().fromJson(reader, DialogueTree.class);
            Console.writeLine("Loaded in dialogue tree: " + dialogueTree.dialogueTreeID);

            // And finally return the file
            return dialogueTree;
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Error reading paths file", ex);
        }
    }

    public DialogueTree getDialogueTree(String dialogueTree) {
        // Return the dialogue tree based on the dialogueTreeID, if the file hasn't been loaded in, or hasn't been added throw exception
        try {
            return dialogueTrees.get(dialogueTree);
        } catch (Exception ex) {
            throw new Functions.ExceptionHandler("Invalid dialogue file", ex);
        }
    }

    //endregion

    //region Dialogue Handler

    int dialogueIndex;                  // The current index we are at on the dialogue tree
    DialogueTree currentDialogueTree;   // The current DialogueTree

    public List<DialogueObject> getCurrentBranches() {
        return currentDialogueTree.getCurrentDialogue(currentDialogueTree.dialogueObjects.get(dialogueIndex).getDialogueBranches());
    }

    public void setupDialogueTree(DialogueTree dialogueTree) {
        // Set variables and components
        currentDialogueTree = dialogueTree;
        dialogueIndex = 0;  // Always make sure this is set to 0 when starting, since we don't want to start at the end of a given dialogue tree

        Console.writeLine("Loaded in dialogue tree: " + currentDialogueTree.dialogueTreeID);

        //handleDialogueStart(dialogueTree);  // Invoke dialogue start
        handleDialogueSetup(currentDialogueTree);
    }

    void handleDialogueTree() {
        // Check if the dialogue ended here, if true invoke the dialogueEnd
        if (dialogueIndex < 0) {
            handleDialogueEnd();
            return;
        }

        // Set the current DialogueObject
        DialogueObject currentDialogue = currentDialogueTree.getDialogueObjects().get(dialogueIndex);
        // Update dialogueUpdate event
        onDialogueUpdate(currentDialogueTree.dialogueTreeID, currentDialogue);
        // Write out the current dialogue text
        Console.writeLine(currentDialogue.dialogueOwner + ": " + currentDialogue.dialogueText);

        // Otherwise display the player choices
        displayDialogueChoices();

        // Handle dialogueChoice
        TESTHandleDialogueChoice();
        //handleDialogueChoice();
    }

    void displayDialogueChoices() {
        Console.writeLine("Dialogue Options:");

        // Get the current dialogueBranches and loop trough them
        List<DialogueObject> currentBranches = getCurrentBranches();
        for (int i = 0; i < currentBranches.size(); i++) {
            // For now write them out in the console as:
            // 1 - Dialogue Text.
            Console.writeLine((i + 1)+ " - " +
                    Functions.capitalize(   // Make sure the String starts with a capitalized letter
                    Functions.punctuate(    // And make sure the String ends with punctuation
                            currentBranches.get(i).dialogueText)));
        }
    }

    public void handleDialogueChoice(int currentChoice) {
        // Get the chosen dialogueObject based on the given index
        DialogueObject chosenDialogue = getCurrentBranches().get(currentChoice - 1);
        // And move the dialogueIndex to the new value
        dialogueIndex = currentDialogueTree.getDialogueObjects().indexOf(currentDialogueTree.getCurrentDialogue(chosenDialogue.getDialogueBranches()).getFirst());

        // Finally return to the dialogue tree handler
        handleDialogueTree();
    }

    //endregion

    //region Events

    private List<DialogueEvent> listeners = new ArrayList<>();

    public void addListener(DialogueEvent listener) {
        // Add listeners to the event
        // Check if the list already has the listener active in it, if not add it,
        // otherwise don't re-add it to the list, since we don't need to invoke the same listener twice
        if (!listeners.contains(listener)) {
            Console.writeLine("Adding listener");
            listeners.add(listener);
        }
    }

    public void removeListener(DialogueEvent listener) {
        // Remove listeners from the event
        // Check if the list contains the listener
        if (listeners.contains(listener)) {
            Console.writeLine("Removing listener");
            listeners.remove(listener);
        }
    }
    void handleDialogueSetup(DialogueTree currentDialogueTree) {
        // When starting dialogue invoke dialogueSetup event
        // Loop through all listeners and invoke
        Console.writeLine("Dialogue setup!");
        for(DialogueEvent listener : new ArrayList<>(listeners)){
            listener.onDialogueSetup(currentDialogueTree);
        }
    }

   public void handleDialogueStart() {
        // When starting dialogue invoke dialogueStart event
        // Loop through all listeners and invoke
        Console.writeLine("Dialogue start!");
        for(DialogueEvent listener : new ArrayList<>(listeners)){
            listener.onDialogueStart(currentDialogueTree);
        }

        handleDialogueTree();
    }

    public void onDialogueUpdate(String dialogueTreeID, DialogueObject dialogueObject){
        // When reaching new dialogue invoke dialogueUpdate event
        // Loop through all listeners and invoke
        for(DialogueEvent listener : new ArrayList<>(listeners)){
            listener.onDialogueUpdate(dialogueTreeID, dialogueObject);
        }
    }

    void handleDialogueEnd() {
        // When reached end invoke dialogueEnd event
        Console.writeLine("Dialogue end!");
        for(DialogueEvent listener : new ArrayList<>(listeners)){
            listener.onDialogueEnd();
        }
    }

    //endregion

    void TESTHandleDialogueChoice() {
        while (true) {
            Integer currentChoice = Functions.parseIntOrNull(System.console().readLine());

            if (currentChoice != null && currentChoice <= getCurrentBranches().size()) {
                handleDialogueChoice(currentChoice);
                break;
            }
        }
    }

    public void TESTHandleDialogue() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = "TESTDialogueTree2.json";

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                setupDialogueTree(gson.fromJson(reader, DialogueTree.class));
                //currentDialogueTree = gson.fromJson(reader, DialogueTree.class);
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to read from settings file", ex);
            }
        }
    }

}
