package com.sintraqos.portfolioproject.scheduler;

import com.sintraqos.portfolioproject.shared.SettingsHandler;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final SettingsHandler settingsHandler;
    private final Logger logger;
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public Scheduler(
            SettingsHandler settingsHandler,
            Logger logger,
            ApplicationEventPublisher eventPublisher) {
        this.settingsHandler = settingsHandler;
        this.logger = logger;
        this.eventPublisher = eventPublisher;
    }

    final long fixedRate = 5; // Write in seconds the  delay

    @Scheduled(cron = "#{@settingsHandler.scheduleTimeCron}") // Run every time on the time set in the application.properties
    @Scheduled(fixedRate= fixedRate * 1000) // Run every given time in seconds. The * 1000 is to convert the given seconds to miliseconds
    public void runTask() {
        ScheduleEventHandler event = new ScheduleEventHandler(this);
        eventPublisher.publishEvent(event);
    }

}
