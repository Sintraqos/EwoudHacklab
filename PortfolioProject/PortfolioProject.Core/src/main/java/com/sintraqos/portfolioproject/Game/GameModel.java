package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Connect.ConnectionHandlerBase;
import com.sintraqos.portfolioproject.Statics.Console;

public class GameModel {

    static GameModel instance;

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
            instance.onNewInstance();
        }

        return instance;
    }

    ConnectionHandlerBase connectionHandler;

    void onNewInstance(){
        connectionHandler = ConnectionHandlerBase.getInstance();
        Console.writeLine("Created new instance of GameModel");
    }

}
