package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.statics.Functions;
import com.sintraqos.portfolioproject.statics.ResourcePaths;
import com.sintraqos.portfolioproject.statics.items.*;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ItemManager {

    // Get instance
    static ItemManager instance;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
            instance.setup();
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

    public void setup() {
        Console.writeHeader("Setup Item Manager");
        // Get the item list from the database
        convertItemObjects(GameManager.getInstance().getConnectHandler().getItemObjects());

        // If the list doesn't have any values create new item list for that
        boolean newItemList = !itemList.isEmpty();

        Console.writeLine("Create new item armor list");
        createArmorItemList();

        Console.writeLine("Create new item weapon list");
        createWeaponItemList();

        if (newItemList) {

            IntStream.range(0, itemArmorList.size()).parallel().forEach(
                    i -> GameManager.getInstance().connectHandler.createItemObject(new ItemObject().createArmorItemObject(
                            itemArmorList.get(i).getItemID(),
                            itemArmorList.get(i).getItemType(),
                            itemArmorList.get(i).getItemName(),
                            itemArmorList.get(i).getItemDescription(),
                            itemArmorList.get(i).getItemArmorSlot(),
                            itemArmorList.get(i).getItemUpgradeSlots(),
                            itemArmorList.get(i).getItemArmorValue(),
                            itemArmorList.get(i).getItemArmorType()
                    )));

            IntStream.range(0, itemWeaponList.size()).parallel().forEach(
                    i -> GameManager.getInstance().connectHandler.createItemObject(new ItemObject().createWeaponItemObject(
                            itemWeaponList.get(i).getItemID(),
                            itemWeaponList.get(i).getItemType(),
                            itemWeaponList.get(i).getItemName(),
                            itemWeaponList.get(i).getItemDescription(),
                            itemWeaponList.get(i).getItemWeaponSlot(),
                            itemWeaponList.get(i).getItemUpgradeSlots(),
                            itemWeaponList.get(i).getItemWeaponMinDamage(),
                            itemWeaponList.get(i).getItemWeaponMaxDamage(),
                            itemWeaponList.get(i).getItemWeaponDamageType()
                    )));
        }

        Console.writeLine("Finished Setup Item Manager");
        Console.writeLine();
    }

    void convertItemObjects(ArrayList<ItemObject> itemObjects) {
        IntStream.range(0, itemObjects.size()).parallel().forEach(
                i -> {
                    switch (itemObjects.get(i).getItemType()) {
                        case ITEM_TYPE_ARMOR ->
                                itemArmorList.add(createItemArmor(itemObjects.get(i).getItemName(), itemObjects.get(i).getItemDescription(), itemObjects.get(i).getItemArmorSlot(), itemObjects.get(i).getItemArmorValue(), itemObjects.get(i).getItemArmorType()));

                        case ITEM_TYPE_WEAPON ->
                                itemWeaponList.add(createItemWeapon(itemObjects.get(i).getItemName(), itemObjects.get(i).getItemDescription(), itemObjects.get(i).getItemWeaponSlot(), itemObjects.get(i).getItemWeaponMinDamage(), itemObjects.get(i).getItemWeaponMaxDamage(), itemObjects.get(i).getItemWeaponDamageType()));
                    }
                });
    }

    // region Armor

    public void createArmorItemList() {
        addArmorItem(createItemArmor("Test Armor Piece", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_ARMOR, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        addArmorItem(createItemArmor("Test Belt", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_BELT, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        addArmorItem(createItemArmor("Test Gloves", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_HAND, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        addArmorItem(createItemArmor("Test Head Gear", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_HEAD, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
        addArmorItem(createItemArmor("Test Implant", "Just a test", Enums.itemArmorSlot.ITEM_ARMOR_SLOT_IMPLANT, 0, Enums.itemArmorType.ITEM_ARMOR_TYPE_LIGHT));
    }

    void addArmorItem(ItemArmor itemArmor) {
        if (itemArmor != null) {
            itemArmorList.add(itemArmor);
        }
    }

    ItemArmor createItemArmor(
            String itemName,
            String itemDescription,
            Enums.itemArmorSlot itemArmorSlot,
            int itemArmorValue,
            Enums.itemArmorType itemArmorType
    ) {
        if (!isNewItem(itemName)) {
            return null;
        }

        ItemArmor itemArmor = new ItemArmor(
                getValidItemID(),
                itemName,
                itemDescription,
                itemArmorSlot,
                itemArmorValue,
                itemArmorType
        );

        Console.writeLine("Created new item: " + itemName);

        itemList.add(itemArmor);
        itemEquipableList.add(itemArmor);
        itemArmorList.add(itemArmor);

        return itemArmor;
    }

    //endregion

    //region Weapon

    public void createWeaponItemList() {
        addWeaponItem(createItemWeapon("Test Main Hand Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_MAIN_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));
        addWeaponItem(createItemWeapon("Test Off Hand Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_OFF_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));
        addWeaponItem(createItemWeapon("Test Two Handed Sword", "Just a test", Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_TWO_HAND, 1, 6, Enums.itemWeaponDamageType.ITEM_WEAPON_DAMAGE_TYPE_SLASHING));

        itemEquipableList.addAll(itemWeaponList);
    }

    void addWeaponItem(ItemWeapon itemWeapon) {
        if (itemWeapon != null) {
            itemWeaponList.add(itemWeapon);
        }
    }

    ItemWeapon createItemWeapon(
            String itemName,
            String itemDescription,
            Enums.itemWeaponSlot itemWeaponSlot,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage,
            Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        if (!isNewItem(itemName)) {
            return null;
        }

        ItemWeapon itemWeapon = new ItemWeapon(
                getValidItemID(),
                itemName,
                itemDescription,
                itemWeaponSlot,
                itemWeaponMinDamage,
                itemWeaponMaxDamage,
                itemWeaponDamageType
        );

        Console.writeLine("Created new item: " + itemName);

        itemList.add(itemWeapon);
        itemEquipableList.add(itemWeapon);
        itemWeaponList.add(itemWeapon);

        return itemWeapon;
    }

    //endregion

    boolean isNewItem(String itemName) {
        return itemList.stream().noneMatch(item -> item.getItemName().equalsIgnoreCase(itemName));
    }

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
