package com.sintraqos.portfolioproject.scheduler;

import com.sintraqos.portfolioproject.scheduler.events.RetrieveGameEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Main scheduler of the application.
 * Use for handling when certain events should be run;
 * IE: Retrieving the newly added games from the API
 */
@Component
public class Scheduler {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public Scheduler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(cron = "#{@settingsHandler.retrieveGameTime}") // Run every time on the time set in the application.properties
    public void runTask_RetrieveGames() {
        RetrieveGameEventHandler event = new RetrieveGameEventHandler(this);
        eventPublisher.publishEvent(event);
    }
}
