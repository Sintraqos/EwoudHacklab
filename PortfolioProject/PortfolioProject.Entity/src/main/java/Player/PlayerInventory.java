package Player;

import Entity.EntityInventory;
import Items.Item;
import Items.ItemManager;

public class PlayerInventory extends EntityInventory {

    // Add Item

    // Different add item scenarios
    public boolean AddItem(Item item) {
        return AddItem(item.getItemID(), 1);
    }

    public boolean AddItem(Item item, int itemCount) {
        return AddItem(item.getItemID(), itemCount);
    }

    boolean AddItem(int itemID) {
        return AddItem(itemID, 1);
    }

    public boolean AddItem(int itemID, int itemCount) {
        // Check if there is an item with the given ID in the item list
        if (isValidItem(itemID)) {

            // If the item exists, add it to the inventory
            // TODO: Check if the player already has an item with the given itemID, if true add it to the item count
            return true;
        }

        // Otherwise return false
        return false;
    }

    // Remove item
    public boolean RemoveItem(Item item) {
        return RemoveItem(item.getItemID(), 1);
    }

    public boolean RemoveItem(Item item, int itemCount) {
        return RemoveItem(item.getItemID(), itemCount);
    }

    boolean RemoveItem(int itemID) {
        return RemoveItem(itemID, 1);
    }

    public boolean RemoveItem(int itemID, int itemCount) {
        // Check if there is an item with the given ID in the item list
        if (isValidItem(itemID)) {

            // If the item exists, check if the inventory has the given item in the inventory
            Item item = getEntityInventory().stream()
                    .filter(itemListObject -> itemListObject.getItemID() == itemID)
                    .findFirst().orElse(null);

            if(item != null){
                // TODO: Remove item from inventory
                return true;
            }
        }

        // Otherwise return false
        return false;
    }

    // Item ID check
    boolean isValidItem(int itemID) {
        if (ItemManager.getItem(itemID) != null)
            return true;

        return false;
    }
}
