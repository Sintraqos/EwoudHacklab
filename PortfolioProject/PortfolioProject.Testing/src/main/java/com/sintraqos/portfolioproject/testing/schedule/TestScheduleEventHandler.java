package com.sintraqos.portfolioproject.testing.schedule;

import org.springframework.context.ApplicationEvent;

public class TestScheduleEventHandler extends ApplicationEvent {
    public TestScheduleEventHandler(Object source) {
        super(source);
    }
}