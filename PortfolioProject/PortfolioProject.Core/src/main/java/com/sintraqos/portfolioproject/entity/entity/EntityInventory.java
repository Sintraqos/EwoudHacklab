package com.sintraqos.portfolioproject.entity.entity;

import com.sintraqos.portfolioproject.statics.items.InventoryItem;
import java.util.ArrayList;
import java.util.List;

public class EntityInventory {
    private final List<InventoryItem> entityInventory;

    public List<InventoryItem> getEntityInventory() {
        return entityInventory;
    }

    public EntityInventory() {
        entityInventory = new ArrayList<>();
    }

    public EntityInventory(List<InventoryItem> entityInventory) {
        this.entityInventory = entityInventory;
    }
}
