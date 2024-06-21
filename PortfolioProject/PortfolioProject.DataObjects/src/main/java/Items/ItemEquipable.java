package Items;

import java.util.ArrayList;

public class ItemEquipable extends Item{
    //private Enums.itemArmorSlot itemArmorSlot = Enums.itemArmorSlot.ITEM_ARMOR_SLOT_NONE;
    //private Enums.itemWeaponSlot itemWeaponSlot = Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_NONE;

    //private ArrayList<Enums.itemUpgradeSlot> itemUpgradeSlots;

    //public void Setup(int itemID, String itemName, String itemDescription, Enums.itemArmorSlot itemArmorSlot, Enums.itemWeaponSlot itemWeaponSlot) {
    public void Setup(int itemID, String itemName, String itemDescription) {
        Setup(itemID, itemName, itemDescription);

        //this.itemArmorSlot = itemArmorSlot;
        //this.itemWeaponSlot = itemWeaponSlot;

        //itemUpgradeSlots = new ArrayList<>();
    }
}
