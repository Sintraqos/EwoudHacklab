package com.sintraqos.portfolioproject.statics;

public class Enums {

    //region Items

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

    // Weapon
    public enum itemWeaponSlot{
        ITEM_WEAPON_SLOT_NONE,
        ITEM_WEAPON_SLOT_MAIN_HAND,
        ITEM_WEAPON_SLOT_OFF_HAND,
        ITEM_WEAPON_SLOT_TWO_HAND
    }

    public enum itemWeaponDamageType{
        ITEM_WEAPON_DAMAGE_TYPE_PHYSICAL_PIERCING,
        ITEM_WEAPON_DAMAGE_TYPE_PHYSICAL_BLUDGEONING,
        ITEM_WEAPON_DAMAGE_TYPE_PHYSICAL_SLASHING,
        ITEM_WEAPON_DAMAGE_TYPE_ENERGY,
        ITEM_WEAPON_DAMAGE_TYPE_UNSTOPPABLE,
        ITEM_WEAPON_DAMAGE_TYPE_SONIC,
        ITEM_WEAPON_DAMAGE_TYPE_ION,
        ITEM_WEAPON_DAMAGE_TYPE_HEAT,
        ITEM_WEAPON_DAMAGE_TYPE_COLD
    }

    // Armor
    public enum itemArmorSlot{
        ITEM_ARMOR_SLOT_NONE,
        ITEM_ARMOR_SLOT_ARMOR,
        ITEM_ARMOR_SLOT_BELT,
        ITEM_ARMOR_SLOT_HAND,
        ITEM_ARMOR_SLOT_HEAD,
        ITEM_ARMOR_SLOT_IMPLANT
    }

    public enum itemArmorType{
        ITEM_ARMOR_TYPE_LIGHT,
        ITEM_ARMOR_TYPE_MEDIUM,
        ITEM_ARMOR_TYPE_HEAVY
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
}