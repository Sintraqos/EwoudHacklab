package com.sintraqos.portfolioproject.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.StaticUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class CreateDialogueFiles {

    //region Dialogue

    //region ---- EntityNames

    //endregion

    //region ---- Per_Intro

    // On Enter
    public static final DialogueObject Per_Intro_Kreia_001 = new DialogueObject("Per_Intro_Kreia_001", "",
            ":: Awaken ::", new ArrayList<>(){{
        add(StaticUtils.DIALOGUE_EXIT_CONDITION);
    }});

    //endregion

    //region ---- Per_Morgue

    //region ---- Kreia

    // On enter
    public static final DialogueObject Per_Morgue_Kreia_GREETINGS_001 = new DialogueObject("Per_Morgue_Kreia_GREETINGS_001", StaticUtils.NAME_KREIA,
            "Yes? What have you found?", new ArrayList<>() {{
        add("Per_Morgue_Player_029");
    }});


    public static final DialogueObject Per_Morgue_Kreia_001 = new DialogueObject("Per_Morgue_Kreia_001", StaticUtils.NAME_KREIA,
            "Find what you're looking for amongst the dead?", new ArrayList<>() {{
        add("Per_Morgue_Player_001");
        add("Per_Morgue_Player_002");
    }});

    // Kolto tank
    public static final DialogueObject Per_Morgue_Kreia_002 = new DialogueObject("Per_Morgue_Kreia_002", StaticUtils.NAME_KREIA,
            "Close to death, yes, closer than I'd like. You have the smell of the kolto tank about you... how do you feel?", new ArrayList<>() {{
        add("Per_Morgue_Player_003");
        add("Per_Morgue_Player_004");
    }});

    // Who is Kreia
    public static final DialogueObject Per_Morgue_Kreia_003 = new DialogueObject("Per_Morgue_Kreia_003", StaticUtils.NAME_KREIA,
            "Yes, I had hoped as much - I slept here too long, and could not awaken. It may be I reached out unconsciously, and your mind must have been a willing one. Or perhaps you have been trained for such things?", new ArrayList<>() {{
        add("Per_Morgue_Player_005");
        add("Per_Morgue_Player_006");
        add("Per_Morgue_Player_007");
        add("Per_Morgue_Player_008");
        add("Per_Morgue_Player_009");
    }});

    // What happened?
    public static final DialogueObject Per_Morgue_Kreia_004 = new DialogueObject("Per_Morgue_Kreia_004", StaticUtils.NAME_KREIA,
            "I am Kreia, and I am your rescuer - as you are mine. Tell me - do you recall what happened?", new ArrayList<>() {{
        add("Per_Morgue_Player_010");
        add("Per_Morgue_Player_011");
    }});

    public static final DialogueObject Per_Morgue_Kreia_005 = new DialogueObject("Per_Morgue_Kreia_005", StaticUtils.NAME_KREIA,
            "Your ship was attacked. You were the only survivor... a result of your Jedi training, no doubt.", new ArrayList<>() {{
        add("Per_Morgue_Player_012");
        add("Per_Morgue_Player_013");
        add("Per_Morgue_Player_014");
    }});
    public static final DialogueObject Per_Morgue_Kreia_006 = new DialogueObject("Per_Morgue_Kreia_006", StaticUtils.NAME_KREIA,
            "I confess I know little more than you do... I do not know where here is. I do recall rescuing you... the Republic ship you were on was attacked, and you were the only survivor. A result of your Jedi training, no doubt.", new ArrayList<>() {{
        add("Per_Morgue_Player_012");
        add("Per_Morgue_Player_013");
        add("Per_Morgue_Player_014");
    }});

    // I'm not a jedi
    public static final DialogueObject Per_Morgue_Kreia_007 = new DialogueObject("Per_Morgue_Kreia_007", StaticUtils.NAME_KREIA,
            "Your stance, your walk tells me you are a Jedi. Your walk is heavy, you carry something that weighs you down.", new ArrayList<>() {{
        add("Per_Morgue_Player_015");
        add("Per_Morgue_Player_016");
        add("Per_Morgue_Player_017");
    }});

    public static final DialogueObject Per_Morgue_Kreia_008 = new DialogueObject("Per_Morgue_Kreia_008", StaticUtils.NAME_KREIA,
            "So it would seem. Keep your past - and let us focus on the now.", new ArrayList<>() {{
        add("Per_Morgue_Player_015");
        add("Per_Morgue_Player_016");
        add("Per_Morgue_Player_017");
    }});

    // What now?
    public static final DialogueObject Per_Morgue_Kreia_009 = new DialogueObject("Per_Morgue_Kreia_009", StaticUtils.NAME_KREIA,
            "I do not know. I was removed from the events of the world as I slept. A survey of the surroundings may provide the answers we seek. The ship we arrived in must still be in this place. We should recover it and leave.", new ArrayList<>() {{
        add("Per_Morgue_Player_021");
        add("Per_Morgue_Player_022");
        add("Per_Morgue_Player_023");
    }});

    public static final DialogueObject Per_Morgue_Kreia_010 = new DialogueObject("Per_Morgue_Kreia_010", StaticUtils.NAME_KREIA,
            "We were attacked once, and I fear our attackers will not give up the hunt so easily - without transport, weapons, and information, they will find us easy prey indeed.", new ArrayList<>() {{
        add("Per_Morgue_Player_024");
        add("Per_Morgue_Player_025");
        add("Per_Morgue_Player_026");
        add("Per_Morgue_Player_027");
    }});

    public static final DialogueObject Per_Morgue_Kreia_011 = new DialogueObject("Per_Morgue_Kreia_011", StaticUtils.NAME_KREIA,
            "Even as I slept, I felt much unrest here - I saw strange visions, minds colored with fear - now, everything here feels terribly silent. I would find out as much as you can about this place quickly - I fear we will need to depart as suddenly as we arrived.", new ArrayList<>() {{
        add("Per_Morgue_Player_025");
        add("Per_Morgue_Player_026");
        add("Per_Morgue_Player_027");
    }});

    public static final DialogueObject Per_Morgue_Kreia_012 = new DialogueObject("Per_Morgue_Kreia_012", StaticUtils.NAME_KREIA,
            "You may wish to extend your search to some clothes... if only for proper first impressions.", new ArrayList<>() {{
        add("Per_Morgue_Player_028");
    }});

    public static final DialogueObject Per_Morgue_Kreia_013 = new DialogueObject("Per_Morgue_Kreia_013", StaticUtils.NAME_KREIA,
            "I am not offering to help you - I am not so young as to leap from death's door as quickly as you.", new ArrayList<>() {{
        add("Per_Morgue_Player_029");
    }});

    //endregion

    //region ---- Player

    // On enter
    public static final DialogueOption Per_Morgue_Player_001 = new DialogueOption("Per_Morgue_Player_001", StaticUtils.NAME_PLAYER,
            "I thought you were dead.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Player_002 = new DialogueOption("Per_Morgue_Player_002", StaticUtils.NAME_PLAYER,
            "Slept too long? You looked dead when I came in.", "Per_Morgue_Kreia_002");

    // Kolto tank
    public static final DialogueOption Per_Morgue_Player_003 = new DialogueOption("Per_Morgue_Player_003", StaticUtils.NAME_PLAYER,
            "A little disoriented... was it your voice I heard in the kolto tank?", "Per_Morgue_Kreia_003");
    public static final DialogueOption Per_Morgue_Player_004 = new DialogueOption("Per_Morgue_Player_004", StaticUtils.NAME_PLAYER,
            "Your voice - I heard it as I floated in the kolto tank.", "Per_Morgue_Kreia_003");

    // Who is Kreia
    public static final DialogueOption Per_Morgue_Player_005 = new DialogueOption("Per_Morgue_Player_005", StaticUtils.NAME_PLAYER,
            "Who are you?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_006 = new DialogueOption("Per_Morgue_Player_006", StaticUtils.NAME_PLAYER,
            "The kolto tank left me a little drained... who are you?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_007 = new DialogueOption("Per_Morgue_Player_007", StaticUtils.NAME_PLAYER,
            "Just stay out of my mind in the future. Now what do you want?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_008 = new DialogueOption("Per_Morgue_Player_008", StaticUtils.NAME_PLAYER,
            "Enough with the false concern - what do you want?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_009 = new DialogueOption("Per_Morgue_Player_009", StaticUtils.NAME_PLAYER,
            "So you can touch minds... and feign death. Who are you?", "Per_Morgue_Kreia_004");

    // What happened?
    public static final DialogueOption Per_Morgue_Player_010 = new DialogueOption("Per_Morgue_Player_010", StaticUtils.NAME_PLAYER,
            "Last thing I remember, I was on board a Republic ship, the Harbinger... what happened to it?", "Per_Morgue_Kreia_005");
    public static final DialogueOption Per_Morgue_Player_011 = new DialogueOption("Per_Morgue_Player_011", StaticUtils.NAME_PLAYER,
            "I'm the one asking the questions. How did I get here?", "Per_Morgue_Kreia_006");

    // I'm not a jedi
    public static final DialogueOption Per_Morgue_Player_012 = new DialogueOption("Per_Morgue_Player_012", StaticUtils.NAME_PLAYER,
            "If you think I'm a Jedi, you're mistaken.", "Per_Morgue_Kreia_007");
    public static final DialogueOption Per_Morgue_Player_013 = new DialogueOption("Per_Morgue_Player_013", StaticUtils.NAME_PLAYER,
            "I am no longer a member of the Jedi Order.", "Per_Morgue_Kreia_007");
    public static final DialogueOption Per_Morgue_Player_014 = new DialogueOption("Per_Morgue_Player_014", StaticUtils.NAME_PLAYER,
            "How do you know I was a Jedi?", "Per_Morgue_Kreia_007");

    public static final DialogueOption Per_Morgue_Player_015 = new DialogueOption("Per_Morgue_Player_015", StaticUtils.NAME_PLAYER,
            "The Jedi Order and I have a... troubled history.", "Per_Morgue_Kreia_008");
    public static final DialogueOption Per_Morgue_Player_016 = new DialogueOption("Per_Morgue_Player_016", StaticUtils.NAME_PLAYER,
            "That is no business of yours.", "Per_Morgue_Kreia_008");

    // What now?
    public static final DialogueOption Per_Morgue_Player_017 = new DialogueOption("Per_Morgue_Player_017", StaticUtils.NAME_PLAYER,
            "Let's deal with the now. What is this place?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_018 = new DialogueOption("Per_Morgue_Player_018", StaticUtils.NAME_PLAYER,
            "What is this place?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_019 = new DialogueOption("Per_Morgue_Player_019", StaticUtils.NAME_PLAYER,
            "All right - what's going on? How did we get here?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_020 = new DialogueOption("Per_Morgue_Player_020", StaticUtils.NAME_PLAYER,
            "Very well. How do we get out of here?", "Per_Morgue_Kreia_009");

    public static final DialogueOption Per_Morgue_Player_021 = new DialogueOption("Per_Morgue_Player_021", StaticUtils.NAME_PLAYER,
            "Why do we need to leave?", "Per_Morgue_Kreia_010");
    public static final DialogueOption Per_Morgue_Player_022 = new DialogueOption("Per_Morgue_Player_022", StaticUtils.NAME_PLAYER,
            "Care to explain why you're in such a hurry?", "Per_Morgue_Kreia_010");
    public static final DialogueOption Per_Morgue_Player_023 = new DialogueOption("Per_Morgue_Player_023", StaticUtils.NAME_PLAYER,
            "\"We?\" Why do you think we are together in this?", "Per_Morgue_Kreia_010");

    // Awareness Check if player awareness is at least 4, otherwise skip this line and load in the next one
    public static final DialogueOption Per_Morgue_Player_024 = new DialogueOption("Per_Morgue_Player_024", StaticUtils.NAME_PLAYER,
            "[Awareness (4)] You seem nervous, worried. Is something wrong?", "Per_Morgue_Kreia_011", new DialogueConditions(Enums.entitySkills.ENTITY_SKILLS_AWARENESS, 4));

    // What to do now?
    public static final DialogueOption Per_Morgue_Player_025 = new DialogueOption("Per_Morgue_Player_025", StaticUtils.NAME_PLAYER,
            "We'll see. There's got to be someone left alive around here.", "Per_Morgue_Kreia_012");
    public static final DialogueOption Per_Morgue_Player_026 = new DialogueOption("Per_Morgue_Player_026", StaticUtils.NAME_PLAYER,
            "I'll go look for our ship - and some weapons.", "Per_Morgue_Kreia_012");
    public static final DialogueOption Per_Morgue_Player_027 = new DialogueOption("Per_Morgue_Player_027", StaticUtils.NAME_PLAYER,
            "Is there anything else I should keep an eye out for?", "Per_Morgue_Kreia_012");

    //endregion

    //region ---- Exit dialogue

    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_001 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_001", StaticUtils.NAME_PLAYER,
            "Are you well enough to travel?", "Per_Morgue_Kreia_013");

    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_002 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_002", StaticUtils.NAME_PLAYER,
            "Very well. I'll take a look around.", StaticUtils.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_003 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_003", StaticUtils.NAME_PLAYER,
            "I'll return soon to make sure you're all right", StaticUtils.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_004 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_004", StaticUtils.NAME_PLAYER,
            "Just stay out of my way, or you'll end up like these corpses.", StaticUtils.DIALOGUE_EXIT_CONDITION);

    public static final DialogueOption Per_Morgue_EXIT_TELEPATHY_001 = new DialogueOption("Per_Morgue_EXIT_TELEPATHY_001", StaticUtils.NAME_PLAYER,
            "Just stay here and be quiet.", StaticUtils.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_TELEPATHY_002 = new DialogueOption("Per_Morgue_EXIT_TELEPATHY_002", StaticUtils.NAME_PLAYER,
            "All right, then - I'll be back.", StaticUtils.DIALOGUE_EXIT_CONDITION);

    public static final DialogueOption Per_Morgue_EXIT_001 = new DialogueOption("Per_Morgue_EXIT_001", StaticUtils.NAME_PLAYER,
            "Just checking to make sure you're all right.", StaticUtils.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_002 = new DialogueOption("Per_Morgue_EXIT_002", StaticUtils.NAME_PLAYER,
            "Nothing yet. I'll return later.", StaticUtils.DIALOGUE_EXIT_CONDITION);

    //endregion

    //region ---- Per_Morgue - Telepathy

    //region ---- Kreia

    public static final DialogueOption Per_Morgue_Telepathy_Kreia_001 = new DialogueOption("Per_Morgue_Telepathy_Kreia_001", StaticUtils.NAME_KREIA,
            "Ah yes. I noticed that as well. It may be that our proximity during our long slumber may have had... unforeseen consequences.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_002 = new DialogueOption("Per_Morgue_Telepathy_Kreia_002", StaticUtils.NAME_KREIA,
            "We seem to be able to speak without speaking. Perhaps this effect will pass with time.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_003 = new DialogueOption("Per_Morgue_Telepathy_Kreia_003", StaticUtils.NAME_KREIA,
            "Then hope that we have little to say to each other, lest it prove distracting.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_004 = new DialogueOption("Per_Morgue_Telepathy_Kreia_004", StaticUtils.NAME_KREIA,
            "I have little choice - and neither do you. It is an advantage to us both, and I suggest we make use of it.", "Per_Morgue_Kreia_002");

    //endregion

    //region ---- Player

    public static final DialogueOption Per_Morgue_Telepathy_Player_001 = new DialogueOption("Per_Morgue_Telepathy_Player_001", StaticUtils.NAME_PLAYER,
            "I keep hearing your voice as I explore this place.", "Per_Morgue_Telepathy_Kreia_001", new DialogueConditions());
    public static final DialogueOption Per_Morgue_Telepathy_Player_002 = new DialogueOption("Per_Morgue_Telepathy_Player_002", StaticUtils.NAME_PLAYER,
            "Unforeseen consequences?", "Per_Morgue_Telepathy_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Player_003 = new DialogueOption("Per_Morgue_Telepathy_Player_003", StaticUtils.NAME_PLAYER,
            "What if it doesn't pass?", "Per_Morgue_Telepathy_Kreia_003");
    public static final DialogueOption Per_Morgue_Telepathy_Player_004 = new DialogueOption("Per_Morgue_Telepathy_Player_004", StaticUtils.NAME_PLAYER,
            "I don't care... just stay out of my head, got it?", "Per_Morgue_Telepathy_Kreia_004");
    public static final DialogueOption Per_Morgue_Telepathy_Player_005 = new DialogueOption("Per_Morgue_Telepathy_Player_005", StaticUtils.NAME_PLAYER,
            "Just stay here and be quiet.", StaticUtils.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_Telepathy_Player_006 = new DialogueOption("Per_Morgue_Telepathy_Player_006", StaticUtils.NAME_PLAYER,
            "All right, then - I'll be back.", StaticUtils.DIALOGUE_EXIT_CONDITION);

    //endregion

    //endregion

    //endregion

    //endregion

    CreateDialogueFiles() {

        DialogueTree dialogueTree;
        ArrayList<DialogueObject> dialogueOptions;

        //region Awaken 001

        dialogueOptions = new ArrayList<>();

        // Add dialogue to the list
        dialogueOptions.add(Per_Intro_Kreia_001);

        // Create the dialogue tree
        dialogueTree = new DialogueTree(ResourcePaths.KREIA_001, ResourcePaths.DIALOGUE_PERAGUS_PATH + ResourcePaths.KREIA_001, dialogueOptions);

        // And create the new dialogue file
        createFile(ResourcePaths.KREIA_001, dialogueTree, ResourcePaths.DIALOGUE_PERAGUS_PATH);

        //endregion

        //region Kreia 002

        dialogueOptions = new ArrayList<>();

        // Add dialogue to the list

        // Kreia
        dialogueOptions.add(Per_Morgue_Kreia_GREETINGS_001);
        dialogueOptions.add(Per_Morgue_Kreia_001);
        dialogueOptions.add(Per_Morgue_Kreia_002);
        dialogueOptions.add(Per_Morgue_Kreia_003);
        dialogueOptions.add(Per_Morgue_Kreia_004);
        dialogueOptions.add(Per_Morgue_Kreia_005);
        dialogueOptions.add(Per_Morgue_Kreia_006);
        dialogueOptions.add(Per_Morgue_Kreia_007);
        dialogueOptions.add(Per_Morgue_Kreia_008);
        dialogueOptions.add(Per_Morgue_Kreia_009);
        dialogueOptions.add(Per_Morgue_Kreia_010);
        dialogueOptions.add(Per_Morgue_Kreia_011);
        dialogueOptions.add(Per_Morgue_Kreia_012);
        dialogueOptions.add(Per_Morgue_Kreia_013);

        // Player
        dialogueOptions.add(Per_Morgue_Player_001);
        dialogueOptions.add(Per_Morgue_Player_002);
        dialogueOptions.add(Per_Morgue_Player_003);
        dialogueOptions.add(Per_Morgue_Player_004);
        dialogueOptions.add(Per_Morgue_Player_005);
        dialogueOptions.add(Per_Morgue_Player_006);
        dialogueOptions.add(Per_Morgue_Player_007);
        dialogueOptions.add(Per_Morgue_Player_008);
        dialogueOptions.add(Per_Morgue_Player_009);
        dialogueOptions.add(Per_Morgue_Player_010);
        dialogueOptions.add(Per_Morgue_Player_011);
        dialogueOptions.add(Per_Morgue_Player_012);
        dialogueOptions.add(Per_Morgue_Player_013);
        dialogueOptions.add(Per_Morgue_Player_014);
        dialogueOptions.add(Per_Morgue_Player_015);
        dialogueOptions.add(Per_Morgue_Player_016);
        dialogueOptions.add(Per_Morgue_Player_017);
        dialogueOptions.add(Per_Morgue_Player_018);
        dialogueOptions.add(Per_Morgue_Player_019);
        dialogueOptions.add(Per_Morgue_Player_020);
        dialogueOptions.add(Per_Morgue_Player_021);
        dialogueOptions.add(Per_Morgue_Player_022);
        dialogueOptions.add(Per_Morgue_Player_023);
        dialogueOptions.add(Per_Morgue_Player_024);
        dialogueOptions.add(Per_Morgue_Player_025);
        dialogueOptions.add(Per_Morgue_Player_026);
        dialogueOptions.add(Per_Morgue_Player_027);

        // Exit
        dialogueOptions.add(Per_Morgue_EXIT_FIRST_TIME_001);
        dialogueOptions.add(Per_Morgue_EXIT_FIRST_TIME_002);
        dialogueOptions.add(Per_Morgue_EXIT_FIRST_TIME_003);
        dialogueOptions.add(Per_Morgue_EXIT_FIRST_TIME_004);

        dialogueOptions.add(Per_Morgue_EXIT_TELEPATHY_001);
        dialogueOptions.add(Per_Morgue_EXIT_TELEPATHY_002);

        dialogueOptions.add(Per_Morgue_EXIT_001);
        dialogueOptions.add(Per_Morgue_EXIT_002);

        // Telepathy
        dialogueOptions.add(Per_Morgue_Telepathy_Kreia_001);
        dialogueOptions.add(Per_Morgue_Telepathy_Kreia_002);
        dialogueOptions.add(Per_Morgue_Telepathy_Kreia_003);
        dialogueOptions.add(Per_Morgue_Telepathy_Kreia_004);

        dialogueOptions.add(Per_Morgue_Telepathy_Player_001);
        dialogueOptions.add(Per_Morgue_Telepathy_Player_002);
        dialogueOptions.add(Per_Morgue_Telepathy_Player_003);
        dialogueOptions.add(Per_Morgue_Telepathy_Player_004);
        dialogueOptions.add(Per_Morgue_Telepathy_Player_005);
        dialogueOptions.add(Per_Morgue_Telepathy_Player_006);

        // Create the dialogue tree
        dialogueTree = new DialogueTree(ResourcePaths.KREIA_002, ResourcePaths.DIALOGUE_PERAGUS_PATH + ResourcePaths.KREIA_002, dialogueOptions);

        // And create the new dialogue file
        createFile(ResourcePaths.KREIA_002, dialogueTree, ResourcePaths.DIALOGUE_PERAGUS_PATH);

        //endregion

        // Cleanup
        dialogueTree = null;
        dialogueOptions.clear();
    }

    void createFile(String fileName, DialogueTree newDialogueTree, String locationPath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String directoryPath = System.getProperty("user.dir") + ResourcePaths.DIALOGUE_PATH + locationPath;

        new File(directoryPath).mkdirs();

        String filePath = String.format("%s/%s%s", directoryPath, fileName, ResourcePaths.EXTENSION_DIALOGUE);

        try (Writer writer = new FileWriter(filePath)) {
            gson.toJson(newDialogueTree, writer);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
