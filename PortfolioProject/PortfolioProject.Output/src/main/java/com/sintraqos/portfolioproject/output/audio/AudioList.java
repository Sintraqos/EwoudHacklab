package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;

public class AudioList {
    public static final Map<String, List<AudioClip>> audioClips = new HashMap<>();

    AudioList(){}

    public void createAudioClips(String audioPath, String audioPrefix) {
        try {
            List<File> files = Files.walk(Paths.get(getClass().getClassLoader().getResource(audioPath.replaceFirst("/", "").substring(0, audioPath.length() - 2)).toURI()))
                    .filter(Files::isRegularFile)
                    .map(x -> x.toFile())
                    .collect(Collectors.toList());

            audioClips.put(audioPrefix, new ArrayList<>());
            assignAudioFile(files, audioPrefix);

            // Dispose of the file list
            files.clear();

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    void assignAudioFile(List<File> files, String audioPrefix) {
        Console.StringTitleOutput("New Audio Prefix: " + audioPrefix);
        for (File file : files) {
            // Check if the audio file starts with the needed prefix
            if (file.getName().contains(audioPrefix)) {
                audioClips.get(audioPrefix).add(new AudioClip(file.getName(), ResourcePaths.AUDIO_PATH + ResourcePaths.SOUND_TRACK_PATH + file.getName()));
            }
        }

        // Dispose of the file list
        files.clear();
    }

    public AudioClip getRandomAudioClip(String audioPrefix) {
        return audioClips.get(audioPrefix).get( new Random().nextInt(audioClips.get(audioPrefix).size()));
    }
}
