package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.output.audio.AudioClip;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

public class DialogueManager {

    HashMap<String, DialogueTree> dialogues;

    public HashMap<String, DialogueTree> getDialogues() {
        return dialogues;
    }

    // Get instance
    static DialogueManager instance;

    public static DialogueManager getInstance() {
        if (instance == null) {
            instance = new DialogueManager();   // Then create new instance of the dialogue manager
        }

        return instance;
    }

    String currentDialogueID;

    public String getCurrentDialogueID() {
        return currentDialogueID;
    }

    DialogueManager() {
        Console.writeHeader("Initializing Dialogue Manager");

        dialogues = new HashMap<>();

        String dialoguePath = System.getProperty("user.dir") + ResourcePaths.PATH_SEPERATOR + ResourcePaths.DIALOGUE_PATH;

        readDialogueFiles(dialoguePath + ResourcePaths.PATH_SEPERATOR + ResourcePaths.DIALOGUE_PERAGUS_PATH);

        Console.writeLine("Finished setting up Dialogue Manager");
        Console.writeLine();
    }

    void readDialogueFiles(String directory) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Get all the files into a list
        File[] fileList = Objects.requireNonNull(new File(directory).listFiles());

        // Since we need to process a lot of files run a parallel loop, so it won't take too much time to process each file
        IntStream.range(0, fileList.length).parallel().forEach(i -> {
            try {    // Create new reader from file
                DialogueTree dialogueTree = gson.fromJson(new FileReader(fileList[i]), DialogueTree.class);
                dialogues.put(dialogueTree.dialogueTreeID, dialogueTree);
                //DialogueAudioManager.getInstance().getDialogueAudio(dialogueTree);

                Console.writeLine("Successfully read from dialogue file: " + Functions.getFileNameWithoutExtension(fileList[i].getName()));
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to read from dialogue file: " + Functions.getFileNameWithoutExtension(fileList[i].getName()), ex);
            }
        });
    }
}
