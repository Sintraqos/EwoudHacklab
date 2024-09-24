package com.sintraqos.portfolioproject.Game;

import lombok.Getter;

import java.util.ArrayList;

@Getter
public class GameManager {

    private ArrayList<Game> gameLibrary;

    public Game getGame(String gameName) {
        // Loop through the list and find the first Game object with the name given. If the Game doesn't exist in the list, return null
        return gameLibrary.stream()
                .filter(game -> game.getGameName().equalsIgnoreCase(gameName))
                .findFirst().orElse(null);
    }
}
