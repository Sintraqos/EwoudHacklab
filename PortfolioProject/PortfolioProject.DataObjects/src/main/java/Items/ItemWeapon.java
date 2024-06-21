package Items;

public class ItemWeapon extends ItemEquipable {

    private int itemWeaponMinDamage = 1;
    private int itemWeaponMaxDamage = 6;
    private Enums.itemWeaponDamageType itemWeaponDamageType;

    public void Setup(
            int itemID,
            String itemName,
            String itemDescription,
            //Enums.itemWeaponSlot itemWeaponSlot,
            int itemWeaponMinDamage,
            int itemWeaponMaxDamage
            //Enums.itemWeaponDamageType itemWeaponDamageType
    ) {
        // Setup the item base
//        Setup(itemID, itemName, itemDescription, Enums.itemArmorSlot.ITEM_ARMOR_SLOT_NONE, itemWeaponSlot);
//
        this.itemWeaponMinDamage = itemWeaponMinDamage;
        this.itemWeaponMaxDamage = itemWeaponMaxDamage;
//        this.itemWeaponDamageType = itemWeaponDamageType;
    }

    @Override
    public String toString() {
        //return getItemName() + " " + getItemDescription() + " " + itemWeaponDamageType;
        return getItemName() + " " + getItemDescription();
    }
}
