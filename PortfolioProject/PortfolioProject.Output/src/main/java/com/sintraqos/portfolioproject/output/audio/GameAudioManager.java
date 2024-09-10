package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.statics.*;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueListener;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueManager;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueObject;
import com.sintraqos.portfolioproject.statics.dialogue.DialogueTree;

import java.io.IOException;
import java.util.*;

import javax.sound.sampled.*;

public class GameAudioManager {
    // Get instance
    static GameAudioManager instance;

    public static GameAudioManager getInstance() {
        if (instance == null) {
            instance = new GameAudioManager();

            instance.setup();
        }

        return instance;
    }

    public Map<String, AudioClip> audioClips = new HashMap<>();
    public Map<String, Map<String,AudioClip>> dialogueAudio = new HashMap<>();

    Clip musicClip;
    Clip sfxClip;
    Clip dialogueClip;
    float masterVolume = 1;
    float musicVolume = 1;
    float sfxVolume = 1;
    float dialogueVolume = 1;

    AudioList ambientAudio;
    AudioList battleAudio;

    void setup() {
        Console.writeHeader("Setup Audio Manager");

        DialogueResponder responder = new DialogueResponder();
        DialogueManager.getInstance().addListener(responder);

        // Set volume
        masterVolume = GameSettings.getInstance().getMasterVolume();
        musicVolume = GameSettings.getInstance().getMusicVolume();
        sfxVolume = GameSettings.getInstance().getSfxVolume();
        dialogueVolume = GameSettings.getInstance().getDialogueVolume();

        //Load in all the audio files as AudioClips, so they're ready to be played at anytime
        loadAudioFiles();

        // Set the volumes to their proper calculated values
        //setVolume(Enums.audioType.AUDIO_TYPE_MASTER, GameSettings.getInstance().getMasterVolume());

        Console.writeLine("Finished Setup Audio Manager");
        Console.writeLine();
    }

    //region Load Audio

