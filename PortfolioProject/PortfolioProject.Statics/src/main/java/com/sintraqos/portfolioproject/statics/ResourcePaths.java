package com.sintraqos.portfolioproject.statics;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    //region Image

    // Directories
    public static final String IMAGE_DIRECTORY = "images";
    public static final String MAIN_MENU_DIRECTORY = "mainMenu";
    public static final String ITEM_IMAGE_DIRECTORY = "itemIcon";
    public static final String UI_ELEMENT_DIRECTORY = "uiElements";

    // Image Files
    public static final String TITLE_SCREEN_LOGO = "SWKoToRII_Logo";
    public static final String CURSOR_BASE = "CursorBase";
    public static final String BUTTON_BASE = "ButtonBase";
    public static final String BUTTON_CLICK = "ButtonClick";
    public static final String BUTTON_HOVER = "ButtonHover";
    public static final String LABEL_IMAGE = "Label";
    public static final String GUI_BACKGROUND = "GUI_Background";
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

    //endregion

    //region Audio

    // paths
    public static final String RESOURCE_DIRECTORY = "resources";
    public static final String AUDIO_DIRECTORY = "audio";
    public static final String SOUND_TRACK_DIRECTORY = "ost";
    public static final String SOUND_EFFECT_DIRECTORY = "sfx";
    public static final String GUI_SOUND_EFFECT_DIRECTORY = "gui";
    public static final String FILEPATH_DIRECTORY = "filePaths";

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

    public static final String DIALOGUE_PERAGUS_DIRECTORY = "per";
    public static final String KREIA_001 = "Kreia001";
    public static final String KREIA_002 = "Kreia002";

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

    public static String getUIImagePath(String imageName){
        return IMAGE_DIRECTORY + PATH_SEPARATOR + UI_ELEMENT_DIRECTORY + PATH_SEPARATOR + imageName + EXTENSION_IMAGE;
    }

    //endregion

    //region Audio Path

    // General
    static String getAudioDirectoryPath(String locationDirectory) {
        return PATH_SEPARATOR + AUDIO_DIRECTORY + PATH_SEPARATOR + locationDirectory + PATH_SEPARATOR;
    }

    static String getAudioDirectoryPath(String locationDirectory, String locationSubDirectory) {
        return getAudioDirectoryPath(locationDirectory) + locationSubDirectory + PATH_SEPARATOR ;
    }

    public static String getAudioFile(String locationDirectory, String locationSubDirectory, String fileName) {
        return getAudioDirectoryPath(locationDirectory, locationSubDirectory) + fileName;
    }

    // Soundtrack
    public static String getSoundtrackAudioPath(){
        return getAudioDirectoryPath(SOUND_TRACK_DIRECTORY);
    }

    public static String getSoundtrackAudioFile(String fileName) {
        return getSoundtrackAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // SFX
    public static String getSoundEffectAudioPath(){
        return getAudioDirectoryPath(SOUND_EFFECT_DIRECTORY);
    }

    public static String getSoundEffectAudioFile(String fileName) {
        return getSoundEffectAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // GUI
    public static String getGUISoundEffectAudioPath(){
        return getSoundEffectAudioPath() + GUI_SOUND_EFFECT_DIRECTORY + PATH_SEPARATOR;
    }

    public static String getGUISoundEffectAudioFile(String fileName){
        return getGUISoundEffectAudioPath() + fileName + EXTENSION_AUDIO;
    }

    // Dialogue
    public static String getDialogueAudioFile(String locationDirectory, String fileName) {
        return getAudioDirectoryPath(locationDirectory) + fileName + EXTENSION_AUDIO;
    }

    //endregion

    // Font
    public static String getFontPath() {
        return PATH_SEPARATOR + FONT_DIRECTORY + PATH_SEPARATOR + FONT_FILE_NAME + EXTENSION_FONT;
    }

    // File Path
    public static String getResourceFilepathDirectory(){
        return RESOURCE_DIRECTORY + PATH_SEPARATOR + FILEPATH_DIRECTORY;
    }

    public static String getResourceFilepathDialogueDirectory(){
        return getResourceFilepathDirectory() + ResourcePaths.PATH_SEPARATOR + ResourcePaths.DIALOGUE_DIRECTORY + ResourcePaths.PATH_SEPARATOR;
    }

    //endregion

    //region Classes

    public static class ResourcePathsFile implements Serializable {
        ConcurrentHashMap<String, List<String>> paths = new ConcurrentHashMap<>();

        public List<String> getFilePaths(String path) {
            return paths.get(path);
        }

        public ResourcePathsFile() {
            paths = new ConcurrentHashMap<>();
        }

        public ResourcePathsFile(Map<String, List<String>> paths) {
            this.paths.putAll(paths);
        }

        public void put(String index, List<String> pathList) {
            paths.put(index, pathList);
        }

        public void clear() {
            paths.clear();
        }
    }

    //endregion
}
