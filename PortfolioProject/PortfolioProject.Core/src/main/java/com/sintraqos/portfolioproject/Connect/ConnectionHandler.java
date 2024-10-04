package com.sintraqos.portfolioproject.Connect;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;

import java.util.ArrayList;

/**
 * The base form of the connectionHandler with all it's base functions
 */
public class ConnectionHandler {

    protected boolean isConnected;

    /**
     * Get the current connect state
     */
    public boolean getIsConnected() {
        // Check if there currently is a connection active, return the current status, if it isn't connected log it
        if (!isConnected) {
            Console.writeLine("No active connection");

            initializeConnection();

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
     * Get a user account from the database
     *
     * @param username username of the account
     * @param password password of the account
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message getAccount(String username, String password){
        Console.writeLine("Got account: " + username);

        return new Message(true, "Account received");
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
    public Message updateAccount(Account account) {
        // Check if the update account library was successful
        Message updateAccount =  updateAccountLibrary(account);
        if(!updateAccount.isSuccessful()){
            return updateAccount;
        }

        Console.writeLine("Updated account: " + account.getUserName());

        return new Message(true, "Account Updated");
    }

    /**
     * Update the given account library inside the database
     *
     * @param account the Account object from the account list
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message updateAccountLibrary(Account account) {
        for (Game game : account.getAccountLibrary().getGameLibrary()) {
            Console.writeLine(
                    "Updated Game: " + "\n" +
                            "ID: " + game.getGameID() + "\n" +
                            "Name: " + game.getGameName() + "\n" +
                            "Current Time: " + game.getGamePlayTime());
        }

        return new Message(true, "Account Library Updated");
    }

    //endregion

    //region Game

    /**
     * Create a new account object without any games
     *
     * @param game the Game object created inside the gameController
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message addGame(Game game){
        Console.writeLine("Added game: " + game.getGameName());

        return new Message(true, "Game added");
    }

    /**
     * Remove the given game from the database
     *
     * @param game the Game object from the game list
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message removeGame(Game game){
        Console.writeLine("Removed game: " + game.getGameName());

        return new Message(true, "Game removed");
    }

    /**
     * Get the games stored inside the database
     *
     * @return all games stored inside the database
     */
    public ArrayList<Game> getGameList(){
        Console.writeLine("Retrieving all games stored");

        return new ArrayList<>();
    }

    /**
     * Update the given account library inside the database
     *
     * @param accountLibrary the AccountLibrary from the given account
     * @return new message containing if it was successful and the message the function gives back
     */
    public Message updateGameList(ArrayList<Game> accountLibrary) {

        for (Game game : accountLibrary) {
            Console.writeLine(
                    "Updated Game: " + "\n" +
                            "ID: " + game.getGameID() + "\n" +
                            "Name: " + game.getGameName() + "\n" +
                            "Current Time: " + game.getGamePlayTime());
        }

        return new Message(true, "Game Library Updated");
    }

    //endregion
}
