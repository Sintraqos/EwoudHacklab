package com.sintraqos.portfolioproject.shared;

// Spring components
import org.springframework.context.annotation.*;

// External components
import org.slf4j.*;

@Configuration
public class SharedBeans {

    // This will be used for any class needing a logger
    @Bean
    public Logger logger() {
        // Use the logger for the specific class or package
        return LoggerFactory.getLogger("com.sintraqos.portfolioproject");
    }
}
