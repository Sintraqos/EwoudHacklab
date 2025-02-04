package com.sintraqos.portfolioprojectAPI.webservice.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebAuthController {

    /**
     * Get the loginPage URL, use for logging into an account
     *
     * @return the loginPage
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }
}
