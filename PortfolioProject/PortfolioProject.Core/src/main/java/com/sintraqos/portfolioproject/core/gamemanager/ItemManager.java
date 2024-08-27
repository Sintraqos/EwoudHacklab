package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.items.*;

import java.util.ArrayList;

public class ItemManager {

    // Get instance
    static ItemManager instance;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
            instance.createItemList();
        }

        return instance;
    }

    static ArrayList<Item> itemList = new ArrayList<>();
    static ArrayList<ItemEquipable> itemEquipableList = new ArrayList<>();
    static ArrayList<ItemArmor> itemArmorList = new ArrayList<>();
    static ArrayList<ItemWeapon> itemWeaponList = new ArrayList<>();
    static ArrayList<ItemUpgradeResource> itemUpgradeResourceList = new ArrayList<>();

    //region Get Item

    public static Item getItem(int itemID) {
        return itemList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    public static ItemEquipable getEquipableItem(int itemID) {
        return itemEquipableList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ArrayList<ItemEquipable> getItemEquipableList() {
        return itemEquipableList;
    }

    public static ItemArmor getArmorItem(int itemID) {
        return itemArmorList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ArrayList<ItemArmor> getItemArmorList() {
        return itemArmorList;
    }

    public static ItemWeapon getWeaponItem(int itemID) {
        return itemWeaponList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ArrayList<ItemWeapon> getItemWeaponList() {
        return itemWeaponList;
    }

    public static ItemUpgradeResource getUpgradeResourceItem(int itemID) {
        return itemUpgradeResourceList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ArrayList<ItemUpgradeResource> getItemUpgradeResourceList() {
        return itemUpgradeResourceList;
    }

    public static boolean isValidItem(int itemID) {
        return getItem(itemID) != null;
    }

    //endregion

    //region Create Items

    public void createItemList() {
        Console.writeHeader("Setup Item Armor List");
        createArmorItemList();

        Console.writeHeader("Setup Item Weapon List");
        createWeaponItemList();

        for (ItemArmor item : itemArmorList) {
            GameManager.getInstance().connectHandler.createItemObject(new ItemObject().createArmorItemObject(
                    item.getItemID(),
                    item.getItemName(),
                    item.getItemDescription(),
                    item.getItemArmorSlot(),
                    item.getItemUpgradeSlots(),
                    item.getItemArmorValue(),
                    item.getItemArmorType()
            ));
        }
        for (ItemWeapon item : itemWeaponList) {
            GameManager.getInstance().connectHandler.createItemObject(new ItemObject().createWeaponItemObject(
                    item.getItemID(),
                    item.getItemName(),
                    item.getItemDescription(),
                    item.getItemWeaponSlot(),
                    item.getItemUpgradeSlots(),
                    item.getItemWeaponMinDamage(),
                    item.getItemWeaponMaxDamage(),
                    item.getItemWeaponDamageType()
            ));
        }
    }

    // region Armor

    public void createArmorItemList() {
        itemArmorList.add(createItemArmor("Test Armor Piece", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_ARMOR, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        itemArmorList.add(createItemArmor("Test Belt", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_BELT, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        itemArmorList.add(createItemArmor("Test Gloves", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_HAND, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        itemArmorList.add(createItemArmor("Test Head Gear", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_HEAD, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        itemArmorList.add(createItemArmor("Test Implant", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_IMPLANT, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
    }

    ItemArmor createItemArmor(
            String itemName,
            String itemDescription,
            Enums.itemArmorSlot itemArmorSlot,
            int itemArmorValue,
            Enums.itemArmorType itemArmorType
    ) {
        Console.writeLine("Added new item: " + itemName);

        ItemArmor itemArmor = new ItemArmor(
                getValidItemID(),
                itemName,
                itemDescription,
                itemArmorSlot,
                itemArmorValue,
                itemArmorType
        );

        itemList.add(itemArmor);
        itemEquipableList.add(itemArmor);
        itemArmorList.add(itemArmor);

        return itemArmor;
    }

    //endregion

    //region Weapon

    public void createWeaponItemList() {
        itemWeaponList.add(createItemWeapon("Test Main Hand Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_MAIN_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));
        itemWeaponList.add(createItemWeapon("Test Off Hand Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_OFF_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));
        itemWeaponList.add(createItemWeapon("Test Two Handed Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_TWO_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));

        itemEquipableList.addAll(itemWeaponList);
    }

    ItemWeapon createItemWeapon(
            String itemName,
            String itemDescription,
            Enums.itemWeaponSlot itemWeaponSlot,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        Console.writeLine("Added new item: " + itemName);
        ItemWeapon itemWeapon = new ItemWeapon(
                getValidItemID(),
                itemName,
                itemDescription,
                itemWeaponSlot,
                itemWeaponMinDamage,
                itemWeaponMaxDamage,
                itemWeaponDamageType
        );

        itemList.add(itemWeapon);
        itemEquipableList.add(itemWeapon);
        itemWeaponList.add(itemWeapon);

        return itemWeapon;
    }

    //endregion

    int getValidItemID() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            int currentIndex = i;
            if (itemList.stream()
                    .filter(item -> item.getItemID() == currentIndex)
                    .findFirst().orElse(null) == null) {
                return i;
            }
        }

        return -1;
    }

    //endregion
}
