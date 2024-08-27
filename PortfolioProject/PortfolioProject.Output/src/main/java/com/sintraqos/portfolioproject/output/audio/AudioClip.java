package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Functions;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public class AudioClip {
    String audioClipName;
    String audioClipPath;
    AudioInputStream audioInputStream;

    public String getAudioClipName() {
        return audioClipName;
    }

    public String getAudioClipPath() {
        return audioClipPath;
    }


    public AudioClip(String audioClipName, String audioClipPath) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Functions.class.getResource(audioClipPath)));
            Console.writeLine("Created audio clip: " + Functions.getFileNameWithoutExtension(audioClipName));
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new Functions.ExceptionHandler("AudioClip could not be created", ex);
        }

        this.audioClipName = audioClipName;
        this.audioClipPath = audioClipPath;
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