    void loadAudioFiles() {
        audioClips = new HashMap<>();
        dialogueAudio = new HashMap<>();

        // Gui
        Console.writeLine("Loading GUI audio files");
        audioClips.put(ResourcePaths.GUI_CLICK, new AudioClip(ResourcePaths.GUI_CLICK, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_CLICK)));
        audioClips.put(ResourcePaths.GUI_CLOSE, new AudioClip(ResourcePaths.GUI_CLOSE, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_CLOSE)));
        audioClips.put(ResourcePaths.GUI_PROMPT, new AudioClip(ResourcePaths.GUI_PROMPT, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_PROMPT)));
        audioClips.put(ResourcePaths.GUI_INVENTORY_ADD, new AudioClip(ResourcePaths.GUI_INVENTORY_ADD, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_INVENTORY_ADD)));
        audioClips.put(ResourcePaths.GUI_INVENTORY_REMOVE, new AudioClip(ResourcePaths.GUI_INVENTORY_REMOVE, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_INVENTORY_REMOVE)));
        audioClips.put(ResourcePaths.GUI_INVENTORY_SELECT, new AudioClip(ResourcePaths.GUI_INVENTORY_SELECT, ResourcePaths.getGUISoundEffectAudioFile(ResourcePaths.GUI_INVENTORY_SELECT)));
        Console.writeLine();

        // Music
        Console.writeLine("Loading music audio files");
        audioClips.put(ResourcePaths.OST_MAIN_MENU, new AudioClip(ResourcePaths.OST_MAIN_MENU, ResourcePaths.getSoundtrackAudioFile(ResourcePaths.OST_MAIN_MENU)));
        audioClips.put(ResourcePaths.OST_CHARACTER_CREATE, new AudioClip(ResourcePaths.OST_CHARACTER_CREATE, ResourcePaths.getSoundtrackAudioFile(ResourcePaths.OST_CHARACTER_CREATE)));
        Console.writeLine();
    }

    public void loadAudioFiles(String dialogueFile) {
        Console.writeLine("Load Audiofiles: " + dialogueFile);

        Map<String, AudioClip> audioClips = new HashMap<>();

        for (DialogueObject dialogueObject : DialogueManager.getInstance().getDialogueTree(dialogueFile).getDialogueObjects()) {
            AudioClip audioClip = new AudioClip(dialogueObject.getDialogueID(), ResourcePaths.getDialogueAudioFile(ResourcePaths.DIRECTORY_PERAGUS, DialogueManager.getInstance().getDialogueTree(dialogueFile).getDialogueTreeID(), dialogueObject.getDialogueID()));
            audioClips.put(dialogueObject.getDialogueID(), audioClip);
        }

        dialogueAudio.put(dialogueFile, audioClips);
    }

    boolean loadedAudioFiles;

    public boolean hasLoadedAudioFiles() {
        return loadedAudioFiles;
    }

    public void loadAudioFiles(Enums.currentLocation currentLocation) {
        loadedAudioFiles = false;
        // Since there is no need to load in all the audio files from places we can't go to yet just load in the needed audio clips when we get there

        // Setup Audio Lists
        Console.writeLine("Loading audio list");
        ambientAudio = new AudioList();
        battleAudio = new AudioList();

        // Ship
        switch (currentLocation) {
            // Planet
            case CURRENT_LOCATION_DANTOOINE->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE);
            case CURRENT_LOCATION_DXUN ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_DXUN);
            case CURRENT_LOCATION_KORRIBAN ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN);
            case CURRENT_LOCATION_MALACHOR_V ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V);
            case CURRENT_LOCATION_NAR_SHADDAA ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA);
            case CURRENT_LOCATION_ONDERON ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_ONDERON);
            case CURRENT_LOCATION_TELOS ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_TELOS);

            // Ship
            case CURRENT_LOCATION_EBON_HAWK ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK);
            case CURRENT_LOCATION_HARBINGER ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER);
            case CURRENT_LOCATION_RAVAGER ->
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER);

            // Other
            case CURRENT_LOCATION_PERAGUS ->
            {
                    ambientAudio.createAudioClips(ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS);
                    loadAudioFiles(ResourcePaths.PERAGUS_KREIA_DIALOGUE[0]);
                    loadAudioFiles(ResourcePaths.PERAGUS_KREIA_DIALOGUE[1]);
            }
            case CURRENT_LOCATION_BATTLE -> battleAudio.createAudioClips(ResourcePaths.OST_BATTLE_PREFIX);
        }

        loadedAudioFiles = true;
    }

    //endregion

    static class DialogueResponder implements DialogueListener {
        @Override
        public void onDialogueUpdate(String dialogueTreeID, DialogueObject dialogueObject){
            GameAudioManager.getInstance().playDialogueAudio(dialogueTreeID, dialogueObject.getDialogueID());
        }
    }

    public void playDialogueAudio(String dialogueTreeID, String dialogueID){
        AudioClip audioClip = dialogueAudio.get(dialogueTreeID).get(dialogueID);
        Console.writeLine(audioClip.audioClipName);

        playOneShotAudio(audioClip, Enums.audioType.AUDIO_TYPE_DIALOGUE);
    }

    public void setVolume(Enums.audioType audioType, float volume) {
        switch (audioType) {
            case AUDIO_TYPE_MASTER:
                masterVolume = volume;
                setVolume(Enums.audioType.AUDIO_TYPE_MUSIC, musicVolume);
                setVolume(Enums.audioType.AUDIO_TYPE_SFX, sfxVolume);
                setVolume(Enums.audioType.AUDIO_TYPE_DIALOGUE, dialogueVolume);

                break;
            case AUDIO_TYPE_MUSIC:
                musicVolume = volume * masterVolume;
                break;
            case AUDIO_TYPE_SFX:
                sfxVolume = volume * masterVolume;
                break;
            case AUDIO_TYPE_DIALOGUE:
                dialogueVolume = volume * masterVolume;
                break;
        }

        setVolume(musicClip, musicVolume);
        setVolume(sfxClip, sfxVolume);
        setVolume(dialogueClip, dialogueVolume);
    }

    public float getVolume(Enums.audioType audioType) {
        return switch (audioType) {
            case AUDIO_TYPE_MUSIC -> musicVolume;
            case AUDIO_TYPE_SFX -> sfxVolume;
            case AUDIO_TYPE_DIALOGUE -> dialogueVolume;
            default -> masterVolume;
        };
    }

    public float getFixedVolume(Enums.audioType audioType) {
        return switch (audioType) {
            case AUDIO_TYPE_MUSIC -> musicVolume / masterVolume;
            case AUDIO_TYPE_SFX -> sfxVolume / masterVolume;
            case AUDIO_TYPE_DIALOGUE -> dialogueVolume / masterVolume;
            default -> masterVolume;
        };
    }

    public void playAudio(String audioName, Enums.audioType audioType) {
        playAudio(audioName, audioType, -1);
    }

    public void playAudio(String audioName, Enums.audioType audioType, int loopCount) {
        try {
            Clip clip = AudioSystem.getClip();
            float audioVolume = 0;
            switch (audioType) {
                case AUDIO_TYPE_MUSIC:
                    audioVolume = musicVolume;
                    if (musicClip == null) {
                        musicClip = clip;
                    }
                    break;

                case AUDIO_TYPE_SFX:
                    audioVolume = sfxVolume;
                    if (sfxClip == null) {
                        sfxClip = clip;
                    }
                    break;

                case AUDIO_TYPE_DIALOGUE:
                    audioVolume = dialogueVolume;
                    if (dialogueClip == null) {
                        dialogueClip = clip;
                    }
                    break;
            }

            clip.open(audioClips.get(audioName).audioInputStream);
            setVolume(clip, audioVolume);
            clip.start();
            clip.loop(loopCount);

            Console.writeLine("Currently playing: " + audioName);
        } catch (IOException | LineUnavailableException ex) {
            throw new Functions.ExceptionHandler("Failed play audio clip", ex);
        }
    }

    public void stopAudio() {
        musicClip.stop();
    }

    // For stuff like button clicks or other sound effects that only need to be played once
    public void playOneShotAudio(String audioName, Enums.audioType audioType) {
        playOneShotAudio(audioClips.get(audioName), audioType);
    }

    public void playOneShotAudio(AudioClip audioClip, Enums.audioType audioType) {
        GameSettings settings = GameSettings.getInstance();
        float audioVolume = 0;
        switch (audioType) {
            case AUDIO_TYPE_MUSIC -> audioVolume = settings.getMusicVolume();
            case AUDIO_TYPE_SFX -> audioVolume = settings.getSfxVolume();
            case AUDIO_TYPE_DIALOGUE -> audioVolume = settings.getDialogueVolume();
            default -> audioVolume = settings.getMasterVolume();
        }

        new Thread(new OneShotAudioTask(audioClip.audioInputStream, audioVolume)).start();

        Console.writeLine("Currently playing one-shot: " + audioClip.getAudioClipName());
    }

    public void setVolume(Clip clip, float volume) {
        volume = Math.max(0, Math.min(1, volume));

        FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volumeControl.setValue(20f * (float) Math.log10(volume));
    }

    static class OneShotAudioTask implements Runnable {
        AudioInputStream audioInputStream;
        float audioVolume;

        @Override
        public void run() {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                GameAudioManager.getInstance().setVolume(clip, audioVolume);
                clip.start();
                //audioInputStream.reset();
                Thread.sleep(clip.getMicrosecondLength() + 25);    // The added time is for preventing the clip from cutting off all of a sudden
                clip.close();
            } catch (InterruptedException | LineUnavailableException | IOException ex) {
                Thread.currentThread().interrupt();
                throw new Functions.ExceptionHandler("Failed play audio clip", ex);
            }
        }

        public OneShotAudioTask(AudioInputStream audioInputStream, float audioVolume) {
            this.audioInputStream = audioInputStream;
            this.audioVolume = audioVolume;
        }
    }

    //region Dispose

    public void disposeFiles() {
        audioClips = new HashMap<>();
        ambientAudio = new AudioList();
        battleAudio = new AudioList();
    }

    //endregion
}
