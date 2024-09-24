package com.sintraqos.portfolioproject.Connect;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Logger.Console;
import lombok.Getter;

public class ConnectionHandlerBase {

    @Getter
    static ConnectionHandlerBase instance;

    protected boolean isConnected;

    public boolean getIsConnected() {
        // Check if there currently is a connection active, return the current status, if it isn't connected log it
        if (!isConnected) {
            Console.writeLine("No active connection");
            return false;
        }

        return true;
    }

    //region Connection

    public void initializeConnection() {
        // Setup for connection, prepare all parts to start connection
        Console.writeLine("Initializing connection");

        isConnected = false;
    }

    public void startConnection() {
        if(getIsConnected()){
            Console.writeLine("Connection is already active");
        }

        // Start connection
        Console.writeLine("Started connection");

        isConnected = true;
    }

    public void stopConnection() {
        // If it isn't connected just return to prevent errors
        if(!getIsConnected()) {
            return;
        }

        isConnected = false;
        Console.writeLine("Stopped active connection");
    }

    //endregion

    //region Account

    public void createAccount(Account account){
        if(!getIsConnected()) {
            return;
        }

        Console.writeLine("Creating account: " + account.getUserName());
    }

    public void removeAccount(Account account){
        if(!getIsConnected()) {
            return;
        }

        Console.writeLine("Creating account: " + account.getUserName());
    }

    public void updateAccount(Account account){
        if(!getIsConnected()) {
            return;
        }

        Console.writeLine("Updating account: " + account.getUserName());
    }

    //endregion
}
