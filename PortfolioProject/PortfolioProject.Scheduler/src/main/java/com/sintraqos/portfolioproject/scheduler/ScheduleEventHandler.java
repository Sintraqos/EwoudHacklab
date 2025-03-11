package com.sintraqos.portfolioproject.scheduler;

import org.springframework.context.ApplicationEvent;

public class ScheduleEventHandler extends ApplicationEvent {
    public ScheduleEventHandler(Object source) {
        super(source);
    }
}