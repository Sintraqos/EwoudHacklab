package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.Console;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class DialogueManager {

    // Get instance
    static DialogueManager instance;

    public static DialogueManager getInstance() {
        if (instance == null) {
            instance = new DialogueManager();

            //instance.setup();
        }

        return instance;
    }

    HashMap<String, DialogueTree> dialogues;
    String currentDialogueID;

    public HashMap<String, DialogueTree> getDialogues() {
        return dialogues;
    }

    public String getCurrentDialogueID() {
        return currentDialogueID;
    }

    //region Setup

    void setup() {
        Console.writeHeader("Setup Dialogue Manager");
        dialogues = new HashMap<>();

        List<String> fileNames = ResourcePaths.readPathsFile(ResourcePaths.DIALOGUE_DIRECTORY).getFilePaths(ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY);

        IntStream.range(0, fileNames.size()).parallel().forEach(i -> dialogues.put(
                Functions.getFileNameWithoutExtension(fileNames.get(i)),
                getDialogueTree(ResourcePaths.getDialogueFile(fileNames.get(i)))));

        Console.writeLine("Finished Setup Dialogue Manager");
        Console.writeLine();
    }

    DialogueTree getDialogueTree(String filePath) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Functions.class.getResourceAsStream(filePath)))) {
            DialogueTree dialogueTree = new Gson().fromJson(reader, DialogueTree.class);
            Console.writeLine("Loaded in dialogue tree: " + dialogueTree.dialogueTreeID);
            return dialogueTree;
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Error reading paths file", ex);
        }
    }

    //endregion

    int currentDialoguePosition;
    DialogueTree dialogueTree;

    public void handleDialogueTree(DialogueTree dialogueTree) {
        this.dialogueTree = dialogueTree;
        handleDialogueTree();
    }

    void handleDialogueTree() {
        currentDialoguePosition = 0;
        Console.writeLine("Loaded in dialogue tree: " + dialogueTree.dialogueTreeID);

        while (true) {

            // Write out the current dialogue text
            displayCurrentDialogue(dialogueTree.getDialogueObjects().get(currentDialoguePosition));
            // Check if the dialogue ended here
            if (checkIfEnd(dialogueTree.getDialogueObjects().get(currentDialoguePosition))) {
                handleDialogueEnd();
                return;
            }

            // Otherwise display the player choices
            displayDialogueChoices(dialogueTree);

            handleDialogueChoice();
        }
    }

    void displayDialogueChoices(DialogueTree dialogueTree) {
        Console.writeLine("Dialogue choices:");

        int dialogueIndex = 1;

        for (DialogueObject dialogueObject : getCurrentBranches()) {
            displayCurrentDialogue(dialogueIndex, dialogueObject);
            dialogueIndex++;
        }
    }

    List<DialogueObject> getCurrentBranches(DialogueObject dialogueObject){
        return dialogueTree.getCurrentDialogue(dialogueObject.getDialogueBranches());
    }

    List<DialogueObject> getCurrentBranches(){
        return dialogueTree.getCurrentDialogue(dialogueTree.dialogueObjects.get(currentDialoguePosition).getDialogueBranches());
    }

    void displayCurrentDialogue(DialogueObject dialogueObject) {
        if (Objects.equals(dialogueObject.dialogueOwner, StaticUtils.NAME_PLAYER)) {
            dialogueObject.dialogueOwner = PlayerManager.getInstance().getCurrentPlayer().getEntityName();
        }

        Console.writeLine(dialogueObject.dialogueOwner + ": " + dialogueObject.dialogueText);
    }

    void displayCurrentDialogue(int dialogueIndex, DialogueObject dialogueObject) {
        Console.writeLine(dialogueIndex + " - " + dialogueObject.dialogueText);
    }

    void handleDialogueEnd() {
        Console.writeLine("Dialogue end!");
    }

    boolean checkIfEnd(DialogueObject dialogueObject) {
        return Objects.equals(dialogueObject.getDialogueBranches().getFirst(), StaticUtils.DIALOGUE_EXIT_CONDITION);
    }

    public void handleDialogueChoice() {
        while (true) {
            Integer currentChoice = parseIntOrNull(System.console().readLine());

            if (currentChoice != null && currentChoice <= getCurrentBranches().size()) {
                //TODO: move the dialogue tree towards the new index from the choice
                DialogueObject chosenDialogue = getCurrentBranches().get(currentChoice - 1);
                currentDialoguePosition = dialogueTree.getDialogueObjects().indexOf(getCurrentBranches(chosenDialogue).getFirst());
                displayCurrentDialogue(chosenDialogue);
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
                handleDialogueTree(gson.fromJson(reader, DialogueTree.class));

            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to read from settings file", ex);
            }
        }
    }

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
