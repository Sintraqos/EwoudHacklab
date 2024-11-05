package com.sintraqos.portfolioproject.Webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@Configuration
public class WebServiceController implements WebMvcConfigurer {
    @Autowired
    WebService userService;

    public void addViewControllers(ViewControllerRegistry registry) {
        // Home page
        registry.addViewController("/index").setViewName("index");
        // Account Login and Register
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        // Account Page
        registry.addViewController("/account").setViewName("account");
    }

    @PostMapping("/create")
    public String create(
            @RequestParam("username") String username,
            @RequestParam("eMail") String eMail,
            @RequestParam("password") String password,
            @RequestParam("passwordConfirm") String passwordConfirm
    )    {
//        // Check if the passwords match each other
//        if(!password.equals(passwordConfirm)) {
//            return "Password's Don't Match!";
//        }

        return  userService.create(username,eMail, password);
    }
}