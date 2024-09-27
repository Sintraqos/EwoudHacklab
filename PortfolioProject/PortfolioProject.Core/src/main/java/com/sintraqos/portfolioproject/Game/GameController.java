package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.Statics.Console;
import lombok.Getter;

import java.util.ArrayList;

public class GameController {

    static GameController instance;

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
            instance.onNewInstance();
        }

        return instance;
    }

    void onNewInstance(){
        Console.writeLine("Created new instance of GameController");
    }

    @Getter
    private ArrayList<Game> gameLibrary = new ArrayList<>();
    /**
     * Create a new Game object using a base from the game list
     * @param gameName the name of the game we're looking for
     * @return the game from the library, if it isn't in the list returns null
     */
    public Game getGame(String gameName) {
        // Loop through the list and find the first Game object with the name given. If the Game doesn't exist in the list, return null
        return gameLibrary.stream()
                .filter(game -> game.getGameName().equalsIgnoreCase(gameName))
                .findFirst().orElse(null);
    }
}
