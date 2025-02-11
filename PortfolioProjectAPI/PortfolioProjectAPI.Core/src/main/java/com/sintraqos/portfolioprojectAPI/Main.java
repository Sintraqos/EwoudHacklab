package com.sintraqos.portfolioprojectAPI;

import com.sintraqos.portfolioprojectAPI.game.service.GameService;
import com.sintraqos.portfolioprojectAPI.webservice.WebServiceBeans;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication(scanBasePackages = {"com.sintraqos.portfolioprojectAPI"})
@EnableCaching
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * After the initialization of Spring-Boot this will start
     */
    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
    }
}
