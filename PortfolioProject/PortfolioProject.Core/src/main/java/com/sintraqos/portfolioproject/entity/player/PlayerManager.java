package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Enums;

public class PlayerManager {
    // Get instance
    static PlayerManager instance;

    public static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();

            instance.setup();
        }

        return instance;
    }

    private PlayerMaster currentPlayer;

    public PlayerMaster getCurrentPlayer() {return currentPlayer;}

    void setup(){
        Console.writeHeader("Setup Player Manager");

        currentPlayer = new PlayerMaster();

        Console.writeLine("Finished Setup Player Manager");
        Console.writeLine();
    }

    public void createNewPlayer(boolean playerIsMale, Enums.playerClass playerClass){
        currentPlayer = new PlayerMaster(playerIsMale, playerClass);
    }

}
