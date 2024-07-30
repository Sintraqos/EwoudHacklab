package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.*;

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
        dialogues = new HashMap<>();

        List<String> fileNames = Functions.readPathsFile(ResourcePaths.DIALOGUE_DIRECTORY).getFilePaths(ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY);

        IntStream.range(0, fileNames.size()).parallel().forEach(i -> dialogues.put(
                Functions.getFileNameWithoutExtension(fileNames.get(i)),
                getDialogueTree(ResourcePaths.getDialogueFile(fileNames.get(i)))));
    }

    DialogueTree getDialogueTree(String filePath) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Functions.class.getResourceAsStream(filePath)))) {
            return new Gson().fromJson(reader, DialogueTree.class);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Error reading paths file", ex);
        }
    }
}
