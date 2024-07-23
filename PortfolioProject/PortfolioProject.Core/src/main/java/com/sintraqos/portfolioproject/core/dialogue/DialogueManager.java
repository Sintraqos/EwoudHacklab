package com.sintraqos.portfolioproject.core.dialogue;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.core.gamemanager.GameManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.Statics;

import java.io.*;
import java.util.ArrayList;

public class DialogueManager {

    // TODO: create basic dialogue list
    // Then create dialogue tree that can jump around from point a to b etc.
    // Check if the dialogue gives allignment points
    // When dialogue is finished invoke allignment event

    // Get instance
    static DialogueManager instance;

    public static DialogueManager getInstance() {
        if (instance == null) {
            instance = new DialogueManager();
        }

        return instance;
    }

    public void CreateTestDialogueFile(){

        DialogueTree testTree = new DialogueTree(new ArrayList<>(){{
            // Kreia
            add(Per_Morgue_Kreia_GREETINGS_001);
            add(Per_Morgue_Kreia_001);
            add(Per_Morgue_Kreia_002);
            add(Per_Morgue_Kreia_003);
            add(Per_Morgue_Kreia_004);
            add(Per_Morgue_Kreia_005);
            add(Per_Morgue_Kreia_006);
            add(Per_Morgue_Kreia_007);
            add(Per_Morgue_Kreia_008);
            add(Per_Morgue_Kreia_009);
            add(Per_Morgue_Kreia_010);
            add(Per_Morgue_Kreia_011);
            add(Per_Morgue_Kreia_012);
            add(Per_Morgue_Kreia_013);

            // Player
            add(Per_Morgue_Player_001);
            add(Per_Morgue_Player_002);
            add(Per_Morgue_Player_003);
            add(Per_Morgue_Player_004);
            add(Per_Morgue_Player_005);
            add(Per_Morgue_Player_006);
            add(Per_Morgue_Player_007);
            add(Per_Morgue_Player_008);
            add(Per_Morgue_Player_009);
            add(Per_Morgue_Player_010);
            add(Per_Morgue_Player_011);
            add(Per_Morgue_Player_012);
            add(Per_Morgue_Player_013);
            add(Per_Morgue_Player_014);
            add(Per_Morgue_Player_015);
            add(Per_Morgue_Player_016);
            add(Per_Morgue_Player_017);
            add(Per_Morgue_Player_018);
            add(Per_Morgue_Player_019);
            add(Per_Morgue_Player_020);
            add(Per_Morgue_Player_021);
            add(Per_Morgue_Player_022);
            add(Per_Morgue_Player_023);
            add(Per_Morgue_Player_024);
            add(Per_Morgue_Player_025);
            add(Per_Morgue_Player_026);
            add(Per_Morgue_Player_027);
            add(Per_Morgue_Player_028);

            // Exit
            add(Per_Morgue_EXIT_FIRST_TIME_001);
            add(Per_Morgue_EXIT_FIRST_TIME_002);
            add(Per_Morgue_EXIT_FIRST_TIME_003);

            add(Per_Morgue_EXIT_TELEPATHY_001);
            add(Per_Morgue_EXIT_TELEPATHY_002);

            add(Per_Morgue_EXIT_001);
            add(Per_Morgue_EXIT_002);
        }});


        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "TestDialogue");

