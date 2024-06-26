package com.sintraqos.portfolioproject.entity.player;

import com.sintraqos.portfolioproject.output.Console;

public class PlayerManager {

    private PlayerMaster currentPlayer;

    public PlayerMaster getCurrentPlayer() {return currentPlayer;}

    public PlayerManager(){
        Console.StringTitleOutput("Initializing PlayerManager");
        currentPlayer = new PlayerMaster();

        Console.StringOutput("Finished setting up PlayerManager");
    }

}
