package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.Enums;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public Map<String, List<AudioClip>> dialogueAudio= new HashMap<>();

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
        Console.StringTitleOutput("Initializing Audio Manager");

        // Set volume
        GameSettings settings = GameSettings.getInstance();
        masterVolume = settings.getMasterVolume();
        musicVolume = settings.getMusicVolume();
        sfxVolume = settings.getSfxVolume();
        dialogueVolume = settings.getDialogueVolume();

        //Load in all the audio files as AudioClips, so they're ready to be played at anytime
        loadAudioFiles();
        // Set the volumes to their proper calculated values
        setVolume(Enums.audioType.AUDIO_TYPE_MASTER, GameSettings.getInstance().getMasterVolume());

        Console.StringOutput("Finished setting up Audio Manager");
        Console.StringOutput();
    }

    void loadAudioFiles() {
        audioClips = new HashMap<>();
        dialogueAudio = new HashMap<>();

        // Music
        audioClips.put(ResourcePaths.OST_MAIN_MENU, new AudioClip(ResourcePaths.OST_MAIN_MENU, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH + ResourcePaths.OST_MAIN_MENU + ResourcePaths.EXTENSION_AUDIO));

        // Gui
        audioClips.put(ResourcePaths.GUI_CLICK, new AudioClip(ResourcePaths.GUI_CLICK, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_CLICK + ResourcePaths.EXTENSION_AUDIO));
        audioClips.put(ResourcePaths.GUI_CLOSE, new AudioClip(ResourcePaths.GUI_CLOSE, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_CLOSE + ResourcePaths.EXTENSION_AUDIO));
        audioClips.put(ResourcePaths.GUI_PROMPT, new AudioClip(ResourcePaths.GUI_PROMPT, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_PROMPT + ResourcePaths.EXTENSION_AUDIO));
        audioClips.put(ResourcePaths.GUI_INVENTORY_ADD, new AudioClip(ResourcePaths.GUI_INVENTORY_ADD, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_INVENTORY_ADD + ResourcePaths.EXTENSION_AUDIO));
        audioClips.put(ResourcePaths.GUI_INVENTORY_REMOVE, new AudioClip(ResourcePaths.GUI_INVENTORY_REMOVE, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_INVENTORY_REMOVE + ResourcePaths.EXTENSION_AUDIO));
        audioClips.put(ResourcePaths.GUI_INVENTORY_SELECT, new AudioClip(ResourcePaths.GUI_INVENTORY_SELECT, ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_EFFECT_PATH + ResourcePaths.GUI_SOUND_EFFECT_PATH + ResourcePaths.GUI_INVENTORY_SELECT + ResourcePaths.EXTENSION_AUDIO));

        // Setup Audio Lists
        ambientAudio = new AudioList();
        // Planet
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_DXUN);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_ONDERON);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_TELOS);

        // Ship
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER);
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER);

        // Other
        ambientAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS);

        // Battle
        battleAudio = new AudioList();
        battleAudio.createAudioClips(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_BATTLE_PREFIX);

        // Dialogue
    }

    public void getDialogueAudio(List<String> dialogueIDs, String dialogueTreeID, String dialogueTreeLocation){
        Console.StringTitleOutput("Getting audio clips for dialogue tree: ");

        dialogueAudio.put(dialogueTreeID, new ArrayList<>());

        for (String dialogueID : dialogueIDs) {
            String audioClipPath = ResourcePaths.AUDIO_PATH + ResourcePaths.DIALOGUE_PATH + dialogueTreeLocation + File.separator + dialogueID + ResourcePaths.EXTENSION_AUDIO;

            if (getClass().getResource(audioClipPath) != null) {
                AudioClip dialogueAudioClip = new AudioClip(dialogueID, audioClipPath);

                dialogueAudio.get(dialogueTreeID).add(dialogueAudioClip);

                Console.StringOutput("Found audio file: " + dialogueAudioClip.getAudioClipName());
            }
        }

        Console.StringOutput("Found audio files for dialogue tree: " + dialogueTreeID);
        Console.StringOutput();
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
        // Check if the audioclip is currently active, if true, set the volume for the given audioClip
        if (musicClip != null) {
            setVolume(musicClip, musicVolume);
        }
        if (sfxClip != null) {
            setVolume(sfxClip, sfxVolume);
        }
        if (dialogueClip != null) {
            setVolume(dialogueClip, dialogueVolume);
        }
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
            Clip clip;
            float audioVolume = 0;
            switch (audioType) {
                case AUDIO_TYPE_MUSIC:
                    audioVolume = musicVolume;
                    clip = musicClip;
                    break;
                case AUDIO_TYPE_SFX:
                    audioVolume = sfxVolume;
                    clip = sfxClip;
                    break;
                case AUDIO_TYPE_DIALOGUE:
                    audioVolume = dialogueVolume;
                    clip = dialogueClip;
                    break;
            }
            clip = AudioSystem.getClip();
            clip.open(audioClips.get(audioName).audioInputStream);
            setVolume(clip, audioVolume);
            clip.start();
            clip.loop(loopCount);
        } catch (IOException | LineUnavailableException ex) {
            throw new   Functions.ExceptionHandler("Failed play audio clip", ex);
        }
    }

    public void stopAudio() {
        musicClip.stop();
    }

    // For stuff like button clicks or other sound effects that only need to be played once
    public void playOneShotAudio(String audioName, Enums.audioType audioType) {
        GameSettings settings = GameSettings.getInstance();
        float audioVolume = 0;
        switch (audioType) {
            case AUDIO_TYPE_MUSIC -> audioVolume = settings.getMusicVolume();
            case AUDIO_TYPE_SFX -> audioVolume = settings.getSfxVolume();
            case AUDIO_TYPE_DIALOGUE -> audioVolume = settings.getDialogueVolume();
            default -> audioVolume = settings.getMasterVolume();
        }

        new Thread(new OneShotAudioTask(audioClips.get(audioName).audioInputStream, audioVolume)).start();
    }

    public void setVolume(Clip clip, float volume) {
        volume = Math.max(0, Math.min(1, volume));

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    static class OneShotAudioTask implements Runnable {
        AudioInputStream audioInputStream;
        float audioVolume;

        @Override
        public void run() {
            Clip clip;

            try {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                GameAudioManager.getInstance().setVolume(clip, audioVolume);
                clip.start();
                audioInputStream.reset();
                Thread.sleep(clip.getMicrosecondLength() + 25);    // The added time is for preventing the clip from cutting off all of a sudden
                clip.close();
            } catch (InterruptedException | LineUnavailableException | IOException ex) {
                Thread.currentThread().interrupt();
                throw new   Functions.ExceptionHandler("Failed play audio clip", ex);
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
