package com.sintraqos.portfolioproject.userLibrary;

import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {
    /**
     * Service to handle storage of User objects inside the database
     */
    @Bean
    public UserLibraryService userLibraryService() {
        return new UserLibraryService();
    }

}
