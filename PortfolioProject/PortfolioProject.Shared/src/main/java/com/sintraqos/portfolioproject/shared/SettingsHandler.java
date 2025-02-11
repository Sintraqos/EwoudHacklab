package com.sintraqos.portfolioproject.shared;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "settings")
public class SettingsHandler {

    private final Logger logger;

    @Autowired
    public SettingsHandler(Logger logger) {
        this.logger = logger;
    }

    public void logSettings() {
        if (isLogSettings()) {
            logger.info("""
                    Settings:
                    Username:
                        - Min Length: %s
                        - Max Length: %s
                    Password:
                        - Min Length: %s
                        - Max Length: %s
                        - Contain Capital: %s
                        - Contain Special Character: %s
                    Forum:
                        - Min Length: %s
                        - Max Length: %s
                    Schedule:
                        - Schedule Time: %s
                    API:
                        - URL: %s
                    """.formatted(
                    // Username
                    usernameMinLength,
                    usernameMaxLength,
                    // Password
                    passwordMinLength,
                    passwordMaxLength,
                    passwordContainCapital,
                    passwordContainSpecialChar,
                    // Forum Post
                    messageMinLength,
                    messageMaxLength,
                    // Schedule
                    scheduleTimeCron,
                    // API
                    apiURl
            ));
        }
    }

    private boolean logSettings;
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

    // API
    private String apiURl;
}
