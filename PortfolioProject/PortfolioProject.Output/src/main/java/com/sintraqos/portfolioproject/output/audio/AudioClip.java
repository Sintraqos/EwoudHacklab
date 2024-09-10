package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Functions;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AudioClip {
    String audioClipName;
    String audioClipPath;
    AudioInputStream audioInputStream;
    File audioFile;

    public String getAudioClipName() {
        return audioClipName;
    }

    public String getAudioClipPath() {
        return audioClipPath;
    }

    public AudioClip(String audioClipName, String audioClipPath) {

        if (Functions.class.getResource(audioClipPath) == null) {
            return;
        }
        this.audioClipName = audioClipName;
        this.audioClipPath = audioClipPath;
        Console.writeLine("Created audio clip: " + Functions.getFileNameWithoutExtension(audioClipName));

        reset();
    }

    public void reset() {
        try {
            // Resetting the audioStream using audioInputStream.reset() doesn't work properly, for now just reimport the audio from the original path
            audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(Functions.class.getResource(audioClipPath)));
        } catch (UnsupportedAudioFileException | IOException ex) {
            throw new Functions.ExceptionHandler("AudioClip could not be created", ex);
        }
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
