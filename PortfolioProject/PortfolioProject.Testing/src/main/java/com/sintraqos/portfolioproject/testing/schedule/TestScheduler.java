package com.sintraqos.portfolioproject.testing.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestScheduler {
    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public TestScheduler(ApplicationEventPublisher eventPublisher) {

        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedRate = 5000)
    // Run every time on the time set in the application.properties
    public void runTask() {
        TestScheduleEventHandler event = new TestScheduleEventHandler(this);
        eventPublisher.publishEvent(event);
    }
}

