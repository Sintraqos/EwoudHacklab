package com.sintraqos.portfolioproject.statics.items;

import com.sintraqos.portfolioproject.statics.Enums;

public class ItemWeapon extends ItemEquipable {

    private int itemWeaponMinDamage = 1;
    private int itemWeaponMaxDamage = 6;
    private Enums.itemWeaponDamageType itemWeaponDamageType;

    public int getItemWeaponMinDamage(){return itemWeaponMinDamage;}
    public int getItemWeaponMaxDamage(){return itemWeaponMaxDamage;}
    public Enums.itemWeaponDamageType getItemWeaponDamageType(){return itemWeaponDamageType;}

    public ItemWeapon(
            int itemID,
            String itemName,
            String itemDescription,
            Enums.itemWeaponSlot itemWeaponSlot,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        // Set up the item base
        setup(itemID,Enums.itemType.ITEM_TYPE_WEAPON, itemName, itemDescription, Enums.itemArmorSlot.ITEM_ARMOR_SLOT_NONE, itemWeaponSlot);

        this.itemWeaponMinDamage = itemWeaponMinDamage;
        this.itemWeaponMaxDamage = itemWeaponMaxDamage;
        this.itemWeaponDamageType = itemWeaponDamageType;
    }

    @Override
    public String toString() {
        return getItemName() + " " + getItemDescription() + " " + itemWeaponDamageType;
    }
}
