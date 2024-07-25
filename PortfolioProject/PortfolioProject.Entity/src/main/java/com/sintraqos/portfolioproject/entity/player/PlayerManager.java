package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.output.Console;

public class PlayerManager {
    // Get instance
    static PlayerManager instance;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }

        return instance;
    }

    private final PlayerMaster currentPlayer;

    public PlayerMaster getCurrentPlayer() {return currentPlayer;}

    public PlayerManager(){
        Console.writeHeader("Initializing Player Manager");
        currentPlayer = new PlayerMaster();

        Console.writeLine("Finished setting up Player Manager");
        Console.writeLine();
    }

}
