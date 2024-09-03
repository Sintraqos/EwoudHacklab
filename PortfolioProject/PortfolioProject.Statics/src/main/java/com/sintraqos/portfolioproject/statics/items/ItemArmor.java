package com.sintraqos.portfolioproject.statics.items;

import com.sintraqos.portfolioproject.statics.Enums;

public class ItemArmor extends ItemEquipable {
    private int itemArmorValue = 0;
    private Enums.itemArmorType itemArmorType;

    public int getItemArmorValue() {
        return itemArmorValue;
    }
    public Enums.itemArmorType getItemArmorType() {
        return itemArmorType;
    }

    public ItemArmor(
            int itemID,
            String itemName,
            String itemDescription,
            Enums.itemArmorSlot itemArmorSlot,
            int itemArmorValue,
            Enums.itemArmorType itemArmorType
    ) {
        // Set up the item base
        setup(itemID,Enums.itemType.ITEM_TYPE_ARMOR, itemName, itemDescription, itemArmorSlot, Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_NONE);

        this.itemArmorValue = itemArmorValue;
        this.itemArmorType = itemArmorType;
    }

    @Override
    public String toString() {
        return getItemName() + " " + getItemDescription() + " " + itemArmorType;
    }
}
