package com.sintraqos.portfolioproject.statics.items;

import com.sintraqos.portfolioproject.statics.Enums;

public class Item {
    private int itemID = -1;
    private Enums.itemType itemtype;
    private String itemName = "Items.Item Name";
    private String itemDescription = "Items.Item Description";

    public void setup(int itemID,Enums.itemType itemType, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.itemtype = itemType;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
    }

    public int getItemID() {return itemID;}
    public Enums.itemType getItemType() {return itemtype;}
    public String getItemName() {return itemName;}
    public String getItemDescription() {return itemDescription;}

    @Override
    public String toString(){return itemName + " " + itemDescription;}
}

