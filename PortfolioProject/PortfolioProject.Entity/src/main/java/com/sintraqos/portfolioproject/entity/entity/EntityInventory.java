package com.sintraqos.portfolioproject.entity.entity;

import com.sintraqos.portfolioproject.dataobjects.items.InventoryItem;
import java.util.ArrayList;

public class EntityInventory {
    private ArrayList<InventoryItem> entityInventory;

    public ArrayList<InventoryItem> getEntityInventory() {
        return entityInventory;
    }

    public EntityInventory() {
        entityInventory = new ArrayList<>();
    }

    public EntityInventory(ArrayList<InventoryItem> entityInventory) {
        this.entityInventory = entityInventory;
    }
}
