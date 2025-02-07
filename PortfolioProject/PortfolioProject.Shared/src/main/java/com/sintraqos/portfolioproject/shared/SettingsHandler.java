package com.sintraqos.portfolioproject.shared;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "settings")
public class SettingsHandler {
    // Username
    private int usernameMinLength;
    private int usernameMaxLength;

    // Password
    private int passwordMinLength;
    private int passwordMaxLength;
    private boolean passwordContainCapital;
    private boolean passwordContainSpecialChar;

    // Forum
    private int messageMinLength;
    private int messageMaxLength;

    // Schedule
    private String scheduleTimeCron;
}
