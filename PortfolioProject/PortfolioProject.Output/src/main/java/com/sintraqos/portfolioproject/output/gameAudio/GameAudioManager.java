package com.sintraqos.portfolioproject.output.gameAudio;

import com.sintraqos.portfolioproject.output.gameGUI.GameSettings;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.output.ResourcePaths;
import com.sintraqos.portfolioproject.statics.Enums;

import java.lang.Math;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.sampled.*;

public class GameAudioManager {
    // Get instance
    static GameAudioManager instance;

    public static GameAudioManager getInstance() {
        if (instance == null) {
            instance = new GameAudioManager();

            //Load in all the audio files as AudioClips so they're ready to be played at anytime
            instance.LoadAudioFiles();
            // Set the volumes to their proper calculated values
            instance.SetVolume(Enums.audioType.AUDIO_TYPE_MASTER, OutputManager.getInstance().getSettings().masterVolume);

            instance.PlayAudio(ResourcePaths.mainMenuOSTName, Enums.audioType.AUDIO_TYPE_MUSIC);
        }

        return instance;
    }

    public HashMap<String,AudioClip> audioClips = new HashMap<>();

    Clip musicClip;
    Clip sfxClip;
    Clip dialogueClip;
    float masterVolume = 1;
    float musicVolume = 1;
    float sfxVolume = 1;
    float dialogueVolume = 1;

    void LoadAudioFiles() {
        audioClips = new HashMap<String, AudioClip>();

        // Music
        audioClips.put(ResourcePaths.mainMenuOSTName, new AudioClip(ResourcePaths.mainMenuOSTName, ResourcePaths.audioPath + ResourcePaths.soundTrackPath + ResourcePaths.mainMenuOSTName));

        // Gui
        audioClips.put(ResourcePaths.guiClickName, new AudioClip(ResourcePaths.guiClickName, ResourcePaths.audioPath + ResourcePaths.soundEffectPath + ResourcePaths.guiSoundEffectPath + ResourcePaths.guiClickName));
        audioClips.put(ResourcePaths.guiCloseName, new AudioClip(ResourcePaths.guiCloseName, ResourcePaths.audioPath + ResourcePaths.soundEffectPath + ResourcePaths.guiSoundEffectPath + ResourcePaths.guiCloseName));
        audioClips.put(ResourcePaths.guiPromptName, new AudioClip(ResourcePaths.guiPromptName, ResourcePaths.audioPath + ResourcePaths.soundEffectPath + ResourcePaths.guiSoundEffectPath + ResourcePaths.guiPromptName));
    }

    public void SetVolume(Enums.audioType audioType, float volume) {
        switch (audioType) {
            case AUDIO_TYPE_MASTER:
                masterVolume = volume;
                SetVolume(Enums.audioType.AUDIO_TYPE_MUSIC, musicVolume);
                SetVolume(Enums.audioType.AUDIO_TYPE_SFX, sfxVolume);
                SetVolume(Enums.audioType.AUDIO_TYPE_DIALOGUE, dialogueVolume);

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
        if (musicClip != null ) {
            SetVolume(musicClip, musicVolume);
        }
        if (sfxClip != null ) {
            SetVolume(sfxClip, sfxVolume);
        }
        if (dialogueClip != null ) {
            SetVolume(dialogueClip, dialogueVolume);
        }
    }

    public float GetVolume(Enums.audioType audioType) {
        switch (audioType) {
            case AUDIO_TYPE_MUSIC:
                return musicVolume;
            case AUDIO_TYPE_SFX:
                return sfxVolume;
            case AUDIO_TYPE_DIALOGUE:
                return dialogueVolume;
            default:
                return masterVolume;
        }
    }

    public void PlayAudio(String audioName, Enums.audioType audioType) {
        PlayAudio(audioName, audioType, -1);
    }

    public  void PlayAudio(String audioName, Enums.audioType audioType, int loopCount)
    {
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
            SetVolume(clip, audioVolume);
            clip.start();
            clip.loop(loopCount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void StopAudio() {
        musicClip.stop();
    }

    // For stuff like button clicks or other sound effects that only need to be played once
    public void PlayOneShotAudio(String audioName, Enums.audioType audioType) {
        GameSettings settings = OutputManager.getInstance().getSettings();
        float audioVolume = 0;
        switch (audioType) {
            case AUDIO_TYPE_MUSIC -> audioVolume = settings.musicVolume;
            case AUDIO_TYPE_SFX -> audioVolume = settings.sfxVolume;
            case AUDIO_TYPE_DIALOGUE -> audioVolume = settings.dialogueVolume;
        }
        ExecutorService executor = Executors.newScheduledThreadPool(1);          // Create new service
        executor.submit(new OneShotAudioTask(audioClips.get(audioName).audioInputStream, audioVolume));
        executor.shutdown();
    }

    public void SetVolume(Clip clip, float volume) {
        volume = Math.max(0, Math.min(1, volume));

        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(20f * (float) Math.log10(volume));
    }

    static class OneShotAudioTask extends Thread {
        AudioInputStream audioInputStream;
        float audioVolume;

        @Override
        public void run() {
            Clip clip;

            try {
                clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                GameAudioManager.getInstance().SetVolume(clip, audioVolume);
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

        public OneShotAudioTask(AudioInputStream audioInputStream, float audioVolume) {
            this.audioInputStream = audioInputStream;
            this.audioVolume = audioVolume;
        }
    }
}
