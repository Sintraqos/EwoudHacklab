package com.sintraqos.portfolioproject.statics;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ResourcePaths {

    private ResourcePaths() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PATH_SEPARATOR = "/";

    // Extensions
    public static final String EXTENSION_FONT = ".ttf";
    public static final String EXTENSION_IMAGE = ".png";
    public static final String EXTENSION_AUDIO = ".wav";
    public static final String EXTENSION_DATAFILE = ".json";

    // Font
    public static final String FONT_DIRECTORY = "fonts";
    public static final String FONT_FILE_NAME = "Old_Republic";

    // Directories
    public static final String IMAGE_DIRECTORY = "images";
    public static final String MAIN_MENU_DIRECTORY = "mainMenu";
    public static final String ITEM_IMAGE_DIRECTORY = "itemIcon";
    public static final String UI_ELEMENT_DIRECTORY = "uiElements";

    // Locations
    public static final String DIRECTORY_DANTOOINE = "dan";
    public static final String DIRECTORY_DXUN = "dxn";
    public static final String DIRECTORY_KORRIBAN = "kor";
    public static final String DIRECTORY_MALACHOR_V = "mal";
    public static final String DIRECTORY_NAR_SHADDAA = "nar";
    public static final String DIRECTORY_ONDERON = "ond";
    public static final String DIRECTORY_TELOS = "tel";
    public static final String DIRECTORY_EBON_HAWK = "ebo";
    public static final String DIRECTORY_HARBINGER = "har";
    public static final String DIRECTORY_RAVAGER = "rav";
    public static final String DIRECTORY_PERAGUS = "per";
    public static final String DIRECTORY_SHARED = "shared";

    //region Image

    // Image Files
    public static final String TITLE_SCREEN_LOGO = "SWKoToRII_Logo";
    public static final String CURSOR_BASE = "CursorBase";
    public static final String BUTTON_BASE = "ButtonBase";
    public static final String BUTTON_CLICK = "ButtonClick";
    public static final String BUTTON_HOVER = "ButtonHover";
    public static final String LABEL_IMAGE = "Label";
    public static final String GUI_BACKGROUND = "GUI_Background";
    public static final String GUI_LOADSCREEN = "GUI_Background";
    public static final String GUI_CLASS_ICON_CONSULAR = "ConsularIcon";
    public static final String GUI_CLASS_ICON_GUARDIAN = "GuardianIcon";
    public static final String GUI_CLASS_ICON_SENTINEL = "SentinelIcon";

    //region Portrait Frames

    public static final String PORTRAIT_DIRECTORY = "portrait";

    //region ---- Companion

    public static final String PORTRAIT_COMPANION_DIRECTORY = "companion";

    public static final String PORTRAIT_3CFD = "comp_3CFD";               // 3CFD
    public static final String PORTRAIT_ATRIS = "comp_Atris";             // Atris
    public static final String PORTRAIT_ATTON = "comp_Atton";             // Atton
    public static final String PORTRAIT_B4D4 = "comp_B4D4";               // B4D4
    public static final String PORTRAIT_BAO_DUR = "comp_BaoDur";          // Bao Dur
    public static final String PORTRAIT_DISCIPLE = "comp_Disciple";       // Disciple
    public static final String PORTRAIT_G0T0 = "comp_G0T0";               // G0T0
    public static final String PORTRAIT_HAND_MAIDEN = "comp_HandM";       // Hand Maiden
    public static final String PORTRAIT_HANHARR = "comp_Hanharr";         // Hanharr
    public static final String PORTRAIT_HK47 = "comp_HK47";               // HK47
    public static final String PORTRAIT_HK50 = "comp_HK50";               // HK50
    public static final String PORTRAIT_HK51 = "comp_HK51";               // HK51
    public static final String PORTRAIT_KREIA = "comp_Kreia";             // Kreia
    public static final String PORTRAIT_MANDALORE = "comp_Mandalore";     // Mandalore
    public static final String PORTRAIT_MIRA = "comp_Mira";               // Mira
    public static final String PORTRAIT_REMOTE = "comp_Remote";           // Remote
    public static final String PORTRAIT_T3M4 = "comp_T3M4";               // T3M4
    public static final String PORTRAIT_VISAS_MARR = "comp_Visas";        // Visas Marr

    protected static final HashMap<String, List<String>> PORTRAIT_COMPANIONS = new HashMap<>();

    static {
        PORTRAIT_COMPANIONS.put(PORTRAIT_3CFD, List.of(PORTRAIT_3CFD, PORTRAIT_3CFD, PORTRAIT_3CFD));
        PORTRAIT_COMPANIONS.put(PORTRAIT_ATRIS, List.of(PORTRAIT_ATRIS, PORTRAIT_ATRIS, PORTRAIT_ATRIS));
        PORTRAIT_COMPANIONS.put(PORTRAIT_ATTON, List.of(PORTRAIT_ATTON + "_1", PORTRAIT_ATTON + "_2", PORTRAIT_ATTON + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_B4D4, List.of(PORTRAIT_B4D4, PORTRAIT_B4D4, PORTRAIT_B4D4));
        PORTRAIT_COMPANIONS.put(PORTRAIT_BAO_DUR, List.of(PORTRAIT_BAO_DUR + "_1", PORTRAIT_BAO_DUR + "_2", PORTRAIT_BAO_DUR + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_DISCIPLE, List.of(PORTRAIT_DISCIPLE + "_1", PORTRAIT_DISCIPLE + "_2", PORTRAIT_DISCIPLE + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_G0T0, List.of(PORTRAIT_G0T0, PORTRAIT_G0T0, PORTRAIT_G0T0));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HAND_MAIDEN, List.of(PORTRAIT_HAND_MAIDEN + "_1", PORTRAIT_HAND_MAIDEN + "_2", PORTRAIT_HAND_MAIDEN + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HANHARR, List.of(PORTRAIT_HANHARR, PORTRAIT_HANHARR, PORTRAIT_HANHARR));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK47, List.of(PORTRAIT_HK47, PORTRAIT_HK47, PORTRAIT_HK47));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK50, List.of(PORTRAIT_HK50, PORTRAIT_HK50, PORTRAIT_HK50));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK51, List.of(PORTRAIT_HK51, PORTRAIT_HK51, PORTRAIT_HK51));
        PORTRAIT_COMPANIONS.put(PORTRAIT_KREIA, List.of(PORTRAIT_KREIA, PORTRAIT_KREIA, PORTRAIT_KREIA));
        PORTRAIT_COMPANIONS.put(PORTRAIT_MANDALORE, List.of(PORTRAIT_MANDALORE, PORTRAIT_MANDALORE, PORTRAIT_MANDALORE));
        PORTRAIT_COMPANIONS.put(PORTRAIT_MIRA, List.of(PORTRAIT_MIRA + "_1", PORTRAIT_MIRA + "_2", PORTRAIT_MIRA + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_REMOTE, List.of(PORTRAIT_REMOTE, PORTRAIT_REMOTE, PORTRAIT_REMOTE));
        PORTRAIT_COMPANIONS.put(PORTRAIT_T3M4, List.of(PORTRAIT_T3M4, PORTRAIT_T3M4, PORTRAIT_T3M4));
        PORTRAIT_COMPANIONS.put(PORTRAIT_VISAS_MARR, List.of(PORTRAIT_VISAS_MARR + "_1", PORTRAIT_VISAS_MARR + "_2", PORTRAIT_VISAS_MARR + "_3"));
    }

    public static Map<String, List<String>> getPortraitCompanions() {
        return PORTRAIT_COMPANIONS;
    }

    //endregion

    //region ---- Player

    public static final String PORTRAIT_PLAYER_DIRECTORY = "player";

    public static final String PORTRAIT_MALE_PREFIX = "play_Male_";
    public static final String PORTRAIT_FEMALE_PREFIX = "play_Fem_";

    public static final String PORTRAIT_DEFAULT_FEMALE = "DefaultFemalePortrait";
    public static final String PORTRAIT_DEFAULT_MALE = "DefaultMalePortrait";

    //endregion

    //endregion

    //region Load Screen

    public static final String LOADSCREEN_DIRECTORY = "loadScreens";

    // Planet
    public static final String LOADSCREEN_PREFIX_DANTOOINE = "load_dan";
    public static final String LOADSCREEN_PREFIX_DXUN = "load_dxn";
    public static final String LOADSCREEN_PREFIX_KORRIBAN = "load_kor";
    public static final String LOADSCREEN_PREFIX_MALACHOR_V = "load_mal";
    public static final String LOADSCREEN_PREFIX_NAR_SHADDAA = "load_nar";
    public static final String LOADSCREEN_PREFIX_ONDERON = "load_ond";
    public static final String LOADSCREEN_PREFIX_TELOS = "load_tel";

    // Ship
    public static final String LOADSCREEN_PREFIX_EBON_HAWK = "load_ebo";
    public static final String LOADSCREEN_PREFIX_HARBINGER = "load_har";
    public static final String LOADSCREEN_PREFIX_RAVAGER = "load_rav";

    // Other
    public static final String LOADSCREEN_PREFIX_PERAGUS = "load_per";
    public static final String LOADSCREEN_PREFIX_MAIN_MENU = "load_mai";

    //endregion

    //endregion

    //region Audio

    // paths
    public static final String RESOURCE_DIRECTORY = "resources";
    public static final String AUDIO_DIRECTORY = "audio";
    public static final String SOUND_TRACK_DIRECTORY = "ost";
    public static final String SOUND_EFFECT_DIRECTORY = "sfx";
    public static final String GUI_SOUND_EFFECT_DIRECTORY = "gui";
    public static final String FILEPATH_DIRECTORY = "resourcePaths";

    //region ---- GUI

    public static final String GUI_CLICK = "GUI_click";                 // Button Click
    public static final String GUI_CLOSE = "GUI_close";                 // Close GUI
    public static final String GUI_PROMPT = "GUI_prompt";               // Play When Opening Menu

    public static final String GUI_INVENTORY_ADD = "GUI_invadd";        // Inventory Add Item
    public static final String GUI_INVENTORY_REMOVE = "GUI_invdrop";    // Inventory Remove Item
    public static final String GUI_INVENTORY_SELECT = "GUI_invselect";  // Inventory Select Item

    //endregion

    //region ---- OST

    // The music is organised with the following prefixes:

    // The "OST" prefix is for defining that it is part of the OST (Original Sound Track)

    // OST_amb_ABCxxx – These are ambient tracks that play while you explore. ‘xx’ is an arbitrary number which is tied to different maps within areas, the ‘ABC’ is abbreviations of locations.
    // For example, ‘ebo’ is Ebon Hawk; ‘per’ is Peragus; ‘dan’ is Dantooine etc.

    // OST_bat_xxx - These are very short sequences that allow the respective battle track to end naturally when you exit combat.
    // For example “OST_bat_000” will play when you fight the droids on Peragus, and ‘OST_bat_final” plays when you fight Kreia on Malachor etc.

    // OST_mus_a_xxx – These are event specific tracks. The ‘xx’ is an arbitrary number which is tied to events. For example, ‘OST_mus_a_261’ plays when you crash into the Polar Area of Telos.
    // OST_mus_s_xxx – These are short (‘s’) tracks that reoccur occasionally.
    // The ‘xxx’ is the instance it’s tied to. Each of these are pretty self-explanatory, for example ‘OST_mus_s_darkside’ plays when you get darkside points. ‘OST_mus_s_romance’ plays during your character romance etc.

    // Menu's
    public static final String OST_MAIN_MENU = "OST_mus_sion";                  // Play this on main menu
    public static final String OST_CHARACTER_CREATE = "OST_mus_main";           // Play this on character creation

    //region Ambient

    // Planet
    public static final String OST_AMBIENT_PREFIX_DANTOOINE = "OST_amb_dan";        // All ambient sounds from the planet Dantooine
    public static final String OST_AMBIENT_PREFIX_DXUN = "OST_amb_dxn";             // All ambient sounds from the planet Dxun
    public static final String OST_AMBIENT_PREFIX_KORRIBAN = "OST_amb_kor";         // All ambient sounds from the planet Korriban
    public static final String OST_AMBIENT_PREFIX_MALACHOR_V = "OST_amb_mal";       // All ambient sounds from the planet Malachor V
    public static final String OST_AMBIENT_PREFIX_NAR_SHADDAA = "OST_amb_nar";      // All ambient sounds from the planet Nar Shaddaa
    public static final String OST_AMBIENT_PREFIX_ONDERON = "OST_amb_ond";          // All ambient sounds from the planet Onderon
    public static final String OST_AMBIENT_PREFIX_TELOS = "OST_amb_tel";            // All ambient sounds from the planet Telos

    // Ship
    public static final String OST_AMBIENT_PREFIX_EBON_HAWK = "OST_amb_ebo";        // All ambient sounds from the ship Ebon Hawk
    public static final String OST_AMBIENT_PREFIX_HARBINGER = "OST_amb_har";        // All ambient sounds from the ship Harbinger (Ship docking at Peragus mining facility)
    public static final String OST_AMBIENT_PREFIX_RAVAGER = "OST_amb_nih";          // All ambient sounds from the ship Ravager (Nihilus Capital Ship)

    // Other
    public static final String OST_AMBIENT_PREFIX_PERAGUS = "OST_amb_per";          // All ambient sounds from the Peragus mining facility

    // Battle
    public static final String OST_BATTLE_PREFIX = "OST_bat";

    // Light Side
    public static final String OST_LIGHT_SIDE = "OST_s_lightSide";              // Play this when character makes light side choices
    public static final String OST_LIGHT_SIDE_SHORT = "OST_s_lightShort";        // Play this when character makes light side choices
    // Dark Side
    public static final String OST_DARK_SIDE = "OST_s_darkSide";                // Play this when character makes dark side choices
    public static final String OST_DARK_SIDE_SHORT = "OST_s_darkShort";         // Play this when character makes dark side choices

    // Companions
    public static final String OST_ROMANCE = "OST_s_romance";                   // Play this when character makes dark side choices

    //endregion

    //endregion

    //endregion

    //region Dialogue

    public static final String DIALOGUE_DIRECTORY = "dialogue";

    //region ---- Peragus

    public static final String[] PERAGUS_KREIA_DIALOGUE = new String[]
            {
                "Kreia001",
                "Kreia002"
            };

//    public static final HashMap<Enums.currentLocation,String[]> PERAGUS_KREIA_DIALOGUE = new HashMap<>();
//    static {
//        PERAGUS_KREIA_DIALOGUE.put(Enums.currentLocation.CURRENT_LOCATION_PERAGUS, new String[]{
//                "Kreia001",
//                "Kreia002"});
//    }

    //endregion

    //endregion

    //region Get Paths

    // Data
    public static String getDataPath(String directoryPath, String fileName) {
        return directoryPath + PATH_SEPARATOR + fileName + EXTENSION_DATAFILE;
    }

    //region Image

    public static String getImagePath(String locationDirectory, String imageName) {
        return IMAGE_DIRECTORY + PATH_SEPARATOR + locationDirectory + PATH_SEPARATOR + imageName + EXTENSION_IMAGE;
    }

    public static String getImagePath(String locationDirectory, String locationSubDirectory, String imageName) {
        return IMAGE_DIRECTORY + PATH_SEPARATOR + locationDirectory + PATH_SEPARATOR + locationSubDirectory + PATH_SEPARATOR + imageName + EXTENSION_IMAGE;
    }

    public static String getPortraitImagePath(String locationDirectory) {
        return PATH_SEPARATOR + IMAGE_DIRECTORY + PATH_SEPARATOR + PORTRAIT_DIRECTORY + PATH_SEPARATOR + locationDirectory + PATH_SEPARATOR;
    }

    public static String getUIImagePath(String imageName) {
        return IMAGE_DIRECTORY + PATH_SEPARATOR + UI_ELEMENT_DIRECTORY + PATH_SEPARATOR + imageName + EXTENSION_IMAGE;
    }

    public static String getLoadScreenImagePath(){
        return PATH_SEPARATOR + IMAGE_DIRECTORY + PATH_SEPARATOR + LOADSCREEN_DIRECTORY + PATH_SEPARATOR;
    }

    //endregion

    //region Audio Path

    // General
    static String getAudioDirectoryPath(String locationDirectory) {
        return PATH_SEPARATOR + AUDIO_DIRECTORY + PATH_SEPARATOR + locationDirectory + PATH_SEPARATOR;
    }

    // Soundtrack
    public static String getSoundtrackAudioPath() {
        return getAudioDirectoryPath(SOUND_TRACK_DIRECTORY);
    }

    public static String getSoundtrackAudioFile(String fileName) {
        return getSoundtrackAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // SFX
    public static String getSoundEffectAudioPath() {
        return getAudioDirectoryPath(SOUND_EFFECT_DIRECTORY);
    }

    public static String getSoundEffectAudioFile(String fileName) {
        return getSoundEffectAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // GUI
    public static String getGUISoundEffectAudioPath() {
        return getSoundEffectAudioPath() + GUI_SOUND_EFFECT_DIRECTORY + PATH_SEPARATOR;
    }

    public static String getGUISoundEffectAudioFile(String fileName) {
        return getGUISoundEffectAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // Dialogue
    public static String getDialogueAudioFile(String locationDirectory,String dialogueID, String fileName) {
        return getAudioDirectoryPath(DIALOGUE_DIRECTORY) + locationDirectory + PATH_SEPARATOR + dialogueID + PATH_SEPARATOR + fileName + EXTENSION_AUDIO;
    }

    //endregion

    // Font
    public static String getFontPath() {
        return PATH_SEPARATOR + FONT_DIRECTORY + PATH_SEPARATOR + FONT_FILE_NAME + EXTENSION_FONT;
    }

    // File Path
    public static String getResourceFilepathDirectory() {
        return RESOURCE_DIRECTORY + PATH_SEPARATOR + FILEPATH_DIRECTORY;
    }

    public static String getDialogueDirectory(String currentLocation) {
        return PATH_SEPARATOR + DIALOGUE_DIRECTORY + PATH_SEPARATOR + currentLocation + PATH_SEPARATOR;
    }

    public static String getDialogueFile(String currentLocation, String fileName) {
        return getDialogueDirectory(currentLocation) + fileName + EXTENSION_DATAFILE;
    }

    public static String getResourceFilepathDialogueDirectory() {
        return RESOURCE_DIRECTORY + PATH_SEPARATOR + DIALOGUE_DIRECTORY + PATH_SEPARATOR;
    }

    public static String getResourceFilepathDialogueDirectory(String locationDirectory, String fileName) {
        return getResourceFilepathDialogueDirectory() + locationDirectory + PATH_SEPARATOR + fileName + EXTENSION_DATAFILE;
    }

    //endregion

    //region Resource Path File

    public static class ResourcePathsFile implements Serializable {
        ConcurrentHashMap<String, List<String>> paths;

        public ConcurrentHashMap<String, List<String>> getFilePaths() {
            return paths;
        }
        public List<String> getFilePaths(String path) {
            return paths.get(path);
        }

        public ResourcePathsFile() {
            paths = new ConcurrentHashMap<>();
        }

        public void put(String index, List<String> pathList) {
            paths.put(index, pathList);
        }

        public void put(ConcurrentHashMap<String, List<String>> paths){
            this.paths.putAll(paths);
        }

        public void createPathFile(String location, String fileType, String filePrefix) {
            ResourcePaths.ResourcePathsFile paths = new ResourcePaths.ResourcePathsFile();
            paths.put(location, createFilePaths(fileType, filePrefix));
            createPathFile(fileType, paths);
        }

        public void createPathFile(String fileType, ResourcePaths.ResourcePathsFile paths) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            if(new File(ResourcePaths.getDataPath(ResourcePaths.getResourceFilepathDirectory() , fileType)).exists()) {
                try (Reader reader = new FileReader(ResourcePaths.getDataPath(ResourcePaths.getResourceFilepathDirectory(), fileType))) {
                    ResourcePaths.ResourcePathsFile currentPaths = gson.fromJson(reader, ResourcePaths.ResourcePathsFile.class);
                    if (!currentPaths.getFilePaths().isEmpty()) {
                        paths.put(currentPaths.getFilePaths());
                    }
                } catch (IOException ex) {
                    throw new Functions.ExceptionHandler("Failed to read from current file", ex);
                }
            }

            try (Writer writer = new FileWriter(ResourcePaths.getDataPath(ResourcePaths.getResourceFilepathDirectory(), fileType))) {
                gson.toJson(paths, writer);
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to create new path file", ex);
            }
        }

        public static List<String> createFilePaths(String fileType, String filePrefix) {
            switch (fileType) {
                case ResourcePaths.SOUND_TRACK_DIRECTORY -> fileType = ResourcePaths.getSoundtrackAudioPath();
                case ResourcePaths.PORTRAIT_PLAYER_DIRECTORY -> fileType = ResourcePaths.getPortraitImagePath(ResourcePaths.PORTRAIT_PLAYER_DIRECTORY);
                case ResourcePaths.LOADSCREEN_DIRECTORY -> fileType = ResourcePaths.getLoadScreenImagePath();
                case ResourcePaths.DIALOGUE_DIRECTORY -> fileType = ResourcePaths.getDialogueDirectory(filePrefix);
            }

            List<String> fileNames = new ArrayList<>();
            try (InputStream in = Functions.class.getResourceAsStream(fileType)) {
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

        @Override
        public String toString(){
            return "Resource Paths Length: " + paths.size();
        }
    }

    public static ResourcePathsFile readPathsFile(String pathType) {
        Console.writeLine(pathType);

        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Functions.class.getResourceAsStream(PATH_SEPARATOR + getDataPath(FILEPATH_DIRECTORY, pathType))))) {
            return new Gson().fromJson(reader, ResourcePathsFile.class);
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Error reading paths file", ex);
        }
    }

    //endregion
}
