package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.connect.JSONFiles.JSONFileConnectHandler;
import com.sintraqos.portfolioproject.statics.Console;

public class GameManager {

    static GameManager instance;
    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }

        return instance;
    }

    ConnectHandler connectHandler;
    public ConnectHandler getConnectHandler(){return connectHandler;}

    public GameManager() {

        //new MariaDBConnectHandler();
        connectHandler = new JSONFileConnectHandler();

        // TODO: Await for connection to be made, if there is no connection made after a certain amount of time stop game from starting any further

        Console.writeHeader("Setup Game Manager");

        ItemManager.getInstance();
//        PlayerManager.getInstance();
//
//        DialogueManager.getInstance();
//
//        Console.writeLine("Finished Setup Game Manager");
//        Console.writeLine();
//
//        OutputManager.getInstance();
    }
}
