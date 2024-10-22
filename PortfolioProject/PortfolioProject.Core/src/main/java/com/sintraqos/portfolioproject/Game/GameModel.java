package com.sintraqos.portfolioproject.Game;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Statics.Console;

import java.util.ArrayList;

/**
 * Use for interaction with the ConnectionHandler and the GameController
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


    /**
     * Create a new Game object using a base from the game list
     *
     * @param game the game Object to be added to the list
     */
    public void addGame(Game game) {
//        GameDTO gameDTO = new GameDTO(game);
//
//        ConnectionHandler.AddGameMessage addGameMessage = connectHandler.addGame(gameDTO);
//        if(addGameMessage.getMessage().isSuccessful()){
//            Console.writeLine("Added game: %s to the database".formatted(game.getGameName()));
//        }
//        else {
//            Console.writeLine("Failed to add game: %s to the database - Reason: ".formatted(game.getGameName(), addGameMessage.getMessage().getMessage()));
//        }
    }

    public ArrayList<Game> getGameLibrary() {
        return new ArrayList<>();

//        //TODO: Ask the connecthandler to retrieve the game library stored inside the database
//        ArrayList<GameDTO> tempList = new ArrayList<>();
//        for (GameDTO gameDTO : tempList) {
//
//        }

    }
}
