package com.sintraqos.portfolioproject.statics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourcePaths {

    private ResourcePaths() {
        throw new IllegalStateException("Utility class");
    }

    // Extensions
    public static final String EXTENSION_FONT = ".ttf";
    public static final String EXTENSION_IMAGE = ".png";
    public static final String EXTENSION_AUDIO = ".wav";
    public static final String EXTENSION_DIALOGUE = ".json";

    // Font
    public static final String FONT_PATH = "fonts";
    public static final String FONT_FILE_NAME = "Old_Republic";

    //region Image

    // Paths
    public static final String PATH_SEPERATOR = "/";
    public static final String IMAGE_PATH = "images";
    public static final String MAIN_MENU_PATH = "mainMenu";
    public static final String CHARACTER_SCREEN_PATH = "characterScreen";
    public static final String ITEM_IMAGE_PATH = "itemIcon";
    public static final String UI_ELEMENT_PATH = "uiElements";

    // Image Files
    public static final String TITLE_SCREEN_LOGO = "SWKoToRII_Logo";
    public static final String CURSOR_BASE_IMAGE = "UI_CursorBase";
    public static final String BUTTON_BASE_IMAGE = "UI_ButtonBase";
    public static final String BUTTON_CLICK_IMAGE = "UI_ButtonClick";
    public static final String BUTTON_HOVER_IMAGE = "UI_ButtonHover";
    public static final String LABEL_IMAGE = "UI_Label";
    public static final String GUI_BACKGROUND = "UI_Background";

    //region Portrait Frames

    public static final String PORTRAIT_PATH = "portrait";

    //region ---- Companion

    public static final String PORTRAIT_COMPANION_PATH = "companion";

    public static final String PORTRAIT_3CFD_PATH = "comp_3CFD";               // 3CFD
    public static final String PORTRAIT_ATRIS_PATH = "comp_Atris";             // Atris
    public static final String PORTRAIT_ATTON_PATH = "comp_Atton";             // Atton
    public static final String PORTRAIT_B4D4_PATH = "comp_B4D4";              // B4D4
    public static final String PORTRAIT_BAO_DUR_PATH = "comp_BaoDur";         // Bao Dur
    public static final String PORTRAIT_DISCIPLE_PATH = "comp_Disciple";     // Disciple
    public static final String PORTRAIT_G0T0_PATH = "comp_G0T0";              // G0T0
    public static final String PORTRAIT_HAND_MAIDEN_PATH = "comp_HandM";      // Hand Maiden
    public static final String PORTRAIT_HANHARR_PATH = "comp_Hanharr";       // Hanharr
    public static final String PORTRAIT_HK47_PATH = "comp_HK47";              // HK47
    public static final String PORTRAIT_HK50_PATH = "comp_HK50";              // HK50
    public static final String PORTRAIT_HK51_PATH = "comp_HK51";             // HK51
    public static final String PORTRAIT_KREIA_PATH = "comp_Kreia";          // Kreia
    public static final String PORTRAIT_MANDALORE_PATH = "comp_Mandalore";  // Mandalore
    public static final String PORTRAIT_MIRA_PATH = "comp_Mira";             // Mira
    public static final String PORTRAIT_REMOTE_PATH = "comp_Remote";          // Remote
    public static final String PORTRAIT_T3M4_PATH = "comp_T3M4";             // T3M4
    public static final String PORTRAIT_VISAS_MARR_PATH = "comp_Visas";      // Visas Marr

    protected static final HashMap<String, List<String>> PORTRAIT_COMPANIONS = new HashMap<>();

    static {
        PORTRAIT_COMPANIONS.put(PORTRAIT_3CFD_PATH, List.of(PORTRAIT_3CFD_PATH, PORTRAIT_3CFD_PATH, PORTRAIT_3CFD_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_ATRIS_PATH, List.of(PORTRAIT_ATRIS_PATH, PORTRAIT_ATRIS_PATH, PORTRAIT_ATRIS_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_ATTON_PATH, List.of(PORTRAIT_ATTON_PATH + "_1", PORTRAIT_ATTON_PATH + "_2", PORTRAIT_ATTON_PATH + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_B4D4_PATH, List.of(PORTRAIT_B4D4_PATH, PORTRAIT_B4D4_PATH, PORTRAIT_B4D4_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_BAO_DUR_PATH, List.of(PORTRAIT_BAO_DUR_PATH + "_1", PORTRAIT_BAO_DUR_PATH + "_2", PORTRAIT_BAO_DUR_PATH + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_DISCIPLE_PATH, List.of(PORTRAIT_DISCIPLE_PATH + "_1", PORTRAIT_DISCIPLE_PATH + "_2", PORTRAIT_DISCIPLE_PATH + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_G0T0_PATH, List.of(PORTRAIT_G0T0_PATH, PORTRAIT_G0T0_PATH, PORTRAIT_G0T0_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HAND_MAIDEN_PATH, List.of(PORTRAIT_HAND_MAIDEN_PATH + "_1", PORTRAIT_HAND_MAIDEN_PATH + "_2", PORTRAIT_HAND_MAIDEN_PATH + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HANHARR_PATH, List.of(PORTRAIT_HANHARR_PATH, PORTRAIT_HANHARR_PATH, PORTRAIT_HANHARR_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK47_PATH, List.of(PORTRAIT_HK47_PATH, PORTRAIT_HK47_PATH, PORTRAIT_HK47_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK50_PATH, List.of(PORTRAIT_HK50_PATH, PORTRAIT_HK50_PATH, PORTRAIT_HK50_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_HK51_PATH, List.of(PORTRAIT_HK51_PATH, PORTRAIT_HK51_PATH, PORTRAIT_HK51_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_KREIA_PATH, List.of(PORTRAIT_KREIA_PATH, PORTRAIT_KREIA_PATH, PORTRAIT_KREIA_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_MANDALORE_PATH, List.of(PORTRAIT_MANDALORE_PATH, PORTRAIT_MANDALORE_PATH, PORTRAIT_MANDALORE_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_MIRA_PATH, List.of(PORTRAIT_MIRA_PATH + "_1", PORTRAIT_MIRA_PATH + "_2", PORTRAIT_MIRA_PATH + "_3"));
        PORTRAIT_COMPANIONS.put(PORTRAIT_REMOTE_PATH, List.of(PORTRAIT_REMOTE_PATH, PORTRAIT_REMOTE_PATH, PORTRAIT_REMOTE_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_T3M4_PATH, List.of(PORTRAIT_T3M4_PATH, PORTRAIT_T3M4_PATH, PORTRAIT_T3M4_PATH));
        PORTRAIT_COMPANIONS.put(PORTRAIT_VISAS_MARR_PATH, List.of(PORTRAIT_VISAS_MARR_PATH + "_1", PORTRAIT_VISAS_MARR_PATH + "_2", PORTRAIT_VISAS_MARR_PATH + "_3"));
    }

    public static Map<String, List<String>> getPortraitCompanions() {
        return PORTRAIT_COMPANIONS;
    }

    //endregion

    //region ---- Player

    public static final String PORTRAIT_PLAYER_PATH = "player";

    public static final String PORTRAIT_MALE_PATH = "play_Male_";
    public static final String PORTRAIT_FEMALE_PATH = "play_Fem_";

    //endregion

    //endregion

    //endregion

    //region Audio

    // paths
    public static final String AUDIO_PATH = "/audio/";
    public static final String SOUND_TRACK_PATH = "ost/";
    public static final String SOUND_EFFECT_PATH = "sfx/";
    public static final String GUI_SOUND_EFFECT_PATH = "gui/";

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

    public static final String DIALOGUE_PATH = "dialogue";

    //region ---- Peragus

    public static final String DIALOGUE_PERAGUS_PATH = "per";
    public static final String KREIA_001 = "Kreia001";
    public static final String KREIA_002 = "Kreia002";

    //endregion

    //endregion
}
