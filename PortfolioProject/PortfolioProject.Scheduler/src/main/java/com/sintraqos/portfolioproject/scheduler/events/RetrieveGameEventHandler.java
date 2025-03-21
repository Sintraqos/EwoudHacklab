package com.sintraqos.portfolioproject.scheduler.events;

import org.springframework.context.ApplicationEvent;

public class RetrieveGameEventHandler extends ApplicationEvent {
    public RetrieveGameEventHandler(Object source) {
        super(source);
    }
}