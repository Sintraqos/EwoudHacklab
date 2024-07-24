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

        audioClips.put(audioPrefix, new ArrayList<>());
        List<String> fileNames = new ArrayList<>();

        try (
                InputStream in = getClass().getResourceAsStream(audioPath)) {
            assert in != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String resource;

                while ((resource = br.readLine()) != null) {
                    if (resource.contains(audioPrefix))
                        fileNames.add(resource);
                }
            }
        } catch (IOException e) {
            throw new Functions.ExceptionHandler("Error reading audio clips from " + audioPath, e);
        }

        Console.StringTitleOutput("New Audio Prefix: " + audioPrefix);
        for (String fileName : fileNames) {
            // Check if the audio file starts with the needed prefix
            audioClips.get(audioPrefix).add(new AudioClip(fileName, ResourcePaths.AUDIO_PATH + ResourcePaths.PATH_SEPERATOR + ResourcePaths.SOUND_TRACK_PATH + ResourcePaths.PATH_SEPERATOR + fileName));
        }

        // Dispose of the file list
        fileNames.clear();
    }

    public AudioClip getRandomAudioClip(String audioPrefix) {
        return audioClips.get(audioPrefix).get(random.nextInt(audioClips.get(audioPrefix).size()));
    }
}
