package com.sintraqos.portfolioproject.connect;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.DataObjects.AccountObject;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;
import com.sintraqos.portfolioproject.statics.items.Item;

import java.util.ArrayList;

public class ConnectHandler {

    public ConnectHandler() {
        if (!isConnected) {
            initializeConnection();
        }
    }

    boolean isConnected;

    public boolean getIsConnected() {
        return isConnected;
    }

    protected void initializeConnection() {
        // Base start connection to IE: database
        isConnected = true;
        Console.writeLine("Finished connection handler setup");
        Console.writeLine();
    }

    public void connect() {
        // Base connect functionality
        // Call from other scripts
    }

    // Account
    public void registerAccount(AccountObject accountObject) {
    }

    public AccountObject getAccount(String userName, String password) {
        return new AccountObject();
    }

    // Items
    protected ArrayList<ItemObject> itemObjects = new ArrayList<>();

    public void createItemObject(ItemObject itemObject) {
    }

    public ArrayList<ItemObject> getItemObjects() {
        return itemObjects;
    }
}
