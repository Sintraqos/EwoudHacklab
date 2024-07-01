package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class AudioClip {
  public   String audioClipName = "AudioClip";
    public String audioClipPath;
    AudioInputStream audioInputStream;

    public AudioClip(String audioClipName, String audioClipPath) {

        Console.StringOutput("Created new AudioClip: " + audioClipName.replace(".wav", ""));

        try {
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource(audioClipPath)));
            this.audioClipName = audioClipName.replace(".wav", "");
            this.audioClipPath = audioClipPath;
        } catch (UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
