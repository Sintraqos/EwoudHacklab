package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.shared.Errors;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

class UseCaseGetGameTest {

    @Test
    void getGame_ID() {
        GameEntity game = Instancio.create(GameEntity.class);
        System.out.printf("Attempting to get game with ID: '%s'%n", game.getGameID());
        // Get the account

        // If the account was found return the retrieved account
        Random rand = new Random();
        if (rand.nextBoolean()) {
            String message = "Game with ID: '%s' found".formatted(game.getGameID());
            System.out.println(message);
        }
        // Otherwise return the message
        else {
            String message = Errors.FIND_GAME_ID_FAILED.formatted(game.getGameID());
            System.out.println(message);
        }
    }

    @Test
    void getGame_Name() {
        GameEntity game = Instancio.create(GameEntity.class);
        System.out.printf("Attempting to get game with name: '%s'%n", game.getGameName());

        // If the account was found return the retrieved account
        Random rand = new Random();
        if (rand.nextBoolean()) {
            String message = "Game with name: '%s' found".formatted(game.getGameName());
            System.out.println(message);
        }
        // Otherwise return the message
        else {
            String message = Errors.FIND_GAME_NAME_FAILED.formatted(game.getGameName());
            System.out.println(message);
        }
    }

    @Test
    void getGames() {
        Random rand = new Random();
        Game game = Instancio.create(Game.class);
        List<Game> games = Instancio.ofList(Game.class).size(rand.nextInt(0, 15)).create();

        String message;
        if (games != null && !games.isEmpty()) {
            message = "Games containing: '%s' found".formatted(game.getGameName());
        } else {
            message = Errors.FIND_GAME_NAME_FAILED.formatted(game.getGameName());
        }
        System.out.println(message);
    }
}