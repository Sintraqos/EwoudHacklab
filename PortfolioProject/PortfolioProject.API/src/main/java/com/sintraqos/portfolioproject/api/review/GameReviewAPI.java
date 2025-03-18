package com.sintraqos.portfolioproject.api.review;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
@Getter
public class GameReviewAPI {

    private List<GameReviewObject> gamesList = new ArrayList<>();

    public GameReviewAPI(Logger logger) {
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

        for(GameReviewObject object : gamesList){
            logger.info(object.toString());
        }
    }

    public List<GameReviewObject> getReviewObjectsFromScore(int gameScore) {
        return gamesList.stream()
                .filter(reviewObject -> reviewObject.getGameScore() >= gameScore)
                .collect(Collectors.toList());
    }
}

