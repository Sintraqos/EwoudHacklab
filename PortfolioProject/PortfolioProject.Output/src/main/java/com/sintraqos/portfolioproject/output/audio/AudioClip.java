package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class AudioClip {
    String audioClipName = "AudioClip";
    String audioClipPath = "AudioPath";
    AudioInputStream audioInputStream;

    public String getAudioClipName(){return audioClipName;}
    public String getAudioClipPath(){return audioClipPath;}

    public AudioClip(String audioClipName, String audioClipPath) {

        Console.StringOutput("Created new AudioClip: " + Functions.getFileNameWithoutExtension(audioClipName));

        try {
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource(audioClipPath)));
            this.audioClipName = Functions.getFileNameWithoutExtension(audioClipName);
            this.audioClipPath = audioClipPath;
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new Functions.ExceptionHandler("AudioClip could not be created", ex);
        }
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
