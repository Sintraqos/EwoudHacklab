package Items;

import java.util.ArrayList;

public class ItemManager {
    static ArrayList<Item> itemList;
    static ArrayList<ItemEquipable> itemEquipableList;
    static ArrayList<ItemArmor> itemArmorList;
    static ArrayList<ItemWeapon> itemWeaponList;
    static ArrayList<ItemUpgradeResource> itemUpgradeResourceList;


    public ItemManager() {
        itemList = new ArrayList<>();
        itemEquipableList = new ArrayList<>();
        itemArmorList = new ArrayList<>();
        itemWeaponList = new ArrayList<>();
        itemUpgradeResourceList = new ArrayList<>();
    }

    public static Item getItem(int itemID) {
        return itemList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ItemEquipable getEquipableItem(int itemID) {
        return itemEquipableList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ItemArmor getArmorItem(int itemID) {
        return itemArmorList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ItemWeapon getWeaponItem(int itemID) {
        return itemWeaponList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }

    public static ItemUpgradeResource getUpgradeResourceItem(int itemID) {
        return itemUpgradeResourceList.stream()
                .filter(item -> item.getItemID() == itemID)
                .findFirst().orElse(null);
    }
}
