package com.sintraqos.portfolioproject.api.review;

// Spring components
import org.springframework.stereotype.Service;

// External components
import org.slf4j.Logger;
import lombok.Getter;
import com.fasterxml.jackson.databind.ObjectMapper;

// Java components
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Getter
public class GameReviewManager {

    private List<GameReviewObject> gamesList = new ArrayList<>();

    public GameReviewManager(Logger logger) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Assuming the file is placed inside the resources folder
            File jsonFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("baseGames.json")).getFile());

            // Deserialize the JSON into GameReviewObjects
            GameReviewObjects gameReviewObjects = objectMapper.readValue(jsonFile, GameReviewObjects.class);

            // Populate the gamesList with the data from the JSON
            this.gamesList = gameReviewObjects.getReviewObjects();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Return a list of GameReviewObject with at least the same score as the given value
     *
     * @param gameScore the minimum gameScore requireds
     *
     * @return List of GameReviewObjects
     */
    public List<GameReviewObject> getReviewObjectsFromScore(int gameScore) {
        return gamesList.stream()
                .filter(reviewObject -> reviewObject.getGameScore() >= gameScore)
                .collect(Collectors.toList());
    }
}

