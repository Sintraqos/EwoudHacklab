package com.sintraqos.portfolioproject.webservice.service;

import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
public class GameServiceClient {

    private final RestTemplate restTemplate;
    private final  SettingsHandler settingsHandler;

    @Autowired
    public GameServiceClient(RestTemplate restTemplate, SettingsHandler settingsHandler) {
        this.restTemplate = restTemplate;
        this.settingsHandler = settingsHandler;

        apiURL = settingsHandler.getApiURl() + "/api/games";
    }

    String apiURL;

    public GameDTO getGameById(int gameID) {
        String url = apiURL + "/" + gameID;
        return restTemplate.getForObject(url, GameDTO.class);
    }

    public List<GameDTO> getGamesByName(String gameName) {
        String url = UriComponentsBuilder.fromHttpUrl(apiURL)
                .queryParam("gameName", gameName)
                .toUriString();
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<GameDTO>>() {}).getBody();
    }

    public List<GameDTO> getRecentlyAddedGames() {
        return restTemplate.getForObject(apiURL, List.class);
    }
}
