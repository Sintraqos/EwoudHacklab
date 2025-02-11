package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.shared.SettingsHandler;
import com.sintraqos.portfolioproject.webservice.WebServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.sintraqos.portfolioproject")
@Import(WebServiceConfig.class)  // Import the WebServiceConfig class
@EnableCaching
@EnableScheduling
@EnableConfigurationProperties(SettingsHandler.class)
public class Main {
    private final SettingsHandler settingsHandler;

    @Autowired
    public Main(SettingsHandler settingsHandler) {
        this.settingsHandler = settingsHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * After the initialization of Spring-Boot this will start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        // log the settings after application has finished
        settingsHandler.logSettings();
    }
}

