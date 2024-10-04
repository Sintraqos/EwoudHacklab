package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Console;

/**
 * Use for interaction with the InteractHandler and the GameController
 */
public class GameModel{

    static GameModel instance;

    public static GameModel getInstance() {
        if (instance == null) {
            instance = new GameModel();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of GameModel");
    }

}
