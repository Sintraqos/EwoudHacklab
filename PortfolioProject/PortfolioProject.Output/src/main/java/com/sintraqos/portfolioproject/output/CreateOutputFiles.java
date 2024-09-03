package com.sintraqos.portfolioproject.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.statics.Enums;
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

        Functions.createDirectory(ResourcePaths.getResourceFilepathDirectory());

        //region Audio Paths

        // Planet
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_DXUN), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_DXUN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_ONDERON), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_ONDERON);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_TELOS), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_TELOS);

        // Ship
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER);

        // Other
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS);

        // Battle
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_BATTLE_PREFIX), ResourcePaths.getSoundtrackAudioPath(), ResourcePaths.OST_BATTLE_PREFIX);

        //endregion

        //region Image Paths

        // Portraits
        paths.createPathFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_MALE_PREFIX);
        paths.createPathFile(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY), ResourcePaths.PORTRAIT_FEMALE_PREFIX);

        //endregion

        //region Dialogue Audio Files

        //TODO: Read out the dialogue files, get all the needed DialogueID's and find the needed audioFiles from there

        //endregion

        //endregion
    }

}
