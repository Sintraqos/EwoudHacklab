package com.sintraqos.portfolioproject.createfiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.dialogue.DialogueConditions;
import com.sintraqos.portfolioproject.dialogue.DialogueObject;
import com.sintraqos.portfolioproject.dialogue.DialogueOption;
import com.sintraqos.portfolioproject.dialogue.DialogueTree;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.StaticUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateFileManager {
    // Get instance
    static CreateFileManager instance;

    public static CreateFileManager getInstance() {
        if (instance == null) {
            instance = new CreateFileManager();

            instance.createFiles();
        }

        return instance;
    }

    public void createFiles() {

        Console.writeHeader("Creating files");

        //region File Paths

        HashMap<String, ResourcePaths.FilePaths> paths;

        createDirectory(ResourcePaths.RESOURCE_DIRECTORY + ResourcePaths.PATH_SEPARATOR + ResourcePaths.FILEPATH_DIRECTORY);

        //region Audio Paths

        Console.writeLine("Create audio path files");

        // Reset paths
        paths = new HashMap<>();

        // Planet
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_DXUN, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_DXUN)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_ONDERON, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_ONDERON)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_TELOS, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_TELOS)));

        // Ship
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER)));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER)));

        // Other
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS)));

        // Battle
        paths.put(ResourcePaths.OST_BATTLE_PREFIX, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY), ResourcePaths.OST_BATTLE_PREFIX)));

        // Create file
        createFilePathFile(ResourcePaths.AUDIO_DIRECTORY, new ResourcePaths.ResourcePathsFile(paths));

        Console.writeLine("Finished creating audio path files");

        //endregion

        //region Image Paths

        Console.writeLine("Create image path files");

        // Reset paths
        paths = new HashMap<>();

        // Portraits
        paths.put(ResourcePaths.PORTRAIT_MALE_PREFIX, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_MALE_PREFIX)));
        paths.put(ResourcePaths.PORTRAIT_FEMALE_PREFIX, new ResourcePaths.FilePaths(createFilePaths(ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_FEMALE_PREFIX)));

        // Create file
        createFilePathFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, new ResourcePaths.ResourcePathsFile(paths));

        // Cleanup
        paths.clear();

        Console.writeLine("Finished creating image path files");

        //endregion

        //endregion

        //region Dialogue Tree

        DialogueTree dialogueTree;
        ArrayList<DialogueObject> dialogueOptions;

        createDirectory(ResourcePaths.getResourceDirectoryPath(ResourcePaths.DIALOGUE_DIRECTORY));

        Console.writeLine("Create dialogue files");

        //region Awaken 001

        dialogueOptions = new ArrayList<>();

        // Add dialogue to the list
        dialogueOptions.add(new DialogueObject("Per_Intro_Kreia_001", "",
                ":: Awaken ::",
                List.of(
                        StaticUtils.DIALOGUE_EXIT_CONDITION
                )));

        // Create the dialogue tree
        dialogueTree = new DialogueTree(ResourcePaths.KREIA_001, ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY + ResourcePaths.KREIA_001, dialogueOptions);

        // And create the new dialogue file
        createDialogueFile(ResourcePaths.KREIA_001, dialogueTree, ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY);

        //endregion

        //region Kreia 002

        dialogueOptions = new ArrayList<>();

        // Add dialogue to the list

        //region Kreia

        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_GREETINGS_001", StaticUtils.NAME_KREIA,
                "Yes? What have you found?",
                List.of(
                        "Per_Morgue_Player_029"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_001", StaticUtils.NAME_KREIA,
                "Find what you're looking for amongst the dead?",
                List.of(
                        "Per_Morgue_Player_001",
                        "Per_Morgue_Player_002"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_002", StaticUtils.NAME_KREIA,
                "Close to death, yes, closer than I'd like. You have the smell of the kolto tank about you... how do you feel?",
                List.of(
                        "Per_Morgue_Player_003",
                        "Per_Morgue_Player_004"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_003", StaticUtils.NAME_KREIA,
                "Yes, I had hoped as much - I slept here too long, and could not awaken. It may be I reached out unconsciously, and your mind must have been a willing one. Or perhaps you have been trained for such things?",
                List.of(
                        "Per_Morgue_Player_005",
                        "Per_Morgue_Player_006",
                        "Per_Morgue_Player_007",
                        "Per_Morgue_Player_008",
                        "Per_Morgue_Player_009"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_004", StaticUtils.NAME_KREIA,
                "I am Kreia, and I am your rescuer - as you are mine. Tell me - do you recall what happened?",
                List.of(
                        "Per_Morgue_Player_010",
                        "Per_Morgue_Player_011"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_005", StaticUtils.NAME_KREIA,
                "Your ship was attacked. You were the only survivor... a result of your Jedi training, no doubt.",
                List.of(
                        "Per_Morgue_Player_012",
                        "Per_Morgue_Player_013",
                        "Per_Morgue_Player_014"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_006", StaticUtils.NAME_KREIA,
                "I confess I know little more than you do... I do not know where here is. I do recall rescuing you... the Republic ship you were on was attacked, and you were the only survivor. A result of your Jedi training, no doubt.",
                List.of(
                        "Per_Morgue_Player_012",
                        "Per_Morgue_Player_013",
                        "Per_Morgue_Player_014"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_007", StaticUtils.NAME_KREIA,
                "Your stance, your walk tells me you are a Jedi. Your walk is heavy, you carry something that weighs you down.",
                List.of(
                        "Per_Morgue_Player_015",
                        "Per_Morgue_Player_016",
                        "Per_Morgue_Player_017"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_008", StaticUtils.NAME_KREIA,
                "So it would seem. Keep your past - and let us focus on the now.",
                List.of(
                        "Per_Morgue_Player_015",
                        "Per_Morgue_Player_016",
                        "Per_Morgue_Player_017"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_009", StaticUtils.NAME_KREIA,
                "I do not know. I was removed from the events of the world as I slept. A survey of the surroundings may provide the answers we seek. The ship we arrived in must still be in this place. We should recover it and leave.",
                List.of(
                        "Per_Morgue_Player_021",
                        "Per_Morgue_Player_022",
                        "Per_Morgue_Player_023"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_010", StaticUtils.NAME_KREIA,
                "We were attacked once, and I fear our attackers will not give up the hunt so easily - without transport, weapons, and information, they will find us easy prey indeed.",
                List.of(
                        "Per_Morgue_Player_024",
                        "Per_Morgue_Player_025",
                        "Per_Morgue_Player_026",
                        "Per_Morgue_Player_027"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_011", StaticUtils.NAME_KREIA,
                "Even as I slept, I felt much unrest here - I saw strange visions, minds colored with fear - now, everything here feels terribly silent. I would find out as much as you can about this place quickly - I fear we will need to depart as suddenly as we arrived.",
                List.of(
                        "Per_Morgue_Player_025",
                        "Per_Morgue_Player_026",
                        "Per_Morgue_Player_027"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_012", StaticUtils.NAME_KREIA,
                "You may wish to extend your search to some clothes... if only for proper first impressions.",
                List.of(
                        "Per_Morgue_Player_028"
                )));
        dialogueOptions.add(new DialogueObject("Per_Morgue_Kreia_013", StaticUtils.NAME_KREIA,
                "I am not offering to help you - I am not so young as to leap from death's door as quickly as you.",
                List.of(
                        "Per_Morgue_Player_029"
                )));

        //endregion

        //region Player

        // On enter
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_001", StaticUtils.NAME_PLAYER,
                "I thought you were dead.",
                "Per_Morgue_Kreia_002"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_002", StaticUtils.NAME_PLAYER,
                "Slept too long? You looked dead when I came in.",
                "Per_Morgue_Kreia_002"
        ));

        // Kolto tank
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_003", StaticUtils.NAME_PLAYER,
                "A little disoriented... was it your voice I heard in the kolto tank?",
                "Per_Morgue_Kreia_003"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_004", StaticUtils.NAME_PLAYER,
                "Your voice - I heard it as I floated in the kolto tank.",
                "Per_Morgue_Kreia_003"
        ));

        // Who is Kreia
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_005", StaticUtils.NAME_PLAYER,
                "Who are you?",
                "Per_Morgue_Kreia_004")
        );
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_006", StaticUtils.NAME_PLAYER,
                "The kolto tank left me a little drained... who are you?",
                "Per_Morgue_Kreia_004"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_007", StaticUtils.NAME_PLAYER,
                "Just stay out of my mind in the future. Now what do you want?",
                "Per_Morgue_Kreia_004"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_008", StaticUtils.NAME_PLAYER,
                "Enough with the false concern - what do you want?",
                "Per_Morgue_Kreia_004"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_009", StaticUtils.NAME_PLAYER,
                "So you can touch minds... and feign death. Who are you?",
                "Per_Morgue_Kreia_004"
        ));

        // What happened?
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_010", StaticUtils.NAME_PLAYER,
                "Last thing I remember, I was on board a Republic ship, the Harbinger... what happened to it?",
                "Per_Morgue_Kreia_005"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_011", StaticUtils.NAME_PLAYER,
                "I'm the one asking the questions. How did I get here?",
                "Per_Morgue_Kreia_006"
        ));

        // I'm not a jedi
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_012", StaticUtils.NAME_PLAYER,
                "If you think I'm a Jedi, you're mistaken.",
                "Per_Morgue_Kreia_007"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_013", StaticUtils.NAME_PLAYER,
                "I am no longer a member of the Jedi Order.",
                "Per_Morgue_Kreia_007"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_014", StaticUtils.NAME_PLAYER,
                "How do you know I was a Jedi?",
                "Per_Morgue_Kreia_007"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_015", StaticUtils.NAME_PLAYER,
                "The Jedi Order and I have a... troubled history.",
                "Per_Morgue_Kreia_008"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_016", StaticUtils.NAME_PLAYER,
                "That is no business of yours.",
                "Per_Morgue_Kreia_008"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_017", StaticUtils.NAME_PLAYER,
                "Let's deal with the now. What is this place?",
                "Per_Morgue_Kreia_009"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_018", StaticUtils.NAME_PLAYER,
                "What is this place?",
                "Per_Morgue_Kreia_009"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_019", StaticUtils.NAME_PLAYER,
                "All right - what's going on? How did we get here?",
                "Per_Morgue_Kreia_009"
        ));

        // What now?
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_020", StaticUtils.NAME_PLAYER,
                "Very well. How do we get out of here?",
                "Per_Morgue_Kreia_009"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_021", StaticUtils.NAME_PLAYER,
                "Why do we need to leave?",
                "Per_Morgue_Kreia_010"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_022", StaticUtils.NAME_PLAYER,
                "Care to explain why you're in such a hurry?",
                "Per_Morgue_Kreia_010"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_023", StaticUtils.NAME_PLAYER,
                "\"We?\" Why do you think we are together in this?",
                "Per_Morgue_Kreia_010"
        ));

        // Awareness Check if player awareness is at least 4, otherwise skip this line and load in the next one
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_024", StaticUtils.NAME_PLAYER,
                "[Awareness] You seem nervous, worried. Is something wrong?",
                "Per_Morgue_Kreia_011",
                new DialogueConditions(Enums.entitySkills.ENTITY_SKILLS_AWARENESS, 4)
        ));

        // What to do now?
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_025", StaticUtils.NAME_PLAYER,
                "We'll see. There's got to be someone left alive around here.",
                "Per_Morgue_Kreia_012"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_026", StaticUtils.NAME_PLAYER,
                "I'll go look for our ship - and some weapons.",
                "Per_Morgue_Kreia_012"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Player_027", StaticUtils.NAME_PLAYER,
                "Is there anything else I should keep an eye out for?",
                "Per_Morgue_Kreia_012"
        ));

        //endregion

        //region Exit

        // First time
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_001", StaticUtils.NAME_PLAYER,
                "Are you well enough to travel?",
                "Per_Morgue_Kreia_013"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_002", StaticUtils.NAME_PLAYER,
                "Very well. I'll take a look around.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_003", StaticUtils.NAME_PLAYER,
                "I'll return soon to make sure you're all right",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_004", StaticUtils.NAME_PLAYER,
                "Just stay out of my way, or you'll end up like these corpses.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));

        // Telepathy
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_TELEPATHY_001", StaticUtils.NAME_PLAYER,
                "Just stay here and be quiet.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_TELEPATHY_002",
                StaticUtils.NAME_PLAYER,
                "All right, then - I'll be back.", StaticUtils.DIALOGUE_EXIT_CONDITION
        ));

        // Default
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_001", StaticUtils.NAME_PLAYER,
                "Just checking to make sure you're all right.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_EXIT_002", StaticUtils.NAME_PLAYER,
                "Nothing yet. I'll return later.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));

        //endregion

        //region Telepathy

        // Kreia
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Kreia_001", StaticUtils.NAME_KREIA,
                "Ah yes. I noticed that as well. It may be that our proximity during our long slumber may have had... unforeseen consequences.",
                "Per_Morgue_Kreia_002"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Kreia_002", StaticUtils.NAME_KREIA,
                "We seem to be able to speak without speaking. Perhaps this effect will pass with time.",
                "Per_Morgue_Kreia_002"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Kreia_003", StaticUtils.NAME_KREIA,
                "Then hope that we have little to say to each other, lest it prove distracting.",
                "Per_Morgue_Kreia_002"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Kreia_004", StaticUtils.NAME_KREIA,
                "I have little choice - and neither do you. It is an advantage to us both, and I suggest we make use of it.",
                "Per_Morgue_Kreia_002"
        ));

        // Player
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_001", StaticUtils.NAME_PLAYER,
                "I keep hearing your voice as I explore this place.",
                "Per_Morgue_Telepathy_Kreia_001"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_002", StaticUtils.NAME_PLAYER,
                "Unforeseen consequences?",
                "Per_Morgue_Telepathy_Kreia_002"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_003", StaticUtils.NAME_PLAYER,
                "What if it doesn't pass?",
                "Per_Morgue_Telepathy_Kreia_003"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_004", StaticUtils.NAME_PLAYER,
                "I don't care... just stay out of my head, got it?",
                "Per_Morgue_Telepathy_Kreia_004"
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_005", StaticUtils.NAME_PLAYER,
                "Just stay here and be quiet.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));
        dialogueOptions.add(new DialogueOption("Per_Morgue_Telepathy_Player_006", StaticUtils.NAME_PLAYER,
                "All right, then - I'll be back.",
                StaticUtils.DIALOGUE_EXIT_CONDITION
        ));

        //endregion

        // Create the dialogue tree
        dialogueTree = new DialogueTree(ResourcePaths.KREIA_002, ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY + ResourcePaths.KREIA_002, dialogueOptions);

        // And create the new dialogue file
        createDialogueFile(ResourcePaths.KREIA_002, dialogueTree, ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY);

        //endregion

        // Cleanup
        dialogueOptions.clear();

        Console.writeLine("Finished creating dialogue files");

        //endregion

        Console.writeLine("Finished creating files");
    }

    void createDirectory(String directoryPath) {
        if (!new File(directoryPath).mkdirs() && !new File(directoryPath).exists()) {
            throw new Functions.ExceptionHandler("Failed to create new directory: " + directoryPath);
        }
    }

    void createFilePathFile(String fileType, ResourcePaths.ResourcePathsFile paths) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(ResourcePaths.getResourceFilePath(fileType))) {
            gson.toJson(paths, writer);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to create new path file", ex);
        }
    }

    void createDialogueFile(String fileName, DialogueTree newDialogueTree, String locationPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String directoryPath = ResourcePaths.getResourceDirectoryPath(ResourcePaths.DIALOGUE_DIRECTORY) + ResourcePaths.PATH_SEPARATOR + locationPath;

        if (!new File(directoryPath).mkdirs() && !new File(directoryPath).exists()) {
            throw new Functions.ExceptionHandler("Failed to create new directory: " + directoryPath);
        }

        try (Writer writer = new FileWriter(directoryPath + ResourcePaths.PATH_SEPARATOR + fileName + ResourcePaths.EXTENSION_DATAFILE)) {
            gson.toJson(newDialogueTree, writer);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to create new dialogue file", ex);
        }
    }

    public static List<String> createFilePaths(String filePath, String filePrefix) {
        List<String> fileNames = new ArrayList<>();
        try (InputStream in = Functions.class.getResourceAsStream(filePath)) {
            assert in != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String resource;

                while ((resource = br.readLine()) != null) {
                    // Check if the current file contains the needed prefix
                    if (resource.contains(filePrefix)) {
                        fileNames.add(resource);
                    }
                }
            }
        } catch (IOException e) {
            throw new Functions.ExceptionHandler("Error reading files with prefix: " + filePrefix, e);
        }

        return fileNames;
    }
}
