package com.sintraqos.portfolioproject.statics;

public class Enums {

    //region Items

    public enum itemType{
        ITEM_TYPE_ARMOR,
        ITEM_TYPE_CONSUMABLE,
        ITEM_TYPE_MISCELLANEOUS,
        ITEM_TYPE_WEAPON,
        ITEM_TYPE_UPGRADE,
    }

    public String getItemTypeName(itemType itemUpgradeSlot) {
        return itemUpgradeSlot.name().replace("ITEM_TYPE_", "").toLowerCase();
    }

    // Upgrade Slot
    public enum itemUpgradeSlot{
        // Ranged
        ITEM_UPGRADE_SLOT_RANGED_TARGETING,
        ITEM_UPGRADE_SLOT_RANGED_POWER_PACKS,
        ITEM_UPGRADE_SLOT_RANGED_FIRING_CHAMBERS,

        // Melee
        ITEM_UPGRADE_SLOT_MELEE_EDGES,
        ITEM_UPGRADE_SLOT_MELEE_ENERGY_CELLS,
        ITEM_UPGRADE_SLOT_MELEE_GRIPS,

        // Light Saber
        ITEM_UPGRADE_SLOT_LIGHT_SABER_EMITTERS,
        ITEM_UPGRADE_SLOT_LIGHT_SABER_LENSES,
        ITEM_UPGRADE_SLOT_LIGHT_SABER_POWER_CRYSTALS,
        ITEM_UPGRADE_SLOT_LIGHT_SABER_COLOR_CRYSTALS,

        // Armor
        ITEM_UPGRADE_SLOT_ARMOR_UNDERLAY,
        ITEM_UPGRADE_SLOT_ARMOR_OVERLAY
    }

    public String getItemUpgradeSlotName(itemUpgradeSlot itemUpgradeSlot)    {
        return itemUpgradeSlot.name()
                .replace("ITEM_UPGRADE_SLOT_RANGED_", "")
                .replace("ITEM_UPGRADE_SLOT_MELEE_", "")
                .replace("ITEM_UPGRADE_SLOT_LIGHT_SABER_", "")
                .replace("ITEM_UPGRADE_SLOT_ARMOR_", "")
                .toLowerCase();
    }

    // Weapon Slot
    public enum itemWeaponSlot{
        ITEM_WEAPON_SLOT_NONE,
        ITEM_WEAPON_SLOT_MAIN_HAND,
        ITEM_WEAPON_SLOT_OFF_HAND,
        ITEM_WEAPON_SLOT_TWO_HAND
    }

    public String getItemWeaponSlotName(itemWeaponSlot itemWeaponSlot) {
        return itemWeaponSlot.name().replace("ITEM_WEAPON_SLOT_", "").toLowerCase();
    }

    // Damage Type
    public enum itemWeaponDamageType{
        ITEM_WEAPON_DAMAGE_TYPE_NONE,
        ITEM_WEAPON_DAMAGE_TYPE_PIERCING,
        ITEM_WEAPON_DAMAGE_TYPE_BLUDGEONING,
        ITEM_WEAPON_DAMAGE_TYPE_SLASHING,
        ITEM_WEAPON_DAMAGE_TYPE_ENERGY,
        ITEM_WEAPON_DAMAGE_TYPE_UNSTOPPABLE,
        ITEM_WEAPON_DAMAGE_TYPE_SONIC,
        ITEM_WEAPON_DAMAGE_TYPE_ION,
        ITEM_WEAPON_DAMAGE_TYPE_HEAT,
        ITEM_WEAPON_DAMAGE_TYPE_COLD
    }

    public String getItemWeaponDamageTypeName(itemWeaponDamageType itemWeaponDamageType) {
        return itemWeaponDamageType.name().replace("ITEM_WEAPON_DAMAGE_TYPE_", "").toLowerCase();
    }

    // Armor Slot
    public enum itemArmorSlot{
        ITEM_ARMOR_SLOT_NONE,
        ITEM_ARMOR_SLOT_ARMOR,
        ITEM_ARMOR_SLOT_BELT,
        ITEM_ARMOR_SLOT_HAND,
        ITEM_ARMOR_SLOT_HEAD,
        ITEM_ARMOR_SLOT_IMPLANT
    }

    public String getItemArmorSlotName(itemArmorSlot itemArmorSlot) {
        return itemArmorSlot.name().replace("ITEM_ARMOR_SLOT_", "").toLowerCase();
    }

    // Armor Type
    public enum itemArmorType{
        ITEM_ARMOR_TYPE_NONE,
        ITEM_ARMOR_TYPE_LIGHT,
        ITEM_ARMOR_TYPE_MEDIUM,
        ITEM_ARMOR_TYPE_HEAVY
    }


    public String getItemArmorTypeName(itemArmorType itemArmorType) {
        return itemArmorType.name().replace("ITEM_ARMOR_TYPE_", "").toLowerCase();
    }

    //endregion

    //region Game State

    // Current game state

    public enum gameState{
        GAME_STATE_INITIALIZE,
        GAME_STATE_PLAYER_TURN,
        GAME_STATE_ENTITY_TURN,
        GAME_STATE_VICTORY,
        GAME_STATE_LOSS
    }

