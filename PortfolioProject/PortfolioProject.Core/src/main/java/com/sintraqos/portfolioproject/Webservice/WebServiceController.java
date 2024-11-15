package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Messages.UserMessage;
import com.sintraqos.portfolioproject.User.User;
import com.sintraqos.portfolioproject.User.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    private UserManager userManager;
   private PasswordEncoder passwordEncoder;

    @Autowired
    public WebServiceController(UserManager userManager, PasswordEncoder passwordEncoder)
    {
        this.userManager = userManager;
        this.passwordEncoder = passwordEncoder;
    }


    public void addViewControllers(ViewControllerRegistry registry) {
        // Home page
        registry.addViewController("/home").setViewName("home");
        // Account Login and Register
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping({"/", "/home"})
    public String getHome() {
        return "home"; // Render the home page
    }

    @PostMapping("/home")
    public String returnHome() {
        return "redirect:/home";
    }

    //region Register

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "eMail") String eMail,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "passwordConfirm") String passwordConfirm,
            Model model) {

        // Check if passwords match
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        // Hash the password
        String passwordHash = passwordEncoder.encode(password);

        // Go to the userManager to save the account
        userManager.createAccount(username, eMail, passwordHash);

        model.addAttribute("message", "Registration successful");
        return "login";  // Redirect to login page after successful registration
    }

    //endregion

    //region Login

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    //endregion

    //region Account

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

    @GetMapping("/settings")
    public String getSettingsPage(@SessionAttribute("userObject") User user, Model model) {
        model.addAttribute("user", user);
        return "settings";
    }

    //endregion

    //region Library

    @GetMapping("/library")
    public String getLibraryPage(@SessionAttribute("userObject") User user, Model model) {
        model.addAttribute("user", user);
        return "library";
    }

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

            // Add the game to the model if found
            if (!addGame.isSuccessful()) {
                redirectAttributes.addAttribute("error", addGame.getMessage());
            }

            // Since we updated the account we need to get it again from the database
            UserMessage userMessage = userManager.getAccount(user.getUsername());
            if (!userMessage.isSuccessful()) {
                redirectAttributes.addAttribute("error", userMessage.getMessage());
                return "redirect:/library";
            }

            session.setAttribute("userObject", userMessage.getAccount());

        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            redirectAttributes.addAttribute("error", "Value is not numeric!"); // Error message
        }

        return "redirect:/library";
    }

    //endregion
}