        // Check if there is already a settings file present
        if (new File(filePath).exists()) {
            try (Reader reader = new FileReader(filePath)) {
                Console.StringOutput("Successfully read from dialogue file");
                //GameSettings.getInstance().setGameSettings(gson.fromJson(reader, GameSettings.class));

            } catch (IOException e) {
                Console.StringOutput("Failed to read from dialogue file");
                throw new RuntimeException();
            }
        }
        // Create a new settings file
        else {
            try (Writer writer = new FileWriter(filePath)) {
                Console.StringOutput("Successfully created new dialogue file");
                gson.toJson(testTree, writer);
            } catch (IOException e) {
                //Console.StringOutput("Failed to create new dialogue file");
                throw new RuntimeException();
            }
        }
    }

    //region Dialogue

    //region ---- EntityNames

    public static final String playerName = "%PLAYERNAME%";
    public static final String kreiaName = "Kreia";

    //endregion

    //region ---- Per_Morgue

    //region ---- Kreia

    // On enter
    public static final DialogueObject Per_Morgue_Kreia_GREETINGS_001 = new DialogueObject("Per_Morgue_Kreia_013", kreiaName, "Yes? What have you found?", new ArrayList<>() {{
        add("Per_Morgue_Player_029");
    }});


    public static final DialogueObject Per_Morgue_Kreia_001 = new DialogueObject("Per_Morgue_Kreia_001", kreiaName, "Find what you're looking for amongst the dead?", new ArrayList<>() {{
        add("Per_Morgue_Player_001");
        add("Per_Morgue_Player_002");
    }});

    // Kolto tank
    public static final DialogueObject Per_Morgue_Kreia_002 = new DialogueObject("Per_Morgue_Kreia_002", kreiaName, "Close to death, yes, closer than I'd like. You have the smell of the kolto tank about you... how do you feel?", new ArrayList<>() {{
        add("Per_Morgue_Player_003");
        add("Per_Morgue_Player_004");
    }});

    // Who is Kreia
    public static final DialogueObject Per_Morgue_Kreia_003 = new DialogueObject("Per_Morgue_Kreia_003", kreiaName, "Yes, I had hoped as much - I slept here too long, and could not awaken. It may be I reached out unconsciously, and your mind must have been a willing one. Or perhaps you have been trained for such things?", new ArrayList<>() {{
        add("Per_Morgue_Player_005");
        add("Per_Morgue_Player_006");
        add("Per_Morgue_Player_007");
        add("Per_Morgue_Player_008");
        add("Per_Morgue_Player_009");
    }});

    // What happened?
    public static final DialogueObject Per_Morgue_Kreia_004 = new DialogueObject("Per_Morgue_Kreia_004", kreiaName, "I am Kreia, and I am your rescuer - as you are mine. Tell me - do you recall what happened?", new ArrayList<>() {{
        add("Per_Morgue_Player_010");
        add("Per_Morgue_Player_011");
    }});

    public static final DialogueObject Per_Morgue_Kreia_005 = new DialogueObject("Per_Morgue_Kreia_005", kreiaName, "Your ship was attacked. You were the only survivor... a result of your Jedi training, no doubt.", new ArrayList<>() {{
        add("Per_Morgue_Player_012");
        add("Per_Morgue_Player_013");
        add("Per_Morgue_Player_014");
    }});
    public static final DialogueObject Per_Morgue_Kreia_006 = new DialogueObject("Per_Morgue_Kreia_006", kreiaName, "I confess I know little more than you do... I do not know where here is. I do recall rescuing you... the Republic ship you were on was attacked, and you were the only survivor. A result of your Jedi training, no doubt.", new ArrayList<>() {{
        add("Per_Morgue_Player_012");
        add("Per_Morgue_Player_013");
        add("Per_Morgue_Player_014");
    }});

    // I'm not a jedi
    public static final DialogueObject Per_Morgue_Kreia_007 = new DialogueObject("Per_Morgue_Kreia_007", kreiaName, "Your stance, your walk tells me you are a Jedi. Your walk is heavy, you carry something that weighs you down.", new ArrayList<>() {{
        add("Per_Morgue_Player_015");
        add("Per_Morgue_Player_016");
        add("Per_Morgue_Player_017");
    }});

    public static final DialogueObject Per_Morgue_Kreia_008 = new DialogueObject("Per_Morgue_Kreia_008", kreiaName, "So it would seem. Keep your past - and let us focus on the now.", new ArrayList<>() {{
        add("Per_Morgue_Player_015");
        add("Per_Morgue_Player_016");
        add("Per_Morgue_Player_017");
    }});

    // What now?
    public static final DialogueObject Per_Morgue_Kreia_009 = new DialogueObject("Per_Morgue_Kreia_009", kreiaName, "I do not know. I was removed from the events of the world as I slept. A survey of the surroundings may provide the answers we seek. The ship we arrived in must still be in this place. We should recover it and leave.", new ArrayList<>() {{
        add("Per_Morgue_Player_021");
        add("Per_Morgue_Player_022");
        add("Per_Morgue_Player_023");
    }});

    public static final DialogueObject Per_Morgue_Kreia_010 = new DialogueObject("Per_Morgue_Kreia_010", kreiaName, "We were attacked once, and I fear our attackers will not give up the hunt so easily - without transport, weapons, and information, they will find us easy prey indeed.", new ArrayList<>() {{
        add("Per_Morgue_Player_024");
        add("Per_Morgue_Player_025");
        add("Per_Morgue_Player_026");
        add("Per_Morgue_Player_027");
    }});

    public static final DialogueObject Per_Morgue_Kreia_011 = new DialogueObject("Per_Morgue_Kreia_011", kreiaName, "Even as I slept, I felt much unrest here - I saw strange visions, minds colored with fear - now, everything here feels terribly silent. I would find out as much as you can about this place quickly - I fear we will need to depart as suddenly as we arrived.", new ArrayList<>() {{
        add("Per_Morgue_Player_025");
        add("Per_Morgue_Player_026");
        add("Per_Morgue_Player_027");
    }});

    public static final DialogueObject Per_Morgue_Kreia_012 = new DialogueObject("Per_Morgue_Kreia_012", kreiaName, "You may wish to extend your search to some clothes... if only for proper first impressions.", new ArrayList<>() {{
        add("Per_Morgue_Player_028");
    }});

    public static final DialogueObject Per_Morgue_Kreia_013 = new DialogueObject("Per_Morgue_Kreia_013", kreiaName, "I am not offering to help you - I am not so young as to leap from death's door as quickly as you.", new ArrayList<>() {{
        add("Per_Morgue_Player_029");
    }});

    //endregion

    //region ---- Player

    // On enter
    public static final DialogueOption Per_Morgue_Player_001 = new DialogueOption("Per_Morgue_Player_001", playerName, "I thought you were dead.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Player_002 = new DialogueOption("Per_Morgue_Player_002", playerName, "Slept too long? You looked dead when I came in.", "Per_Morgue_Kreia_002");

    // Kolto tank
    public static final DialogueOption Per_Morgue_Player_003 = new DialogueOption("Per_Morgue_Player_003", playerName, "A little disoriented... was it your voice I heard in the kolto tank?", "Per_Morgue_Kreia_003");
    public static final DialogueOption Per_Morgue_Player_004 = new DialogueOption("Per_Morgue_Player_004", playerName, "Your voice - I heard it as I floated in the kolto tank.", "Per_Morgue_Kreia_003");

    // Who is Kreia
    public static final DialogueOption Per_Morgue_Player_005 = new DialogueOption("Per_Morgue_Player_005", playerName, "Who are you?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_006 = new DialogueOption("Per_Morgue_Player_006", playerName, "The kolto tank left me a little drained... who are you?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_007 = new DialogueOption("Per_Morgue_Player_007", playerName, "Just stay out of my mind in the future. Now what do you want?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_008 = new DialogueOption("Per_Morgue_Player_008", playerName, "Enough with the false concern - what do you want?", "Per_Morgue_Kreia_004");
    public static final DialogueOption Per_Morgue_Player_009 = new DialogueOption("Per_Morgue_Player_009", playerName, "So you can touch minds... and feign death. Who are you?", "Per_Morgue_Kreia_004");

    // What happened?
    public static final DialogueOption Per_Morgue_Player_010 = new DialogueOption("Per_Morgue_Player_010", playerName, "Last thing I remember, I was on board a Republic ship, the Harbinger... what happened to it?", "Per_Morgue_Kreia_005");
    public static final DialogueOption Per_Morgue_Player_011 = new DialogueOption("Per_Morgue_Player_011", playerName, "I'm the one asking the questions. How did I get here?", "Per_Morgue_Kreia_006");

    // I'm not a jedi
    public static final DialogueOption Per_Morgue_Player_012 = new DialogueOption("Per_Morgue_Player_012", playerName, "If you think I'm a Jedi, you're mistaken.", "Per_Morgue_Kreia_007");
    public static final DialogueOption Per_Morgue_Player_013 = new DialogueOption("Per_Morgue_Player_013", playerName, "I am no longer a member of the Jedi Order.", "Per_Morgue_Kreia_007");
    public static final DialogueOption Per_Morgue_Player_014 = new DialogueOption("Per_Morgue_Player_014", playerName, "How do you know I was a Jedi?", "Per_Morgue_Kreia_007");

    public static final DialogueOption Per_Morgue_Player_015 = new DialogueOption("Per_Morgue_Player_015", playerName, "The Jedi Order and I have a... troubled history.", "Per_Morgue_Kreia_008");
    public static final DialogueOption Per_Morgue_Player_016 = new DialogueOption("Per_Morgue_Player_016", playerName, "That is no business of yours.", "Per_Morgue_Kreia_008");

    // What now?
    public static final DialogueOption Per_Morgue_Player_017 = new DialogueOption("Per_Morgue_Player_017", playerName, "Let's deal with the now. What is this place?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_018 = new DialogueOption("Per_Morgue_Player_018", playerName, "What is this place?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_019 = new DialogueOption("Per_Morgue_Player_019", playerName, "All right - what's going on? How did we get here?", "Per_Morgue_Kreia_009");
    public static final DialogueOption Per_Morgue_Player_020 = new DialogueOption("Per_Morgue_Player_020", playerName, "Very well. How do we get out of here?", "Per_Morgue_Kreia_009");

    public static final DialogueOption Per_Morgue_Player_021 = new DialogueOption("Per_Morgue_Player_021", playerName, "Why do we need to leave?", "Per_Morgue_Kreia_010");
    public static final DialogueOption Per_Morgue_Player_022 = new DialogueOption("Per_Morgue_Player_022", playerName, "Care to explain why you're in such a hurry?", "Per_Morgue_Kreia_010");
    public static final DialogueOption Per_Morgue_Player_023 = new DialogueOption("Per_Morgue_Player_023", playerName, "\"We?\" Why do you think we are together in this?", "Per_Morgue_Kreia_010");

    // Awareness Check if player awareness is at least 4, otherwise skip this line and load in the next one
    public static final DialogueOption Per_Morgue_Player_024 = new DialogueOption("Per_Morgue_Player_024", playerName, "[Awareness (4)] You seem nervous, worried. Is something wrong?", "Per_Morgue_Kreia_011", new DialogueConditions(Enums.entitySkills.ENTITY_SKILLS_AWARENESS,4));

    // What to do now?
    public static final DialogueOption Per_Morgue_Player_025 = new DialogueOption("Per_Morgue_Player_025", playerName, "We'll see. There's got to be someone left alive around here.", "Per_Morgue_Kreia_012");
    public static final DialogueOption Per_Morgue_Player_026 = new DialogueOption("Per_Morgue_Player_026", playerName, "I'll go look for our ship - and some weapons.", "Per_Morgue_Kreia_012");
    public static final DialogueOption Per_Morgue_Player_027 = new DialogueOption("Per_Morgue_Player_027", playerName, "Is there anything else I should keep an eye out for?", "Per_Morgue_Kreia_012");

    public static final DialogueOption Per_Morgue_Player_028 = new DialogueOption("Per_Morgue_Player_028", playerName, "Are you well enough to travel?", "Per_Morgue_Kreia_013");

    //endregion

    // Exit dialogue
    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_001 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_001", playerName, "Very well. I'll take a look around.", Statics.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_002 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_002", playerName, "I'll return soon to make sure you're all right", Statics.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_FIRST_TIME_003 = new DialogueOption("Per_Morgue_EXIT_FIRST_TIME_003", playerName, "Just stay out of my way, or you'll end up like these corpses.", Statics.DIALOGUE_EXIT_CONDITION);

    public static final DialogueOption Per_Morgue_EXIT_TELEPATHY_001 = new DialogueOption("Per_Morgue_EXIT_TELEPATHY_001", playerName, "Just stay here and be quiet.", Statics.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_TELEPATHY_002 = new DialogueOption("Per_Morgue_EXIT_TELEPATHY_002", playerName, "All right, then - I'll be back.", Statics.DIALOGUE_EXIT_CONDITION);

    public static final DialogueOption Per_Morgue_EXIT_001 = new DialogueOption("Per_Morgue_EXIT_001", playerName, "Just checking to make sure you're all right.", Statics.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_EXIT_002 = new DialogueOption("Per_Morgue_EXIT_002", playerName, "Nothing yet. I'll return later.", Statics.DIALOGUE_EXIT_CONDITION);

    //region ---- Per_Morgue - Telepathy

    //region ---- Kreia

    public static final DialogueOption Per_Morgue_Telepathy_Kreia_001 = new DialogueOption("Per_Morgue_Telepathy_Kreia_001", kreiaName, "Ah yes. I noticed that as well. It may be that our proximity during our long slumber may have had... unforeseen consequences.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_002 = new DialogueOption("Per_Morgue_Telepathy_Kreia_002", kreiaName, "We seem to be able to speak without speaking. Perhaps this effect will pass with time.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_003 = new DialogueOption("Per_Morgue_Telepathy_Kreia_003", kreiaName, "Then hope that we have little to say to each other, lest it prove distracting.", "Per_Morgue_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Kreia_004 = new DialogueOption("Per_Morgue_Telepathy_Kreia_004", kreiaName, "I have little choice - and neither do you. It is an advantage to us both, and I suggest we make use of it.", "Per_Morgue_Kreia_002");

    //endregion

    //region ---- Player

    public static final DialogueOption Per_Morgue_Telepathy_Player_001 = new DialogueOption("Per_Morgue_Telepathy_Player_001", playerName, "I keep hearing your voice as I explore this place.", "Per_Morgue_Telepathy_Kreia_001", new DialogueConditions());
    public static final DialogueOption Per_Morgue_Telepathy_Player_002 = new DialogueOption("Per_Morgue_Telepathy_Player_002", playerName, "Unforeseen consequences?", "Per_Morgue_Telepathy_Kreia_002");
    public static final DialogueOption Per_Morgue_Telepathy_Player_003 = new DialogueOption("Per_Morgue_Telepathy_Player_003", playerName, "What if it doesn't pass?", "Per_Morgue_Telepathy_Kreia_003");
    public static final DialogueOption Per_Morgue_Telepathy_Player_004 = new DialogueOption("Per_Morgue_Telepathy_Player_004", playerName, "I don't care... just stay out of my head, got it?", "Per_Morgue_Telepathy_Kreia_004");
    public static final DialogueOption Per_Morgue_Telepathy_Player_005 = new DialogueOption("Per_Morgue_Telepathy_Player_005", playerName, "Just stay here and be quiet.", Statics.DIALOGUE_EXIT_CONDITION);
    public static final DialogueOption Per_Morgue_Telepathy_Player_006 = new DialogueOption("Per_Morgue_Telepathy_Player_006", playerName, "All right, then - I'll be back.", Statics.DIALOGUE_EXIT_CONDITION);

    //endregion

    //endregion

    //endregion

    //endregion
}
