package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Objects;

public class DialogueManager {

    HashMap<String, DialogueTree> dialogues;

    public HashMap<String, DialogueTree> getDialogues() {return dialogues; }

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
        Console.StringTitleOutput("Initializing Dialogue Manager");

        dialogues = new HashMap<>();

        String dialoguePath = System.getProperty("user.dir") +ResourcePaths.PATH_SEPERATOR+ ResourcePaths.DIALOGUE_PATH;

        readDialogueFiles(dialoguePath +ResourcePaths.PATH_SEPERATOR+ ResourcePaths.DIALOGUE_PERAGUS_PATH);

        Console.StringOutput("Finished setting up Dialogue Manager");
        Console.StringOutput();
    }

    void readDialogueFiles(String directory) {
        for (File currentFile : Objects.requireNonNull(new File(directory).listFiles())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if (currentFile.isFile()) { // Check if the current file isn't a directory

                String cleanFileName = Functions.getFileNameWithoutExtension(currentFile.getName());

                try (Reader reader = new FileReader(currentFile)) {    // Create new reader from file
                    Console.StringOutput("Successfully read from dialogue file: " + cleanFileName);

                    DialogueTree dialogueTree = gson.fromJson(reader, DialogueTree.class);
                    dialogues.put(dialogueTree.dialogueTreeID, dialogueTree);
                    //DialogueAudioManager.getInstance().getDialogueAudio(dialogueTree);
                } catch (IOException ex) {
                    throw new   Functions.ExceptionHandler("Failed to read from dialogue file: " + cleanFileName, ex);
                }
            }
        }
    }
}
