package com.sintraqos.portfolioproject.output.audio;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.output.OutputManager;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

public class AudioList {
    protected static final Map<String, List<AudioClip>> audioClips = new ConcurrentHashMap<>();

    public static Map<String, List<AudioClip>> getAudioClips() {
        return audioClips;
    }

    Random random;


    AudioList() {
        random = new Random();
    }

    public void createAudioClips(String audioPath, String audioPrefix) {
        Console.writeHeader("New audio prefix: " + audioPrefix);

        // Create new audioList
        audioClips.put(audioPrefix, new ArrayList<>());

        ResourcePaths.FilePaths fileNames = OutputManager.getInstance().getAudioPathsFile().getFilePaths(audioPrefix);

        IntStream.range(0, fileNames.resourcePaths().size()).parallel().forEach(i ->
                audioClips.get(audioPrefix).add(
                        new AudioClip(
                                fileNames.resourcePaths().get(i),
                                ResourcePaths.getAudioPath(ResourcePaths.SOUND_TRACK_DIRECTORY, Functions.getFileNameWithoutExtension(fileNames.resourcePaths().get(i)))
                        )));
    }

    public AudioClip getRandomAudioClip(String audioPrefix) {
        return audioClips.get(audioPrefix).get(random.nextInt(audioClips.get(audioPrefix).size()));
    }
}
