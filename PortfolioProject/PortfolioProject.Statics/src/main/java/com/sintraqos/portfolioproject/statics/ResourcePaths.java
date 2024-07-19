package com.sintraqos.portfolioproject.statics;

import java.util.ArrayList;

public class ResourcePaths {
    // Extensions
    public static final String EXTENSION_FONT = ".ttf";
    public static final String EXTENSION_IMAGE = ".png";
    public static final String EXTENSION_AUDIO = ".wav";

    // Font
    public static final String FONT_PATH = "fonts/";
    public static final String FONT_FILE_NAME = "Old_Republic";

    //region Image

    // Paths
    public static final String IMAGE_PATH = "images/";
    public static final String MAIN_MENU_PATH = "mainMenu/";
    public static final String CHARACTER_SCREEN_PATH = "characterScreen/";
    public static final String ITEM_IMAGE_PATH = "itemIcon/";
    public static final String UI_ELEMENT_PATH = "uiElements/";

    // Image Files
    public static final String CURSOR_BASE_IMAGE = "UI_CursorBase";
    public static final String BUTTON_BASE_IMAGE = "UI_ButtonBase";
    public static final String BUTTON_CLICK_IMAGE = "UI_ButtonClick";
    public static final String BUTTON_HOVER_IMAGE = "UI_ButtonHover";
    public static final String LABEL_IMAGE = "UI_Label";
    public static final String TITLE_SCREEN_LOGO = "SWKoToRII_Logo";
    public static final String GUI_BACKGROUND = "UI_Background";

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
}
