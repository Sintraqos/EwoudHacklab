package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.output.Console;
import com.sintraqos.portfolioproject.output.gui.GameGUIManager;
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
        Console.writeHeader("Initializing Player Manager");
        currentPlayer = new PlayerMaster();

        Console.writeLine("Finished setting up Player Manager");
        Console.writeLine();
    }

    public void createNewPlayer(boolean playerIsMale, Enums.playerClass playerClass){
        currentPlayer = new PlayerMaster(playerIsMale, playerClass);
    }

}
