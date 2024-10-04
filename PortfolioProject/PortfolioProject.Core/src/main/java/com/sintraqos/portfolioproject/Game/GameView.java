package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Console;

/**
 * Use for displaying game data
 */
public class GameView {

    static GameView instance;

    public static GameView getInstance() {
        if (instance == null) {
            instance = new GameView();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of GameView");
    }

    public void displayGameInformation(Game game) {
        Console.writeLine("Display Game: " + game.getGameName() + " - " + game.getGamePlayTime());
    }
}
