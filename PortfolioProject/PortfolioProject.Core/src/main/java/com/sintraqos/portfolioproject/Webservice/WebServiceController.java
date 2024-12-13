package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.API.Review.*;
import com.sintraqos.portfolioproject.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import com.sintraqos.portfolioproject.ForumPost.ForumPostManager;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Messages.*;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Errors;
import com.sintraqos.portfolioproject.User.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;
    private final ForumPostManager forumPostManager;
    private final GameManager gameManager;
    private final GameReviewAPI gameReviewAPI;

    @Autowired
    public WebServiceController(
            UserManager userManager,
            PasswordEncoder passwordEncoder,
            ForumPostManager forumPostManager,
            GameManager gameManager,
            GameReviewAPI gameReviewAPI
    ) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
        this.forumPostManager = forumPostManager;
        this.gameManager = gameManager;
        this.gameReviewAPI = gameReviewAPI;
    }

    //region Home

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

    //endregion

    //region Register

    /**
     * Get the registerPage URL, use for loading in the register page
     *
     * @return the registerPage
     */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("headerText", "Register");
        return "register";
    }

    /**
     * Get the registerPage URL, use for registering a new account
     *
     * @param username           the username of the new account
     * @param eMail              the eMail of the new account
     * @param password           the password of the new account
     * @param passwordConfirm    the passwordConfirm is used to compare with the password
     * @param model              use for adding variables which the page can read out
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @return the registerPage
     */
    @PostMapping("/register")
    public String handleRegister(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "eMail") String eMail,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "passwordConfirm") String passwordConfirm,
            Model model, RedirectAttributes redirectAttributes) {

        // Check if passwords match
        if (!password.equals(passwordConfirm)) {
            redirectAttributes.addAttribute("warning", handleWarning("Passwords do not match!"));
            return "redirect:/register";
        }

        // Hash the password
        String passwordHash = passwordEncoder.encode(password);

        // Go to the userManager to save the account
        Message message = userManager.createAccount(username, eMail, passwordHash);

        // If the account failed to register display the error on the page
        if (!message.isSuccessful()) {
            redirectAttributes.addAttribute("error", handleError(message.getMessage()));
            return "redirect:/register";
        }

        model.addAttribute("headerText", "Register");
        model.addAttribute("message", "Registration successful");
        Console.writeLine("Registration successful");
        return "redirect:/login";  // Redirect to login page after successful registration
    }

    //endregion

    //region Login

    /**
     * Get the loginPage URL, use for logging into an account
     *
     * @return the loginPage
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("headerText", "Login");
        return "login";
    }

    //endregion

    //region Account

    /**
     * Get the accountPage URL, use for displaying an account
     *
     * @param model              use for adding variables which the page can read out
     * @param session            use for storing variables to store whilst the session is active
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @return the accountPage
     */
    @GetMapping("/account")
    public String getAccountPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addAttribute("warning", handleWarning("You must be logged in to access your account."));
            return "redirect:/login";
        }

        UserMessage userMessage = userManager.getAccount(authentication.getName());
        if (!userMessage.isSuccessful()) {
            redirectAttributes.addFlashAttribute("error", userMessage.getMessage());
            Console.writeError(userMessage.getMessage());
            return "redirect:/login";
        }

        // Store the User in the session
        session.setAttribute("userObject", userMessage.getUserDTO());

        // Pass the created UserDTO to the model to be used on the page
        model.addAttribute("headerText", "Account");
        model.addAttribute("user", userMessage.getUserDTO());
        Console.writeLine(userMessage.getMessage());

        return "account";
    }

    //endregion

    //region Settings

    /**
     * Get the settingsPage URL, use for displaying account settings
     *
     * @param user  the user object
     * @param model use for adding variables which the page can read out
     * @return the settingsPage
     */
    @GetMapping("/settings")
    public String getSettingsPage(@SessionAttribute("userObject") User user, Model model) {
        model.addAttribute("headerText", "Settings");
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings/changeUsername")
    public String settingsChangeUsername(
            @RequestParam("currentusername") String currentUsername,
            @RequestParam("newUsername") String newUsername,
            @RequestParam("password") String password,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        // Check if the current username is the same as the new one
        if(currentUsername.equals(newUsername)){
            redirectAttributes.addAttribute("warning", handleWarning(Errors.USERNAME_MATCH));
            return "redirect:/settings";
        }

        String passwordHash = passwordEncoder.encode(password);
        Message updateAccount = userManager.changeUsername(currentUsername, newUsername, passwordHash);

        return "redirect:/settings";
    }

    //endregion

    //region Library

    /**
     * Get the libraryPage URL, use for displaying account library
     *
     * @param user  the user object
     * @param model use for adding variables which the page can read out
     * @return the libraryPage
     */
    @GetMapping("/library")
    public String getLibraryPage(@SessionAttribute("userObject") User user, Model model) {
        model.addAttribute("headerText", "Library of user: %s".formatted(user.getUsername()));
        model.addAttribute("user", user);
        return "library";
    }

    /**
     * Get the library addGame page URL, use for displaying adding a new game to the account library
     *
     * @param user               the user object
     * @param gameID             the game ID to add to the library
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session            use for storing variables to store whilst the session is active
     * @return the libraryPage
     */
    @GetMapping("/library/addGame")
    public String addGameById(
            @SessionAttribute("userObject") User user,
            @RequestParam("gameID") String gameID,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        // Check if the gameID is a valid numeric value
        try {
            int parsedGameID = Integer.parseInt(gameID); // Try to convert the string to an integer

            // Try to add the game to the given user
            Message addGame = userManager.addGame(user, parsedGameID);
            if (!addGame.isSuccessful()) {
                redirectAttributes.addAttribute("error", handleError(addGame.getMessage()));
                return "redirect:/library";
            }

            // Since we updated the account we need to get it again from the database
            UserMessage userMessage = userManager.getAccount(user.getUsername());
            if (!userMessage.isSuccessful()) {
                redirectAttributes.addAttribute("error", handleError(userMessage.getMessage()));
                return "redirect:/library";
            }

            // Update the user in the session
            session.setAttribute("userObject", userMessage.getUserDTO());
            Console.writeLine(userMessage.getMessage());
        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            redirectAttributes.addAttribute("error", handleError(Errors.NUMERIC_VALUE_TYPE));
        }

        return "redirect:/library";
    }

    @GetMapping("library/findGame")
    public String searchGameByName(
            @RequestParam("gameName") String gameName,
            RedirectAttributes redirectAttributes,
            Model model) {

        GameEntityMessage getGames = gameManager.getGames(gameName);
        if (!getGames.isSuccessful()) {
            redirectAttributes.addAttribute("error", handleError(getGames.getMessage()));
            return "redirect:/library";
        }

        model.addAttribute("games", getGames.getEntities());
        return "fragments :: gameResults";
    }

    //endregion

    //region Forum

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
                redirectAttributes.addAttribute("error", handleError(gameMessage.getMessage()));
                return "redirect:/account";
            }

            // Add the game to the model, so it can be accessed in the Thymeleaf template
            model.addAttribute("game", gameMessage.getEntity());

            // Add the forum posts to the mode
            ForumPostMessage getForumPost = forumPostManager.getForumPosts_Game(parsedGameID, PageRequest.of(page, size));
            Console.writeLine("Retrieved forum posts: " + getForumPost.getForumPostEntities().getTotalElements() + " posts for page " + page);
            if (!getForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", handleError(getForumPost.getMessage()));
                return "redirect:/account";
            }

            // Update session with new posts
            model.addAttribute("headerText", "Forum");
            model.addAttribute("forumPosts", setForumPostContainer(getForumPost, gameMessage));
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", getForumPost.getTotalPages());
            model.addAttribute("user", user);

            Console.writeLine("Successfully retrieved the forum posts for game with ID: " + parsedGameID);

        } catch (NumberFormatException e) {
            redirectAttributes.addAttribute("error", handleError(Errors.NUMERIC_VALUE_TYPE));
            return "redirect:/account";  // If gameID is invalid, redirect to account page
        }

        return "forum";  // Return the Thymeleaf template for the forum page
    }

    ForumPostContainer setForumPostContainer(ForumPostMessage forumPostMessage, GameEntityMessage gameMessage) {
        // Convert the forum post entities to DTOs
        List<ForumPostDTO> forumPosts = new ArrayList<>();
        for (ForumPostEntity forumPostEntity : forumPostMessage.getForumPostEntities()) {
            // Retrieve the user using their accountID
            UserMessage getUser = userManager.getAccount(forumPostEntity.getAccountID());

            // Create new ForumPostDTO Object
            ForumPostDTO forumPost = new ForumPostDTO(
                    forumPostEntity.getForumPostID(),
                    forumPostEntity.getAccountID(),
                    getUser.getUserDTO().getUsername(),
                    forumPostEntity.getGameID(),
                    gameMessage.getEntity().getGameName(),
                    forumPostEntity.getMessage(),
                    forumPostEntity.getPostDate()
            );

            forumPosts.add(forumPost);
        }
        // Reverse the created list
        Collections.reverse(forumPosts);

        return new ForumPostContainer(forumPosts);
    }

    /**
     * Get the forumPage URL, use for posting a new forum message to the forum itself
     */
    @GetMapping("/forum/addPost")
    public String addForumPost(
            @SessionAttribute("userObject") User user,
            @RequestParam("gameID") String gameID,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes) {
        try {
            // Convert the accountID and gameID to integers
            int parsedGameID = Integer.parseInt(gameID);

            // Add the forum post to the database
            Message addForumPost = forumPostManager.addForumPost(user.getAccountID(), parsedGameID, message);
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", handleError(addForumPost.getMessage()));
            }

            // Add gameID as a path variable for the redirect
            redirectAttributes.addAttribute("gameID", gameID); // This will pass gameID to the redirect URL
        } catch (NumberFormatException e) {
            redirectAttributes.addAttribute("error", handleError(Errors.NUMERIC_VALUE_TYPE));
        }

        // After posting the message to the forum, redirect back to the forum with the gameID
        return "redirect:/forum/{gameID}";
    }

    @Getter
    @AllArgsConstructor
    static class ForumPostContainer {
        List<ForumPostDTO> forumPosts;
    }

    //endregion

    //region Admin

    /**
     * Get the admin dashboard page
     *
     * @return the adminDashboard page
     */
    @PostMapping("/adminDashboard")
    public String getAdminDashboard(Model model) {
        model.addAttribute("headerText", "Dashboard");
        return "admin/adminDashboard"; // Render the home page
    }

    /**
     * Get the user manage page
     *
     * @return the user manage page
     */
    @PostMapping("/adminManageUsers")
    public String getAdminManageUsers(Model model) {
        model.addAttribute("headerText", "Manage Users");
        return "admin/adminManageUsers"; // Render the home page
    }

    @GetMapping("admin/adminManageUsers/findUser")
    public String searchUserByName(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes,
            Model model) {

        UserMessage getAccounts = userManager.getAccounts(username);
        if (!getAccounts.isSuccessful()) {
            redirectAttributes.addAttribute("error", handleError(getAccounts.getMessage()));
            return "redirect:/adminManageUsers";
        }

        model.addAttribute("users", getAccounts.getEntities());
        return "fragments :: userResults";
    }

    @GetMapping("/adminAccountSettings")
    public String adminAccountSettings(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes,
            Model model) {

        UserMessage getAccount = userManager.getAccounts(username);
        if (!getAccount.isSuccessful()) {
            redirectAttributes.addAttribute("error", handleError(getAccount.getMessage()));
            return "redirect:/adminManageUsers";
        }

        model.addAttribute("headerText", "Settings of: %s".formatted(username));
        model.addAttribute("user", getAccount.getUserDTO());
        return "settings";
    }

    //endregion

    //region Shared Methods

    private GameEntityMessage getGame(int gameID, RedirectAttributes redirectAttributes) {
        GameEntityMessage gameMessage = gameManager.getGame(gameID);
        if (!gameMessage.isSuccessful()) {
            redirectAttributes.addAttribute("error", handleError(gameMessage.getMessage()));

            return new GameEntityMessage(gameMessage.getMessage());
        }

        return gameMessage;
    }

    String handleError(String message) {
        // Write the error out
        Console.writeError(message);

        // Return the message
        return message;
    }

    String handleWarning(String message) {
        // Write the error out
        Console.writeWarning(message);

        // Return the message
        return message;
    }

    //endregion
}