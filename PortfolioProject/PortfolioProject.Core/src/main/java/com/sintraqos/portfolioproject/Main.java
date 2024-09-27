package com.sintraqos.portfolioproject;

import com.sintraqos.portfolioproject.Connect.MariaDBConnectHandler;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Time;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        // Initialize Spring application
        //SpringApplication.run(Main.class, args);

        // Setup components
        MariaDBConnectHandler.getInstance();
        Time time = new Time(0, 60);
        Console.writeLine(time.toString());
        time.addMinute(1234);
        Console.writeLine(time.toString());
    }
}
