package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.dataobjects.items.InventoryItem;
import com.sintraqos.portfolioproject.entity.entity.EntityInventory;
import com.sintraqos.portfolioproject.dataobjects.items.Item;

public class PlayerInventory extends EntityInventory {

    // Add Item

    // Different add item scenarios
    public void AddItem(Item item) {
        AddItem(item, 1);
    }

    public void AddItem(Item item, int itemCount) {
        // Check if there is an item with the given ID in the item list
        // If the item exists, add it to the inventory
        // If the item exists, check if the inventory has the given item in the inventory
        InventoryItem inventoryItem = getEntityInventory().stream()
                .filter(itemListObject -> itemListObject.item == item)
                .findFirst().orElse(null);

        if (inventoryItem != null) {
            inventoryItem.itemCount += itemCount;
        } else {
            getEntityInventory().add(new InventoryItem(item, itemCount));
        }
    }

    // Remove item
    public boolean RemoveItem(Item item) {
        return RemoveItem(item, 1);
    }

    public boolean RemoveItem(Item item, int itemCount) {
        // Check if the player has the item in their inventory
        InventoryItem inventoryItem = getEntityInventory().stream()
                .filter(itemListObject -> itemListObject.item == item)
                .findFirst().orElse(null);

        if (inventoryItem != null) {
            inventoryItem.itemCount -= itemCount;

            // If the inventory item doesn't have a count remove it from the list so it doesn't stay around
            if (inventoryItem.itemCount < 1)
                getEntityInventory().remove(item);

            return true;
        }

        // Otherwise return false
        return false;
    }
}
