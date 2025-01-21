package com.sintraqos.portfolioproject.webservice.controllers;

import com.sintraqos.portfolioproject.API.review.GameReviewAPI;
import com.sintraqos.portfolioproject.API.review.GameReviewObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class WebHomeController {

    private final GameReviewAPI gameReviewAPI;

    @Autowired
    public WebHomeController(GameReviewAPI gameReviewAPI) {
        this.gameReviewAPI = gameReviewAPI;
    }

    int gameReviewScore = 8;

    /**
     * Get the homePage URL, use for loading in the default page
     *
     * @return the homePage
     */
    @GetMapping({"/", "/home"})
    public String getHome(Model model) {
        List<GameReviewObject> gameReviewObjects = gameReviewAPI.getReviewObjectsFromScore(gameReviewScore);

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
