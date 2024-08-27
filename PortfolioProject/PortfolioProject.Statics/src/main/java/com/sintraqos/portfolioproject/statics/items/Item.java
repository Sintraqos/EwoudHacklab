package com.sintraqos.portfolioproject.statics.items;

public class Item {
    private int itemID = -1;
    private String itemName = "Items.Item Name";
    private String itemDescription = "Items.Item Description";

    public void setup(int itemID, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public int getItemID() {return itemID;}
    public String getItemName() {return itemName;}
    public String getItemDescription() {return itemDescription;}

    @Override
    public String toString(){return itemName + " " + itemDescription;}
}

