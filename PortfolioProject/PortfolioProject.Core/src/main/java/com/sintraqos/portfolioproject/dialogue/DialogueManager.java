package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
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

            instance.setup();
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
}
