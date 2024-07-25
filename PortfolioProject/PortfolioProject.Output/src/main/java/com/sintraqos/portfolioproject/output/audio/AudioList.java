package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;

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

        for (String fileName : fileNames) {
            audioClips.get(audioPrefix).add(new AudioClip(fileName, ResourcePaths.PATH_SEPERATOR + ResourcePaths.AUDIO_PATH + ResourcePaths.PATH_SEPERATOR + ResourcePaths.SOUND_TRACK_PATH + ResourcePaths.PATH_SEPERATOR + Functions.getFileNameWithoutExtension(fileName)));
        }

        // Dispose of the file list
        fileNames.clear();
    }

    public AudioClip getRandomAudioClip(String audioPrefix) {
        return audioClips.get(audioPrefix).get(random.nextInt(audioClips.get(audioPrefix).size()));
    }
}
