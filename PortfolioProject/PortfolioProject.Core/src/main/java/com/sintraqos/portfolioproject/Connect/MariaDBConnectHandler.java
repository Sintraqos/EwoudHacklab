package com.sintraqos.portfolioproject.Connect;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;

public class MariaDBConnectHandler extends ConnectionHandlerBase {

    //region Connection

    @Override
    public void initializeConnection() {
        super.initializeConnection();
    }

    @Override
    public void startConnection() {
        super.startConnection();

        //TODO: connect to the database and check if the connection is usable

        isConnected = true;
    }

    @Override
    public void stopConnection() {
        super.stopConnection();

        isConnected = false;
    }

    //endregion

    //region Account

    @Override
    public Message createAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the userName isn't already taken by another account, if it is available add it to the table

        return super.createAccount(account);
    }

    @Override
    public Message removeAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the account exists inside the database, if it does remove it from the table
        // if(!ACCOUNT_EXISTS_IN_DB){
        //      return new Message(false, "Account with userName: " + account.getUserName() + " couldn't be found!")
        // }

        return super.removeAccount(account);
    }

    @Override
    public Message updateAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the account exists inside the database, if it does update it with the new information

        return super.updateAccount(account);
    }

    //endregion
}
