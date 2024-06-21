package Entity;

import Items.Item;

import java.util.ArrayList;

public class EntityInventory {
    private ArrayList<Item> entityInventory;

    public ArrayList<Item> getEntityInventory() {
        return entityInventory;
    }

    public EntityInventory() {
        entityInventory = new ArrayList<>();
    }

    public EntityInventory(ArrayList<Item> entityInventory) {
        this.entityInventory = entityInventory;
    }
}
