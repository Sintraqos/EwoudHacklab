package com.sintraqos.portfolioprojectAPI.webservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfig {
    // Define beans specific to this module
    @Bean
    public WebServiceConfig portfolioService() {
        return new WebServiceConfig();
    }
}
