package com.sintraqos.portfolioproject.game.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameEntity;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
class UseCaseAddGameTest {

    @Test
    void addGame() {
        Game game = Instancio.create(Game.class);
        addGame(game);
    }

    @Test
    void addGames() {
        StringBuilder returnString = new StringBuilder();

        List<Game> games = Instancio.ofList(Game.class).size(15).create();

        for (Game game : games) {
            GameEntityMessage message = addGame(game);

            if (!message.isSuccessful()) {
                System.out.printf("Failed to add new game: '%s'%n", message.getMessage());
                returnString.append("\n").append(message.getMessage());
            }
        }

        if (!returnString.isEmpty()) {
            System.out.println(returnString.toString());
        } else {
            System.out.println("Added all games successfully");
        }
    }

    GameEntityMessage addGame(Game game) {

        System.out.printf("Attempting to add new game: '%s'%n", game.getGameName());

        // Make a fake data checker
        Random rand = new Random();
        if (!rand.nextBoolean()) {
            return new GameEntityMessage("Failed to add new game!");
        }

        // Add new game
        GameEntity gameEntity = new GameEntity(game);
        System.out.printf("Added new game: '%s'%n", game.getGameName());
        return new GameEntityMessage(gameEntity, "Added new game: '%s'".formatted(game.getGameName()));
    }
}