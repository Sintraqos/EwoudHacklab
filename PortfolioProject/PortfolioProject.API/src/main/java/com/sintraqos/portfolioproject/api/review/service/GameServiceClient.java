package com.sintraqos.portfolioproject.api.review.service;

// Project components
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.scheduler.events.RetrieveGameEventHandler;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

// External components
import org.slf4j.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Java components
import java.util.*;

@Service
public class GameServiceClient {

    private final RestTemplate restTemplate;
    private final GameService gameService;
    private final Logger logger;
    String apiURL;

    @Autowired
    public GameServiceClient(RestTemplate restTemplate, SettingsHandler settingsHandler, GameService gameService, Logger logger) {
        this.restTemplate = restTemplate;
        this.gameService = gameService;
        this.logger = logger;

        apiURL = settingsHandler.getApiURl() + "/";
    }

    boolean attemptConnection() {
        // Check if the template exists
        if (restTemplate == null) {
            logger.error("RestTemplate is null");
            return false;
        }

        // Check if the url isn't tempy or null
        if (apiURL == null || apiURL.isEmpty()) {
            logger.error("API URL is not set or is empty");
            return false;
        }

        // Attempt a simple HTTP GET request to check connectivity.
        try {
            // If the connection can be made return true
            restTemplate.getForObject(apiURL, String.class);
            return true;
        } catch (Exception e) {
            // Otherwise log the received exception and return false
            logger.warn(Errors.API_CONNECTION_FAILED.formatted(e.getMessage()));
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
            return new ArrayList<>(); // Since we don't want to stop the program when there isn't a connection to the API, return an empty list instead
        }

        // Attempt to retrieve and parse the data from the API
        try {
            logger.debug("Retrieving games from: '%s'".formatted(apiURL));
            String jsonString = restTemplate.getForObject(apiURL, String.class);    // Retrieve the JSON as a string
            ObjectMapper objectMapper = new ObjectMapper();

            // Return the parsed jason as a new list of GameDTO
            return Arrays.stream(objectMapper.readValue(jsonString, GameDTO[].class)).toList(); // Parse the string into a GameDTO List
        } catch (JsonProcessingException e) {
            // If the parsing failed, log the exception and return an empty list
            logger.warn(e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * When a schedule event gets invoked, retrieve games from the API
     */
    @EventListener
    public void handleScheduleTickEvent(RetrieveGameEventHandler event) {
        // Retrieve the new games from the API
        List<GameDTO> newGames = getRecentlyAddedGames();
        logger.debug("Received: '%s' new games".formatted(newGames.size()));

        // Add the games recently added by the API
        gameService.addGames(getRecentlyAddedGames());
    }
}
