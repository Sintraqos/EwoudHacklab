package Items;

public class ItemArmor extends ItemEquipable {
    private int itemArmorValue = 0;
    //private Enums.itemArmorType itemArmorType;

    public int getItemArmorValue() {
        return itemArmorValue;
    }

    public void Setup(
            int itemID,
            String itemName,
            String itemDescription,
            //Enums.itemArmorSlot itemArmorSlot,
            int itemArmorValue
            //Enums.itemArmorType itemArmorType
    ) {
        // Setup the item base
        //Setup(itemID, itemName, itemDescription, itemArmorSlot, Enums.itemWeaponSlot.ITEM_WEAPON_SLOT_NONE);

        //this.itemArmorValue = itemArmorValue;
        //this.itemArmorType = itemArmorType;
    }

    @Override
    public String toString() {
        //return getItemName() + " " + getItemDescription() + " " + itemArmorType;
        return getItemName() + " " + getItemDescription();
    }
}
