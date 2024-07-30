package com.sintraqos.portfolioproject.dataobjects.items;

import com.sintraqos.portfolioproject.statics.Enums;

public class ItemWeapon extends ItemEquipable {

    private int itemWeaponMinDamage = 1;
    private int itemWeaponMaxDamage = 6;
    private Enums.itemWeaponDamageType itemWeaponDamageType;

    public void setup(
            int itemID,
            String itemName,
            String itemDescription,
            Enums.itemWeaponSlot itemWeaponSlot,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        // Set up the item base
        setup(itemID, itemName, itemDescription, Enums.itemArmorSlot.ITEM_ARMOR_SLOT_NONE, itemWeaponSlot);

        this.itemWeaponMinDamage = itemWeaponMinDamage;
        this.itemWeaponMaxDamage = itemWeaponMaxDamage;
        this.itemWeaponDamageType = itemWeaponDamageType;
    }

    @Override
    public String toString() {
        return getItemName() + " " + getItemDescription() + " " + itemWeaponDamageType;
    }
}
