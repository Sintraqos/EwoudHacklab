package com.sintraqos.portfolioproject.webservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.scheduler.ScheduleEventHandler;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class GameServiceClient {

    private final RestTemplate restTemplate;
    private final SettingsHandler settingsHandler;
    private final GameService gameService;
    private final Logger logger;
    String apiURL;

    @Autowired
    public GameServiceClient(RestTemplate restTemplate, SettingsHandler settingsHandler, GameService gameService, Logger logger) {
        this.restTemplate = restTemplate;
        this.settingsHandler = settingsHandler;
        this.gameService = gameService;
        this.logger = logger;

        apiURL = settingsHandler.getApiURl() + "/";
    }

    boolean attemptConnection() {
        if (restTemplate == null) {
            logger.error("RestTemplate is null");
            return false;
        }

        if (apiURL == null || apiURL.isEmpty()) {
            logger.error("API URL is not set or is empty");
            return false;
        }

        try {
            // Attempt a simple HTTP GET request to check connectivity.
            restTemplate.getForObject(apiURL, String.class);
            return true;
        } catch (Exception e) {
            logger.error(Errors.API_CONNECTION_FAILED.formatted(e.getMessage()));
            return false;
        }
    }

    public GameDTO getGameById(int gameID) {
        String url = apiURL + "/" + gameID;
        return restTemplate.getForObject(url, GameDTO.class);
    }

    public List<GameDTO> getRecentlyAddedGames() {
        // Check if the connection can be made to the API
        if (!attemptConnection()) {
            return new ArrayList<>();
        }

        try {
            logger.debug("Retrieving games from: '%s'".formatted(apiURL));
            String jsonString = restTemplate.getForObject(apiURL, String.class);    // Retrieve the JSON as a string
            ObjectMapper objectMapper = new ObjectMapper();

            return Arrays.stream(objectMapper.readValue(jsonString, GameDTO[].class)).toList(); // Parse the string into a GameDTO List
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * When a schedule event gets invoked, retrieve games from the API
     */
    @EventListener
    public void handleScheduleTickEvent(ScheduleEventHandler event) {

        // Retrieve the new games from the API
        List<GameDTO> newGames = getRecentlyAddedGames();
        logger.debug("Received: '%s' new games".formatted(newGames.size()));

        // Add the games recently added by the API
        gameService.addGames(getRecentlyAddedGames());
    }
}
