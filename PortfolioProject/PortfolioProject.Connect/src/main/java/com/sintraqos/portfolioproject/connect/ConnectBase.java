package com.sintraqos.portfolioproject.connect;

public class ConnectBase {

    // Get instance
    static ConnectBase instance;

    public static ConnectBase getInstance() {
        if (instance == null) {
            instance = new ConnectBase();
        }

        return instance;
    }

    public void connect(){
        // Base connect functionality
        // Call from other scripts
    }

}
