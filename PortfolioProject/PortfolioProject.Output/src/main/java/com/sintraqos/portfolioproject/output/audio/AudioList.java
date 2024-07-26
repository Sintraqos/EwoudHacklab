package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
import java.util.stream.IntStream;

public class AudioList {
    protected static final Map<String, List<AudioClip>> audioClips = new HashMap<>();

    public static Map<String, List<AudioClip>> getAudioClips() {
        return audioClips;
    }

    Random random;


    AudioList() {
        random = new Random();
    }

    public void createAudioClips(String audioPath, String audioPrefix) {
        Console.writeHeader("New audio prefix: " + audioPrefix);

        audioClips.put(audioPrefix, new ArrayList<>());
        List<String> fileNames = Functions.getFiles(audioPath, audioPrefix);

        // Since we need to process a lot of files run a parallel loop so it won't take too much time to process each file
        IntStream.range(0,fileNames.size()).parallel().forEach(i -> audioClips.get(audioPrefix).add(new AudioClip(fileNames.get(i), ResourcePaths.PATH_SEPERATOR + ResourcePaths.AUDIO_PATH + ResourcePaths.PATH_SEPERATOR + ResourcePaths.SOUND_TRACK_PATH + ResourcePaths.PATH_SEPERATOR + Functions.getFileNameWithoutExtension(fileNames.get(i)))));

        // Dispose of the file list
        fileNames.clear();
    }

    public AudioClip getRandomAudioClip(String audioPrefix) {
        return audioClips.get(audioPrefix).get(random.nextInt(audioClips.get(audioPrefix).size()));
    }
}
