package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.GameSettings;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.Enums;

import java.io.IOException;
import java.util.HashMap;
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

        //Load in all the audio files as AudioClips so they're ready to be played at anytime
        loadAudioFiles();
        // Set the volumes to their proper calculated values
        setVolume(Enums.audioType.AUDIO_TYPE_MASTER, GameSettings.getInstance().getMasterVolume());

        Console.StringOutput("Finished setting up Audio Manager");
    }

    void loadAudioFiles() {
        audioClips = new HashMap<>();

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
        ambientAudio = new AudioList(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_AMBIENT_PREFIXES);
        battleAudio = new AudioList(ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH, ResourcePaths.OST_BATTLE_PREFIX);
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
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
        }
        ExecutorService executor = Executors.newScheduledThreadPool(1);          // Create new service
        executor.submit(new oneShotAudioTask(audioClips.get(audioName).audioInputStream, audioVolume));
        executor.shutdown();
    }

    public void setVolume(Clip clip, float volume) {
        volume = Math.max(0, Math.min(1, volume));

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    static class oneShotAudioTask extends Thread {
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
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (LineUnavailableException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public oneShotAudioTask(AudioInputStream audioInputStream, float audioVolume) {
            this.audioInputStream = audioInputStream;
            this.audioVolume = audioVolume;
        }
    }
}