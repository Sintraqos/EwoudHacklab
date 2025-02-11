package com.sintraqos.portfolioprojectAPI;

import com.sintraqos.portfolioprojectAPI.game.GameBeans;
import com.sintraqos.portfolioprojectAPI.webservice.WebServiceBeans;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreBeans {

    // Display all current loaded beans 
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//            System.out.println("Beans provided by Spring Boot:");
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//        };
//    }

    @Bean
    GameBeans gameBeans() {
        return new GameBeans();
    }

    @Bean
    WebServiceBeans webServiceBeans() {
        return new WebServiceBeans();
    }
}
