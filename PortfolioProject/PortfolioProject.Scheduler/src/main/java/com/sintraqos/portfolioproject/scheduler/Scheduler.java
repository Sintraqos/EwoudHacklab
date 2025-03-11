package com.sintraqos.portfolioproject.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public Scheduler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(cron = "#{@settingsHandler.scheduleTimeCron}")
    // Run every time on the time set in the application.properties
    public void runTask() {
        ScheduleEventHandler event = new ScheduleEventHandler(this);
        eventPublisher.publishEvent(event);
    }
}
