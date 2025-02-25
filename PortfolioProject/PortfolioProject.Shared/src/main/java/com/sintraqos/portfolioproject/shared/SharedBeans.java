package com.sintraqos.portfolioproject.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SharedBeans {

    // This will be used for any class needing a logger
    @Bean
    public Logger logger() {
        // Use the logger for the specific class or package
        return LoggerFactory.getLogger("com.sintraqos.portfolioproject");
    }
}
