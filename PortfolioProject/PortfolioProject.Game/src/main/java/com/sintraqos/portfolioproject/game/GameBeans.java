package com.sintraqos.portfolioproject.game;

// Project components
import com.sintraqos.portfolioproject.game.service.GameService;

// Spring components
import org.springframework.context.annotation.*;

@Configuration
public class GameBeans {
    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public GameService gameService() {
        return new GameService();
    }

}
