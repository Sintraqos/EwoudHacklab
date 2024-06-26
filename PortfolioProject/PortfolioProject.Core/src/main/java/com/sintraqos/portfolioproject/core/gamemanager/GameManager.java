package com.sintraqos.portfolioproject.core.gamemanager;

import com.sintraqos.portfolioproject.dataobjects.items.ItemManager;
import com.sintraqos.portfolioproject.entity.player.PlayerManager;
import com.sintraqos.portfolioproject.statics.Enums;
import com.sintraqos.portfolioproject.output.Console;

public class GameManager {

    // Get instance
    static GameManager instance;

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
            itemManager = new ItemManager();
            playerManager = new PlayerManager();
        }

        return instance;
    }

    Enums.gameState currentGameState;

    static  ItemManager itemManager;
    static  PlayerManager playerManager;

    GameManager() {
        Console.StringTitleOutput("Initializing GameManager");
        // Create new game manager components

        Console.StringOutput();

        SetGameState(Enums.gameState.GAME_STATE_INITIALIZE);

        Console.StringOutput("Finished setting up GameManager");

        //TODO: on finish initialization start game loop
    }

    public void SetGameState(Enums.gameState currentGameState) {
        this.currentGameState = currentGameState;
    }
}
