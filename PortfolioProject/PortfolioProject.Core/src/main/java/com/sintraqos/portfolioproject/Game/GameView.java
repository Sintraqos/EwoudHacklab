package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Console;

public class GameView {

    public void displayGameInformation(Game game){
        Console.writeLine("Display Game: " + game.getGameName() + " - " + game.getGamePlayTime());
    }
}
