package com.sintraqos.portfolioproject.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CreateOutputFiles {
    // Get instance
    static CreateOutputFiles instance;

    public static CreateOutputFiles getInstance() {
        if (instance == null) {
            instance = new CreateOutputFiles();

            instance.setup();
        }

        return instance;
    }

    public void setup() {

        //region File Paths

        ResourcePaths.ResourcePathsFile paths = new ResourcePaths.ResourcePathsFile();

        createDirectory(ResourcePaths.getResourceFilepathDirectory());

        //region Audio Paths

        // Reset paths
        paths.clear();

        // Planet
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_DXUN, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_DXUN));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_ONDERON, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_ONDERON));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_TELOS, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_TELOS));

        // Ship
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER));
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER));

        // Other
        paths.put(ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS));

        // Battle
        paths.put(ResourcePaths.OST_BATTLE_PREFIX, createFilePaths(ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_BATTLE_PREFIX));

        // Create file
        createFilePathFile(ResourcePaths.AUDIO_DIRECTORY, paths);

        //endregion

        //region Image Paths

        // Reset paths
        paths.clear();

        // Portraits
        paths.put(ResourcePaths.PORTRAIT_MALE_PREFIX, createFilePaths(ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_MALE_PREFIX));
        paths.put(ResourcePaths.PORTRAIT_FEMALE_PREFIX, createFilePaths(ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_FEMALE_PREFIX));

        // Create file
        createFilePathFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, paths);

        // Cleanup
        paths.clear();

        //endregion

        //region Dialogue Audio Files

        //TODO: Read out the dialogue files, get all the needed DialogueID's and find the needed audioFiles from there

        //endregion

        //endregion
    }

    void createDirectory(String directoryPath) {
        if (!new File(directoryPath).mkdirs() && !new File(directoryPath).exists()) {
            throw new Functions.ExceptionHandler("Failed to create new directory: " + directoryPath);
        }
    }

    void createFilePathFile(String fileName, ResourcePaths.ResourcePathsFile paths) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Writer writer = new FileWriter(ResourcePaths.getDataPath(ResourcePaths.getResourceFilepathDirectory(), fileName))) {
            gson.toJson(paths, writer);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to create new path file", ex);
        }
    }

    public static List<String> createFilePaths(String filePath, String filePrefix) {
        List<String> fileNames = new ArrayList<>();
        try (InputStream in = Functions.class.getResourceAsStream(filePath)) {
            assert in != null;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
                String resource;

                while ((resource = br.readLine()) != null) {
                    // Check if the current file contains the needed prefix
                    if (resource.contains(filePrefix)) {
                        fileNames.add(resource);
                    }
                }
            }
        } catch (IOException e) {
            throw new Functions.ExceptionHandler("Error reading files with prefix: " + filePrefix, e);
        }

        return fileNames;
    }
}
