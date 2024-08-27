package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.statics.items.InventoryItem;
import com.sintraqos.portfolioproject.entity.entity.EntityInventory;
import com.sintraqos.portfolioproject.statics.items.Item;

public class PlayerInventory extends EntityInventory {

    // Add Item

    // Different add item scenarios
    public void addItem(Item item) {
        addItem(item, 1);
    }

    public void addItem(Item item, int itemCount) {
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
    public boolean removeItem(Item item) {
        return removeItem(item, 1);
    }

    public boolean removeItem(Item item, int itemCount) {
        // Check if the player has the item in their inventory
        InventoryItem inventoryItem = getEntityInventory().stream()
                .filter(itemListObject -> itemListObject.item == item)
                .findFirst().orElse(null);

        if (inventoryItem != null) {
            inventoryItem.itemCount -= itemCount;

            // If the inventory item doesn't have a count remove it from the list so it doesn't stay around
            if (inventoryItem.itemCount < 1) {
                getEntityInventory().remove(inventoryItem);
            }

            return true;
        }

        // Otherwise return false
        return false;
    }
}
