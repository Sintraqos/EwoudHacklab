package com.sintraqos.portfolioproject.statics.DataObjects;

import com.sintraqos.portfolioproject.statics.Enums;

import java.util.ArrayList;

public class ItemObject implements java.io.Serializable {
    int itemID;
    Enums.itemType itemType;
    String itemName;
    String itemDescription;

    // Equipable
    Enums.itemArmorSlot itemArmorSlot;
    Enums.itemWeaponSlot itemWeaponSlot;
    ArrayList<Enums.itemUpgradeSlot> itemUpgradeSlots;

    // Armor
    int itemArmorValue;
    Enums.itemArmorType itemArmorType;

    int itemWeaponMinDamage;
    int itemWeaponMaxDamage;
    Enums.itemWeaponDamageType itemWeaponDamageType;

    //region Getters

    public int getItemID() {return itemID;}
    public Enums.itemType getItemType() {return itemType;}
    public String getItemName() {return itemName;}
    public String getItemDescription() {return itemDescription;}
    public Enums.itemArmorSlot getItemArmorSlot() {return itemArmorSlot;}
    public Enums.itemWeaponSlot getItemWeaponSlot() {return itemWeaponSlot;}
    public ArrayList<Enums.itemUpgradeSlot> getItemUpgradeSlots() {return itemUpgradeSlots;}
    public int getItemArmorValue() {return itemArmorValue;}
    public Enums.itemArmorType getItemArmorType() {return itemArmorType;}
    public int getItemWeaponMinDamage() {return itemWeaponMinDamage;}
    public int getItemWeaponMaxDamage() {return itemWeaponMaxDamage;}
    public Enums.itemWeaponDamageType getItemWeaponDamageType() {return itemWeaponDamageType;}

    //endregion

    public ItemObject(){}

    // Armor
   public ItemObject createArmorItemObject(
            int itemID,
            Enums.itemType itemType,
            String itemName,
            String itemDescription,
            Enums.itemArmorSlot itemArmorSlot,
            ArrayList<Enums.itemUpgradeSlot> itemUpgradeSlots,
            int itemArmorValue,
            Enums.itemArmorType itemArmorType) {
       return new ItemObject(
               itemID,
               itemType,
               itemName,
               itemDescription,
               itemArmorSlot,
               Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_NONE,
               itemUpgradeSlots,
               itemArmorValue,
               itemArmorType,
               0,
               0,
               Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_NONE
       );
   }

    // Weapon
    public ItemObject createWeaponItemObject(
            int itemID,
            Enums.itemType itemType,
            String itemName,
            String itemDescription,
            Enums.itemWeaponSlot itemWeaponSlot,
            ArrayList<Enums.itemUpgradeSlot> itemUpgradeSlots,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
      return  new ItemObject(
                itemID,
                itemType,
                itemName,
                itemDescription,
                Enums.itemArmorSlot.ITEM_ARMOR_SLOT_NONE,
                itemWeaponSlot,
                itemUpgradeSlots,
                0,
                Enums.itemArmorType.ITEM_ARMOR_TYPE_NONE,
                itemWeaponMinDamage,
                itemWeaponMaxDamage,
                itemWeaponDamageType
                );
    }

    ItemObject(
            int itemID,
            Enums.itemType itemType,
            String itemName,
            String itemDescription,
            Enums.itemArmorSlot itemArmorSlot,
            Enums.itemWeaponSlot itemWeaponSlot,
            ArrayList<Enums.itemUpgradeSlot> itemUpgradeSlots,
            int itemArmorValue,
            Enums.itemArmorType itemArmorType,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        this.itemID = itemID;
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemArmorSlot = itemArmorSlot;
        this.itemWeaponSlot = itemWeaponSlot;
        this.itemUpgradeSlots = itemUpgradeSlots;
        this.itemArmorValue = itemArmorValue;
        this.itemArmorType = itemArmorType;
        this.itemWeaponMinDamage = itemWeaponMinDamage;
        this.itemWeaponMaxDamage = itemWeaponMaxDamage;
        this.itemWeaponDamageType = itemWeaponDamageType;
    }
}