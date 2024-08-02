package com.sintraqos.portfolioproject.connect;

public class ConnectHandler {

    // Get instance
    static ConnectHandler instance;

    public static ConnectHandler getInstance() {
        if (instance == null) {
            instance = new ConnectHandler();
            if(!instance.isConnected){
                instance.initializeConnection();
            }
        }

        return instance;
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

}
