package com.sintraqos.portfolioproject.Connect;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;

public class ConnectionHandlerBase {

    static ConnectionHandlerBase instance;
    public static ConnectionHandlerBase getInstance(){
        if(instance == null){
            instance = new ConnectionHandlerBase();
            instance.initializeConnection();
        }

        return instance;
    }

    protected boolean isConnected;

    /**
     * Get the current connect state
     */
    public boolean getIsConnected() {
        // Check if there currently is a connection active, return the current status, if it isn't connected log it
        if (!isConnected) {
            Console.writeLine("No active connection");
            return false;
        }

        return true;
    }

    //region Connection

    /**
     * Setup for connection, prepare all parts to start connection
     */
    public void initializeConnection() {
        isConnected = false;

        Console.writeLine("Initializing connection");
    }

    /**
     * Start the connection, if there is already a connection active don't do anything except log a message
     */
    public void startConnection() {
        if(getIsConnected()){
            Console.writeLine("Connection is already active");
        }

        // Start connection
        Console.writeLine("Starting connection");
    }

    /**
     * Stop the connection if it is connected
     */
    public void stopConnection() {
        // If it isn't connected just return to prevent errors
        if(!getIsConnected()) {
            return;
        }

        Console.writeLine("Stopping active connection");
    }

    //endregion

    //region Account

    /**
     * Create a new account object without any games
     *
     * @param account the Account object from the account list
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message createAccount(Account account){
        Console.writeLine("Created account: " + account.getUserName());

        return new Message(true, "Account created");
    }

    /**
     * Remove the given account from the database
     *
     * @param account the Account object from the account list
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message removeAccount(Account account){
        Console.writeLine("Removed account: " + account.getUserName());

        return new Message(true, "Account removed");
    }

    /**
     * Update the given account inside the database
     *
     * @param account the Account object from the account list
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message updateAccount(Account account){
        Console.writeLine("Updated account: " + account.getUserName());

        return new Message(true, "Account Updated");
    }

    //endregion
}
