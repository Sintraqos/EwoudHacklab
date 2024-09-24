package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Connect.MariaDBConnectHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Initialize Spring application
        //SpringApplication.run(Main.class, args);

        // Setup components
        MariaDBConnectHandler.getInstance();
    }
}
