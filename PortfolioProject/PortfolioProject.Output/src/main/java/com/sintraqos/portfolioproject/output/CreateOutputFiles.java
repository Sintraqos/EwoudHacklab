package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;

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
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_DXUN), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_DXUN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_ONDERON), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_ONDERON);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_TELOS), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_TELOS);

        // Ship
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER);

        // Other
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS);

        // Battle
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.OST_BATTLE_PREFIX), ResourcePaths.SOUND_TRACK_DIRECTORY, ResourcePaths.OST_BATTLE_PREFIX);

        //endregion

        //region Image Paths

        // Portraits
        paths.createPathFile(ResourcePaths.PORTRAIT_MALE_PREFIX, ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, ResourcePaths.PORTRAIT_MALE_PREFIX);
        paths.createPathFile(ResourcePaths.PORTRAIT_FEMALE_PREFIX, ResourcePaths.PORTRAIT_PLAYER_DIRECTORY, ResourcePaths.PORTRAIT_FEMALE_PREFIX);


        // Load Screens
        // Planet
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_DANTOOINE), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_DANTOOINE);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_DXUN), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_DXUN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_KORRIBAN), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_KORRIBAN);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_MALACHOR_V), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_MALACHOR_V);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_NAR_SHADDAA), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_NAR_SHADDAA);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_ONDERON), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_ONDERON);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_TELOS), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_TELOS);

        // Ship
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_EBON_HAWK), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_EBON_HAWK);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_HARBINGER), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_HARBINGER);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_RAVAGER), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_RAVAGER);

        // Other
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.LOADSCREEN_PREFIX_PERAGUS), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_PERAGUS);
        paths.createPathFile(Enums.getCurrentLocationName(ResourcePaths.DIRECTORY_SHARED), ResourcePaths.LOADSCREEN_DIRECTORY, ResourcePaths.LOADSCREEN_PREFIX_MAIN_MENU);

        //endregion

        //region Dialogue Audio Files

        //TODO: Read out the dialogue files, get all the needed DialogueID's and find the needed audioFiles from there

        //endregion

        //endregion
    }
}
