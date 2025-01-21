package com.sintraqos.portfolioproject.game;

import com.sintraqos.portfolioproject.game.service.GameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public GameService gameService() {
        return new GameService();
    }

}
