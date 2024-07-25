package com.sintraqos.portfolioproject.dataobjects.items;

import com.sintraqos.portfolioproject.output.Console;

import java.util.ArrayList;

public class ItemManager {

    // Get instance
    static ItemManager instance;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
        }

        return instance;
    }

    static ArrayList<Item> itemList;
    static ArrayList<ItemEquipable> itemEquipableList;
    static ArrayList<ItemArmor> itemArmorList;
    static ArrayList<ItemWeapon> itemWeaponList;
    static ArrayList<ItemUpgradeResource> itemUpgradeResourceList;


    ItemManager() {
        Console.writeHeader("Initializing Item Manager");
        Console.writeLine("Finished setting up Item Manager");
        Console.writeLine();
    }

    //region Get Item

    public static Item getItem(int itemID) {
        return itemList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public  static ItemEquipable getEquipableItem(int itemID) {
        return itemEquipableList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public  static ItemArmor getArmorItem(int itemID) {
        return itemArmorList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ItemWeapon getWeaponItem(int itemID) {
        return itemWeaponList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public  static ItemUpgradeResource getUpgradeResourceItem(int itemID) {
        return itemUpgradeResourceList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public  static boolean isValidItem(int itemID) {
        return getItem(itemID) != null;
    }

    //endregion
}
