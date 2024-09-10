package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.Console;

import java.io.*;
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

    public HashMap<String, DialogueTree> getDialogues() {
        return dialogues;
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

    //region Dialogue Handler

    int dialogueIndex;
    DialogueTree dialogueTree;

    public List<DialogueObject> getCurrentBranches() {
        return dialogueTree.getCurrentDialogue(dialogueTree.dialogueObjects.get(dialogueIndex).getDialogueBranches());
    }

    public void handleDialogueTree(DialogueTree dialogueTree) {
        this.dialogueTree = dialogueTree;
        dialogueIndex = 0;
        Console.writeLine("Loaded in dialogue tree: " + dialogueTree.dialogueTreeID);
        handleDialogueTree();
    }

    void handleDialogueTree() {
        // Check if the dialogue ended here
        if (dialogueIndex < 0) {
            handleDialogueEnd();
            return;
        }

        // Write out the current dialogue text
        DialogueObject dialogueObject = dialogueTree.getDialogueObjects().get(dialogueIndex);
        Console.writeLine(dialogueObject.dialogueOwner + ": " + dialogueObject.dialogueText);

        // Otherwise display the player choices
        displayDialogueChoices();

        TESTHandleDialogueChoice();
        //handleDialogueChoice();
    }

    void displayDialogueChoices() {
        Console.writeLine("Dialogue Options:");

        for (int i = 1; i <= getCurrentBranches().size(); i++) {
            DialogueObject dialogueObject = getCurrentBranches().get(i - 1);
            Console.writeLine(i + " - " + dialogueObject.dialogueText);
        }
    }

    public void handleDialogueChoice(int currentChoice) {
        DialogueObject chosenDialogue = getCurrentBranches().get(currentChoice - 1);
        dialogueIndex = dialogueTree.getDialogueObjects().indexOf(dialogueTree.getCurrentDialogue(chosenDialogue.getDialogueBranches()).getFirst());

        handleDialogueTree();
    }

    void handleDialogueEnd() {
        Console.writeLine("Dialogue end!");
    }

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
                handleDialogueTree(gson.fromJson(reader, DialogueTree.class));

            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to read from settings file", ex);
            }
        }
    }

    //endregion
}
