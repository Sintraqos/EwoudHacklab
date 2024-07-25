package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

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
        audioInputStream = Functions.getAudioInputStream(audioClipPath);
        this.audioClipName = audioClipName;
        this.audioClipPath = audioClipPath;

        Console.writeLine("Created new AudioClip: " + Functions.getFileNameWithoutExtension(audioClipName));
    }

    @Override
    public String toString() {
        return audioClipName;
    }
}
