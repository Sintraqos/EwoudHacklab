package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.API.Review.GameReviewAPI;
import com.sintraqos.portfolioproject.API.Review.GameReviewObject;
import com.sintraqos.portfolioproject.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import com.sintraqos.portfolioproject.ForumPost.ForumPostManager;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Messages.*;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.User.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import net.datafaker.Faker;

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


        TESTRegister();
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
        List<GameReviewObject> gameReviewObjects = gameReviewAPI.getReviewObjectsFromScore(8);
        model.addAttribute("reviewList", gameReviewObjects); // Pass the list to the template

        return "home"; // Render the home page
    }

    /**
     * Get the homePage URL, use for redirecting to the default page
     *
     * @return the homePage
     */
    @PostMapping("/home")
    public String returnHome(Model model) {
//        List<GameReviewObject> gameReviewObjects = gameReviewAPI.getReviewObjectsFromScore(8);
//        model.addAttribute("reviewList", gameReviewObjects); // Pass the list to the template

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
    public String getRegisterPage() {
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
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "eMail") String eMail,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "passwordConfirm") String passwordConfirm,
            Model model, RedirectAttributes redirectAttributes) {

        // Check if passwords match
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        // Hash the password
        String passwordHash = passwordEncoder.encode(password);

        // Go to the userManager to save the account
        Message message = userManager.createAccount(username, eMail, passwordHash);

        // If the account failed to register display the error on the page
        if (!message.isSuccessful()) {
            redirectAttributes.addFlashAttribute("error", message.getMessage());
            return "redirect:/register";
        }
        model.addAttribute("message", "Registration successful");
        return "login";  // Redirect to login page after successful registration
    }

    int newAccounts = 10;
    int newMessages = 75;
    boolean createAccounts = false, addMessages = false;

    void TESTRegister() {
        Faker faker = new Faker();
        // Create random test accounts
        if (createAccounts) {
            for (int i = 0; i < newAccounts; i++) {

                Random random = new Random();
                String userName = switch (random.nextInt(5)) {
                    case 0 -> faker.worldOfWarcraft().hero();
                    case 1 -> faker.elderScrolls().firstName();
                    case 2 -> faker.massEffect().character();
                    case 3 -> faker.harryPotter().character();
                    default -> faker.fallout().character();
                };
                String eMail = userName + "@eMail.com";
                String passwordHash = passwordEncoder.encode(userName + "password");

                UserMessage userMessage = userManager.createAccount(userName, eMail, passwordHash);
            }
        }

        // Create random test messages
        if (addMessages) {
            IntStream.range(0, newMessages)  // Range from 0 to 9
                    .parallel()   // Enables parallelism
                    .forEach(i -> {
                        Random random = new Random();
                        String message = switch (random.nextInt(5)) {
                            case 0 -> faker.worldOfWarcraft().quotes();
                            case 1 -> faker.elderScrolls().quote();
                            case 2 -> faker.massEffect().quote();
                            case 3 -> faker.harryPotter().quote();
                            default -> faker.fallout().quote();
                        };

                        Message forumPostMessage = forumPostManager.addForumPost(random.nextInt(111), random.nextInt(51), message);
                        if (!forumPostMessage.isSuccessful()) {
                            Console.writeLine(forumPostMessage.getMessage());
                        }
                    });
        }
    }

    //endregion

    //region Login

    /**
     * Get the loginPage URL, use for logging into an account
     *
     * @return the loginPage
     */
    @GetMapping("/login")
    public String getLoginPage() {
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
            redirectAttributes.addFlashAttribute("error", "You must be logged in to access your account.");
            Console.writeLine("You must be logged in to access your account.");
            return "redirect:/login";
        }

        UserMessage userMessage = userManager.getAccount(authentication.getName());
        if (!userMessage.isSuccessful()) {
            redirectAttributes.addFlashAttribute("error", userMessage.getMessage());
            Console.writeLine(userMessage.getMessage());
            return "redirect:/login";
        }

        // Store the User in the session
        session.setAttribute("userObject", userMessage.getAccount());

        // Pass the created UserDTO to the model to be used on the page
        model.addAttribute("user", userMessage.getAccount());

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
        model.addAttribute("user", user);
        return "settings";
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
    public String searchGameById(
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
                redirectAttributes.addAttribute("error", addGame.getMessage());
            }

            // Since we updated the account we need to get it again from the database
            UserMessage userMessage = userManager.getAccount(user.getUsername());
            if (!userMessage.isSuccessful()) {
                redirectAttributes.addAttribute("error", userMessage.getMessage());
                return "redirect:/library";
            }

            // Update the user in the session
            session.setAttribute("userObject", userMessage.getAccount());
        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
        }

        return "redirect:/library";
    }

    //endregion

    //region Forum

    /**
     * Get the forumPage URL, use for displaying the forum page of the given game
     *
     * @param user the user object
     * @return the forumPage
     */
    @GetMapping("/forum")
    public String getForumPage(
            @RequestParam("gameID") String gameID,  // Retrieve gameID from the query parameter
            @SessionAttribute("userObject") User user,
            @SessionAttribute("forumObject") ForumPostContainer forumPosts, // Since spring doesn't like it if the sessionAttribute consists of a list cast it as an object
            Model model,
            RedirectAttributes redirectAttributes) {
        try {
            // Check if the forumPosts consists of a list
            List<ForumPostDTO> posts = forumPosts.forumPosts;

            // Convert the gameID to an integer
            int parsedGameID = Integer.parseInt(gameID);

            // Retrieve the game details using the gameID
            GameEntityMessage gameMessage = getGame(parsedGameID, redirectAttributes);
            if (!gameMessage.isSuccessful()) {
                return "redirect:/account";
            }

            // Add the game to the model, so it can be accessed in the Thymeleaf template
            model.addAttribute("game", gameMessage.getEntity());

            // Add the forum posts to the mode
            model.addAttribute("forumPosts", posts);
            model.addAttribute("user", user);

        } catch (NumberFormatException e) {
            redirectAttributes.addAttribute("error", "Invalid game ID.");
            return "redirect:/account";  // If gameID is invalid, redirect to account page
        }

        return "forum";  // Return the Thymeleaf template for the forum page
    }

    /**
     * Get the forumPage URL, use for displaying the forum page of the given game
     *
     * @param gameID             the list of forumPosts
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session            use for storing variables to store whilst the session is active
     * @return the forumPage
     */
    @GetMapping("/forum/getForumPosts_Game")
    public String getForumPosts_Game(
            @RequestParam("gameID") String gameID,
            RedirectAttributes redirectAttributes,
            HttpSession session,
            Model model) {

        // Check if the gameID is a valid numeric value
        try {
            // Try to convert the string to an integer
            int parsedGameID = Integer.parseInt(gameID);

            // Add the Game to the model
            GameEntityMessage getGameMessage = gameManager.getGame(parsedGameID);
            if (!getGameMessage.isSuccessful()) {
                redirectAttributes.addAttribute("error", getGameMessage.getMessage());
                return "redirect:/account";
            }

            // Add the current game to the model so we can gather all it's information from it
            model.addAttribute("game", getGameMessage.getEntity());

            // Get all the posts from the given gameID
            ForumPostMessage addForumPost = forumPostManager.getForumPosts_Game(parsedGameID);
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", addForumPost.getMessage());
                return "redirect:/account";
            }

            // Convert the retrieved list to the DTO format
            List<ForumPostDTO> forumPosts = new ArrayList<>();
            for (ForumPostEntity forumPostEntity : addForumPost.getForumPostEntities()) {

                // Try to retrieve the User
                UserMessage userMessage = userManager.getAccount(forumPostEntity.getAccountID());
                if (!userMessage.isSuccessful()) {
                    redirectAttributes.addAttribute("error", userMessage.getMessage()); // Error message
                    return "redirect:/account";
                }

                // Try to retrieve the Game
                GameEntityMessage gameMessage = getGame(forumPostEntity.getGameID(), redirectAttributes);
                if (!gameMessage.isSuccessful()) {
                    return "redirect:/account";
                }

                redirectAttributes.addAttribute("game", gameMessage.getEntity()); // Error message

                // Create new ForumPostDTO Object
                ForumPostDTO forumPost = new ForumPostDTO(
                        forumPostEntity.getForumPostID(),       // Forum Post ID
                        forumPostEntity.getAccountID(),         // Account ID
                        userMessage.getAccount().getUsername(), // Account Username
                        forumPostEntity.getGameID(),            // Game ID
                        gameMessage.getEntity().getGameName(), // Game Name
                        forumPostEntity.getMessage(),            // Forum Post Message
                        forumPostEntity.getPostDate()
                );

                // Add the new ForumPost to the list
                forumPosts.add(forumPost);
            }

            // Update the user in the session
            session.setAttribute("forumObject", new ForumPostContainer(forumPosts));
        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
            return "redirect:/account";
        }

        return "redirect:/forum?gameID=" + gameID;  // Retain gameID in the redirect
    }

    /**
     * Get the forumPage URL, use for displaying the forum page of the given account
     *
     * @param user               the stored user inside the session
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session            use for storing variables to store whilst the session is active
     * @return the forumPage
     */
    @GetMapping("/forum/getForumPosts_Account")
    public String getForumPosts_Account(
            @SessionAttribute("userObject") User user,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        // Check if the gameID is a valid numeric value
        try {
            // Get all the posts from the given accountID
            ForumPostMessage addForumPost = forumPostManager.getForumPosts_Account(user.getAccountID());
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", addForumPost.getMessage());
                return "redirect:/account";
            }

            // Update the user in the session
            session.setAttribute("forumObject", addForumPost.getForumPostEntities());

            List<ForumPostDTO> forumPosts = new ArrayList<>();
            for (ForumPostEntity forumPostEntity : addForumPost.getForumPostEntities()) {
                // Try to retrieve the Game
                GameEntityMessage gameMessage = getGame(forumPostEntity.getGameID(), redirectAttributes);
                if (!gameMessage.isSuccessful()) {
                    return "redirect:/account";
                }

                ForumPostDTO forumPost = new ForumPostDTO(
                        forumPostEntity.getForumPostID(),       // Forum Post ID
                        forumPostEntity.getAccountID(),         // Account ID
                        user.getUsername(),                     // Account Username
                        forumPostEntity.getGameID(),            // Game ID
                        gameMessage.getEntity().getGameName(),  // Game Name
                        forumPostEntity.getMessage(),           // Forum Post Message
                        forumPostEntity.getPostDate()
                );

                forumPosts.add(forumPost);
            }

            session.setAttribute("forumObject", new ForumPostContainer(forumPosts));

        } catch (NumberFormatException e) {
            // If the conversion fails, the accountID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
            return "redirect:/account";
        }

        return "redirect:/forum";
    }

    /**
     * Get the forumPage URL, use for posting a new forum message to the forum itself
     */
    @GetMapping("/forum/addPost")
    public String addForumPost(
            @SessionAttribute("userObject") User user,
            @RequestParam("gameID") String gameID,
            @RequestParam("message") String message,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        try {
            // Convert the accountID and gameID to integers
            int parsedGameID = Integer.parseInt(gameID);

            // Add the forum post to the database
            Message addForumPost = forumPostManager.addForumPost(user.getAccountID(), parsedGameID, message);
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", addForumPost.getMessage());
                return "redirect:/forum/getForumPosts_Game?gameID=" + gameID;  // Retain gameID in the redirect to reload the forum page
            }
        } catch (NumberFormatException e) {
            redirectAttributes.addAttribute("error", "Value is not numeric!");
            return "redirect:/forum?gameID=" + gameID;  // Retain gameID in the redirect
        }

        // After posting the message to the forum, redirect back to the forum with the gameID
        return "redirect:/forum/getForumPosts_Game?gameID=" + gameID;
    }

    //endregion

    //region Shared Methods

    private UserMessage getUser(int accountID, RedirectAttributes redirectAttributes) {
        UserMessage userMessage = userManager.getAccount(accountID);
        if (!userMessage.isSuccessful()) {
            redirectAttributes.addAttribute("error", userMessage.getMessage()); // Error message
            return new UserMessage(userMessage.getMessage());
        }

        return userMessage;
    }

    private GameEntityMessage getGame(int gameID, RedirectAttributes redirectAttributes) {
        GameEntityMessage gameMessage = gameManager.getGame(gameID);
        if (!gameMessage.isSuccessful()) {
            redirectAttributes.addAttribute("error", gameMessage.getMessage());
            return new GameEntityMessage(gameMessage.getMessage());
        }

        return gameMessage;
    }

    //endregion

    @Getter
    @AllArgsConstructor
    class ForumPostContainer {
        List<ForumPostDTO> forumPosts;
    }
}