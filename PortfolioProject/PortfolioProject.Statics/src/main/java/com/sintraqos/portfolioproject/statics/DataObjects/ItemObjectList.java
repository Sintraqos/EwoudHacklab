package com.sintraqos.portfolioproject.statics.DataObjects;

import java.util.ArrayList;

public class ItemObjectList implements java.io.Serializable{
    ArrayList<ItemObject> itemList = new ArrayList<>();

    public ItemObjectList(){
        itemList = new ArrayList<>();
    }

    public ItemObjectList(ItemObject itemObject){
        itemList.add(itemObject);
    }

    public ItemObjectList(ArrayList<ItemObject> itemList) {
        this.itemList = itemList;
    }
}
