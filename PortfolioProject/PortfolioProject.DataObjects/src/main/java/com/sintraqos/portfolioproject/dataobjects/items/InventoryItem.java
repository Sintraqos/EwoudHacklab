package com.sintraqos.portfolioproject.dataobjects.items;

public class InventoryItem {
    public Item item;
    public int itemCount = 1;

    public InventoryItem(Item item, int itemCount) {
        this.item = item;
        this.itemCount = itemCount;
    }
}
