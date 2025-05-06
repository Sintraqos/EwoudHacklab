package com.sintraqos.portfolioproject.webservice.controllers;

// Project components
import com.sintraqos.portfolioproject.api.review.*;
import com.sintraqos.portfolioproject.shared.SettingsHandler;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Java components
import java.util.List;

@Controller
@RequestMapping("/")
public class WebHomeController {

    private final GameReviewManager gameReviewAPI;
    private final SettingsHandler settingsHandler;

    @Autowired
    public WebHomeController(GameReviewManager gameReviewAPI,SettingsHandler settingsHandler) {
        this.gameReviewAPI = gameReviewAPI;
        this.settingsHandler = settingsHandler;
    }

    /**
     * Get the homePage URL, use for loading in the default page
     *
     * @return the homePage
     */
    @GetMapping({"/", "/home"})
    public String getHome(Model model) {
        List<GameReviewObject> gameReviewObjects = gameReviewAPI.getReviewObjectsFromScore(settingsHandler.getGameReviewScore());

        model.addAttribute("headerText", "Home");
        model.addAttribute("reviewList", gameReviewObjects); // Pass the list to the template

        return "home"; // Render the home page
    }

    /**
     * Get the homePage URL, use for redirecting to the default page
     *
     * @return the homePage
     */
    @PostMapping("/home")
    public String returnHome() {
        return "redirect:/home";
    }
}
