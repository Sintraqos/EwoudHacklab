package com.sintraqos.portfolioproject.webservice.controllers;

import com.sintraqos.portfolioproject.forum.forumPost.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.forum.forumPost.DAL.ForumPostEntity;
import com.sintraqos.portfolioproject.forum.forumPost.entities.ForumPostMessage;
import com.sintraqos.portfolioproject.forum.forumPost.service.ForumPostService;
import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.entities.User;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class WebForumController {
    private final UserService userService;
    private final GameService gameService;
    private final ForumPostService forumPostService;
    private final Logger logger;

    @Autowired
    public WebForumController(
            UserService userService,
            GameService gameService,
            ForumPostService forumPostService,
            Logger logger
    ) {
        this.userService = userService;
        this.gameService = gameService;
        this.forumPostService = forumPostService;
        this.logger = logger;
    }

    private GameEntityMessage getGame(int gameID, RedirectAttributes redirectAttributes) {
        GameEntityMessage getGameMessage = gameService.getGame(gameID);
        if (!getGameMessage.isSuccessful()) {
            logger.error(getGameMessage.getMessage());
            redirectAttributes.addAttribute("error", getGameMessage.getMessage());

            return new GameEntityMessage(getGameMessage.getMessage());
        }

        return getGameMessage;
    }
    /**
     * Get the forumPage URL, use for displaying the forum page of the given game
     *
     * @param user the user object
     * @return the forumPage
     */
    @GetMapping("/forum/{gameID}")
    public String getForumPage(
            @PathVariable("gameID") String gameID,  // Retrieve gameID from the query parameter
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @SessionAttribute("userObject") User user,
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Convert the gameID to an integer
            int parsedGameID = Integer.parseInt(gameID);

            // Retrieve the game details using the gameID
            GameEntityMessage gameMessage = getGame(parsedGameID, redirectAttributes);
            if (!gameMessage.isSuccessful()) {
                logger.error(gameMessage.getMessage());
                redirectAttributes.addAttribute("error", gameMessage.getMessage());
                return "redirect:/account";
            }

            // Add the game to the model, so it can be accessed in the Thymeleaf template
            model.addAttribute("game", gameMessage.getEntity());

            // Add the forum posts to the mode
            ForumPostMessage getForumPostMessage = forumPostService.getForumPosts_Game(parsedGameID, PageRequest.of(page, size));
            logger.debug("Retrieved forum posts: %s posts for page %s".formatted(getForumPostMessage.getForumPostEntities().getTotalElements(),page));
            if (!getForumPostMessage.isSuccessful()) {
                logger.error(getForumPostMessage.getMessage());
                redirectAttributes.addAttribute("error", getForumPostMessage.getMessage());
                return "redirect:/account";
            }

            // Update session with new posts
            model.addAttribute("headerText", "Forum");
            model.addAttribute("forumPosts", setForumPostContainer(getForumPostMessage, gameMessage));
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", getForumPostMessage.getTotalPages());
            model.addAttribute("user", user);

            logger.debug("Successfully retrieved the forum posts for game with ID: %s".formatted(parsedGameID));

        } catch (NumberFormatException e) {
            logger.error(Errors.NUMERIC_VALUE_TYPE);
            redirectAttributes.addAttribute("error", Errors.NUMERIC_VALUE_TYPE);
            return "redirect:/account";  // If gameID is invalid, redirect to account page
        }

        return "forum";  // Return the Thymeleaf template for the forum page
    }

    ForumPostContainer setForumPostContainer(ForumPostMessage forumPostMessage, GameEntityMessage gameMessage) {
        // Convert the forum post entities to DTOs
        List<ForumPostDTO> forumPosts = new ArrayList<>();
        for (ForumPostEntity forumPostEntity : forumPostMessage.getForumPostEntities()) {
            // Retrieve the user using their accountID
            UserMessage getAccountMessage = userService.getAccount(forumPostEntity.getAccountID());

            if (getAccountMessage.getUserDTO() != null && gameMessage.getEntity() != null) {
                // Create new ForumPostDTO Object
                ForumPostDTO forumPost = new ForumPostDTO(
                        forumPostEntity.getForumPostID(),
                        forumPostEntity.getAccountID(),
                        getAccountMessage.getUserDTO().getUsername(),
                        forumPostEntity.getGameID(),
                        gameMessage.getEntity().getGameName(),
                        forumPostEntity.getMessage(),
                        forumPostEntity.getPostDate()
                );

                forumPosts.add(forumPost);
            }
        }
        // Reverse the created list
        Collections.reverse(forumPosts);

        return new ForumPostContainer(forumPosts);
    }

    /**
     * Get the forumPage URL, use for posting a new forum message to the forum itself
     */
    @PostMapping("/forum/addPost")
    public String addForumPost(
            @SessionAttribute("userObject") User user,
            @RequestParam("gameID") String gameID,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {
        try {
            // Convert the accountID and gameID to integers
            int parsedGameID = Integer.parseInt(gameID);

            // Add the forum post to the database
            ForumPostMessage addForumPostMessage = forumPostService.addForumPost(user.getAccountID(), parsedGameID, message);
            if (!addForumPostMessage.isSuccessful()) {
                logger.error(addForumPostMessage.getMessage());
                redirectAttributes.addAttribute("error", addForumPostMessage.getMessage());
            }

            // Add gameID as a path variable for the redirect
            redirectAttributes.addAttribute("gameID", gameID); // This will pass gameID to the redirect URL
        } catch (NumberFormatException e) {
            logger.error(Errors.NUMERIC_VALUE_TYPE);
            redirectAttributes.addAttribute("error", Errors.NUMERIC_VALUE_TYPE);
        }

        // After posting the message to the forum, redirect back to the forum with the gameID
        return "redirect:/forum/{gameID}";
    }

    @Getter
    @AllArgsConstructor
    static class ForumPostContainer {
        List<ForumPostDTO> forumPosts;
    }
}
