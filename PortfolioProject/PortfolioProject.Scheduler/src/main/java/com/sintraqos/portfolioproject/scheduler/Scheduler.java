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

    @Scheduled(cron = "#{@settingsHandler.scheduleTimeCron}") // Run every time on the time set in the application.properties
    public void runTask() {
        ScheduleEventHandler event = new ScheduleEventHandler(this);
        eventPublisher.publishEvent(event);
    }
}