    //endregion

    //region Audio

    public enum audioType{
        AUDIO_TYPE_MASTER,
        AUDIO_TYPE_MUSIC,
        AUDIO_TYPE_SFX,
        AUDIO_TYPE_DIALOGUE
    }

    //endregion

    //region Dialogue

    public enum dialogueEmotion{
        DIALOGUE_EMOTION_NEUTRAL,           // Default emote state, should show base emote sprite
        DIALOGUE_EMOTION_LOVE,           // Love emote state, should show whenever romance lines are active, or when dialogue makes NPC super happy
        DIALOGUE_EMOTION_HAPPY,          // Happy emote state, should show whenever NPC gets happy from actions
        DIALOGUE_EMOTION_ANGER,          // Angry emote state, should show whenever NPC gets angry from actions
        DIALOGUE_EMOTION_RAGE            // Raging emote state, should show whenever NPC gets furious from actions
    }

    //endregion

    public enum alignment{
        ALIGNMENT_DARK,
        ALIGNMENT_DARK_GRAY,
        ALIGNMENT_NEUTRAL,
        ALIGNMENT_LIGHT_GRAY,
        ALIGNMENT_LIGHT
    }

    public String getAlignmentName(alignment alignment)    {
        return alignment.name().replace("ALIGNMENT_", "").toLowerCase();
    }

    public enum event{
        EVENT_NONE,
        EVENT_DARK_SIDE_GAINED,
        EVENT_LIGHT_SIDE_GAINED
    }

    public enum playerClass{
        PLAYER_CLASS_CONSULAR,
        PLAYER_CLASS_GUARDIAN,
        PLAYER_CLASS_SENTINEL,
    }

    public String getPlayerClassName(playerClass playerClass)    {
        return playerClass.name().replace("PLAYER_CLASS_", "").toLowerCase();
    }


    public enum entitySkills{
        ENTITY_SKILLS_AWARENESS,
        ENTITY_SKILLS_PERSUADE,
        ENTITY_SKILLS_INTELLIGENCE,
        ENTITY_SKILLS_WISDOM
    }

    public String getEntitySkillName(entitySkills entitySkills)    {
        return entitySkills.name().replace("ENTITY_SKILLS_", "").toLowerCase();
    }

    public enum currentLocation{
        CURRENT_LOCATION_NONE,
        // Planet
        CURRENT_LOCATION_DANTOOINE,
        CURRENT_LOCATION_DXUN,
        CURRENT_LOCATION_KORRIBAN,
        CURRENT_LOCATION_MALACHOR_V,
        CURRENT_LOCATION_NAR_SHADDAA,
        CURRENT_LOCATION_ONDERON,
        CURRENT_LOCATION_TELOS,

        // Ship
        CURRENT_LOCATION_EBON_HAWK,
        CURRENT_LOCATION_HARBINGER,
        CURRENT_LOCATION_RAVAGER,

        // Other
        CURRENT_LOCATION_PERAGUS,
        CURRENT_LOCATION_BATTLE,
    }

    public static String getCurrentLocationName(String currentLocationString) {

        currentLocation currentLocation;

        switch (currentLocationString) {
            // Planet
            case ResourcePaths.OST_AMBIENT_PREFIX_DANTOOINE ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_DANTOOINE;
            case ResourcePaths.OST_AMBIENT_PREFIX_DXUN ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_DXUN;
            case ResourcePaths.OST_AMBIENT_PREFIX_KORRIBAN ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_KORRIBAN;
            case ResourcePaths.OST_AMBIENT_PREFIX_MALACHOR_V ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_MALACHOR_V;
            case ResourcePaths.OST_AMBIENT_PREFIX_NAR_SHADDAA ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_NAR_SHADDAA;
            case ResourcePaths.OST_AMBIENT_PREFIX_ONDERON ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_ONDERON;
            case ResourcePaths.OST_AMBIENT_PREFIX_TELOS ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_TELOS;

            // Ship
            case ResourcePaths.OST_AMBIENT_PREFIX_EBON_HAWK ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_EBON_HAWK;
            case ResourcePaths.OST_AMBIENT_PREFIX_HARBINGER ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_HARBINGER;
            case ResourcePaths.OST_AMBIENT_PREFIX_RAVAGER ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_RAVAGER;

            // Other
            case ResourcePaths.OST_AMBIENT_PREFIX_PERAGUS, ResourcePaths.DIALOGUE_PERAGUS_DIRECTORY ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_PERAGUS;
            case ResourcePaths.OST_BATTLE_PREFIX ->
                    currentLocation = Enums.currentLocation.CURRENT_LOCATION_BATTLE;

            default -> currentLocation = Enums.currentLocation.CURRENT_LOCATION_NONE;
        }

        return getCurrentLocationName(currentLocation);
    }

    public static String getCurrentLocationName(currentLocation currentLocation) {
        String returnString = "";
        for(String currentLocationName : currentLocation.name().replace("CURRENT_LOCATION_", "").toLowerCase().split("_")) {
            returnString += Functions.capitalize(currentLocationName);
        }

        return returnString;
    }
}
