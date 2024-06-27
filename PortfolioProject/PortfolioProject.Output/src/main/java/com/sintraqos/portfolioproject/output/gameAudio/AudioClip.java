package com.sintraqos.portfolioproject.output.gameAudio;

import javax.sound.sampled.*;
import java.io.IOException;

public class AudioClip {
    public String audioClipName;
    public String audioClipPath;
    AudioInputStream audioInputStream;

    public AudioClip(String audioClipName, String audioClipPath) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(audioClipPath));
            this.audioClipName = audioClipName;
            this.audioClipPath = audioClipPath;
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
