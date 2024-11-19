package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.DTO.ForumPostDTO;
import com.sintraqos.portfolioproject.Entities.ForumPostEntity;
import com.sintraqos.portfolioproject.ForumPost.ForumPostManager;
import com.sintraqos.portfolioproject.Game.GameManager;
import com.sintraqos.portfolioproject.Messages.*;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.User.*;
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
import java.util.List;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    private final UserManager userManager;
    private final PasswordEncoder passwordEncoder;
    private final ForumPostManager forumPostManager;
    private final GameManager gameManager;

    @Autowired
    public WebServiceController(UserManager userManager, PasswordEncoder passwordEncoder, ForumPostManager forumPostManager, GameManager gameManager) {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
        this.forumPostManager = forumPostManager;
        this.gameManager = gameManager;
    }

    /**
     * Get the homePage URL, use for loading in the default page
     *
     * @return the homePage
     */
    @GetMapping({"/", "/home"})
    public String getHome() {
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
     * @param username the username of the new account
     * @param eMail the eMail of the new account
     * @param password the password of the new account
     * @param passwordConfirm the passwordConfirm is used to compare with the password
     * @param model use for adding variables which the page can read out
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     *
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
     * @param model use for adding variables which the page can read out
     * @param session use for storing variables to store whilst the session is active
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     *
     * @return the accountPage
     */
    @GetMapping("/account")
    public String getAccountPage(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to access your account.");
        }

        assert authentication != null;
        UserMessage userMessage = userManager.getAccount(authentication.getName());
        if (!userMessage.isSuccessful()) {
            model.addAttribute("error", userMessage.getMessage());
            return "account";
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
     * @param user the user object
     * @param model use for adding variables which the page can read out
     *
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
     * @param user the user object
     * @param model use for adding variables which the page can read out
     *
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
     * @param user the user object
     * @param gameID the game ID to add to the library
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session use for storing variables to store whilst the session is active
     *
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
     * @param forumPosts the list of forumPosts
     *
     * @return the forumPage
     */
    @GetMapping("/forum")
    public String getForumPage(
            @SessionAttribute("userObject") User user,
            @SessionAttribute("forumObject") List<ForumPostDTO> forumPosts,
            Model model) {
        model.addAttribute("user", user);
        model.addAttribute("forumPosts", forumPosts);
        return "forum";
    }

    /**
     * Get the forumPage URL, use for displaying the forum page of the given game
     *
     * @param gameID the list of forumPosts
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session use for storing variables to store whilst the session is active
     *
     * @return the forumPage
     */
    @GetMapping("/forum/getForumPosts_Game")
    public String getForumPosts_Game(
            @RequestParam("gameID") String gameID,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        // Check if the gameID is a valid numeric value
        try {
            // Try to convert the string to an integer
            int parsedGameID = Integer.parseInt(gameID);

            // Get all the posts from the given gameID
            ForumPostMessage addForumPost = forumPostManager.getForumPosts_Game(parsedGameID);
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", addForumPost.getMessage());
                return "redirect:/account";
            }

            // Convert the retrieved list to the DTO format
            List<ForumPostDTO> forumPosts = new ArrayList<>();
            for(ForumPostEntity forumPostEntity :addForumPost.getForumPostEntities()){

                // Try to retrieve the User
                UserMessage userMessage = userManager.getAccount(forumPostEntity.getAccountID());
                if(!userMessage.isSuccessful()){
                    redirectAttributes.addAttribute("error", userMessage.getMessage()); // Error message
                    return "redirect:/account";
                }

                // Try to retrieve the Game
                GameEntityMessage gameMessage = gameManager.getGame(forumPostEntity.getGameID());
                if(!gameMessage.isSuccessful()){
                    redirectAttributes.addAttribute("error", gameMessage.getMessage()); // Error message
                    return "redirect:/account";
                }

                // Create new ForumPostDTO Object
                ForumPostDTO forumPost = new ForumPostDTO
                        (
                            forumPostEntity.getForumPostID(),       // Forum Post ID
                            forumPostEntity.getAccountID(),         // Account ID
                            userMessage.getAccount().getUsername(), // Account Username
                            forumPostEntity.getGameID(),            // Game ID
                            gameMessage.getEntity().getGameName() , // Game Name
                            forumPostEntity.getMessage()            // Forum Post Message
                        );

                // Add the new ForumPost to the list
                forumPosts.add(forumPost);
            }

            // Update the user in the session
            session.setAttribute("forumObject", forumPosts);
            System.out.println("Forum posts added to session: " + addForumPost.getForumPostEntities().size());  // Debug line

        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
            return "redirect:/account";
        }

        return "redirect:/forum";
    }

    /**
     * Get the forumPage URL, use for displaying the forum page of the given account
     *
     * @param accountID the accountID
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session use for storing variables to store whilst the session is active
     *
     * @return the forumPage
     */
    @GetMapping("/forum/getForumPosts_Account")
    public String getForumPosts_Account(
            @RequestParam("accountID") String accountID,
            RedirectAttributes redirectAttributes,
            HttpSession session) {

        // Check if the gameID is a valid numeric value
        try {
            // Try to convert the string to an integer
            int parsedAccountID = Integer.parseInt(accountID);

            // Get all the posts from the given accountID
            ForumPostMessage addForumPost = forumPostManager.getForumPosts_Account(parsedAccountID);
            if (!addForumPost.isSuccessful()) {
                redirectAttributes.addAttribute("error", addForumPost.getMessage());
                return "redirect:/account";
            }

            // Update the user in the session
            session.setAttribute("forumObject", addForumPost.getForumPostEntities());

            System.out.println("Forum posts added to session: " + addForumPost.getForumPostEntities().size());  // Debug line

        } catch (NumberFormatException e) {
            // If the conversion fails, the accountID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
            return "redirect:/account";
        }

        return "redirect:/forum";
    }

    //endregion
}
