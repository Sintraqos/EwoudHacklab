package com.sintraqos.portfolioproject.connect;

import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;

import java.util.ArrayList;

public class ConnectHandler {

    public ConnectHandler() {
        if (!isConnected) {
            initializeConnection();
        }
    }

    boolean isConnected;
    public boolean getIsConnected() {return isConnected;}

    public void initializeConnection(){
        // Base start connection to IE: database
    }

    public void connect(){
        // Base connect functionality
        // Call from other scripts
    }

    // Items
    public void createItemObject(ItemObject itemObject){}
    public ArrayList<ItemObject> getItemObjects(){return new ArrayList<>();}
}
