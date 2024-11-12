package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Entities.UserEntity;
import com.sintraqos.portfolioproject.Repositories.UserRepository;
import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.Statics.Console;
import jakarta.servlet.http.HttpSession;
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

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void addViewControllers(ViewControllerRegistry registry) {
        // Home page
        registry.addViewController("/home").setViewName("home");
        // Account Login and Register
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/login").setViewName("login");
    }

    @GetMapping("/")
    public String getHomePage() {
        return "home";
    }

    @PostMapping("/returnHome")
    public String returnHome() {
        return "redirect:/home";
    }

    //region Register

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/registerAccount")
    public String handleRegister(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "eMail") String eMail,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "passwordConfirm") String passwordConfirm,
            Model model) {

        // Validate password
        if (!password.equals(passwordConfirm)) {
            model.addAttribute("error", "Passwords do not match!");
            return "register";
        }

        // Hash the password
        String passwordHash = passwordEncoder.encode(password);

        // Create and save the new user
        UserEntity user = new UserEntity(username, eMail, passwordHash);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        userRepository.save(user);

        model.addAttribute("message", "Registration successful!");
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
    public String getAccountPage(Model model) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // This is the logged-in username

        // Fetch the complete UserEntity from the database using the username
        UserEntity userEntity = userRepository.findByUsername(username);

        // Pass the username to the model to be used in the Thymeleaf template
        model.addAttribute("user", userEntity);

        return "account";  // Return the account.html view
    }

    //endregion

    //region Settings

    @GetMapping("/settings")
    public String getSettingsPage() {
        //TODO: Get current account information, request the information from the DB and fill it in inside the settings page
        return "settings";
    }

    //endregion

    //region Library

    @GetMapping("/library")
    public String getLibraryPage(HttpSession session) {
        //TODO: Get current account information, request the library from the DB and fill it in inside the library page
        //AccountDTO accountDTO = (AccountDTO) session.getAttribute("account");

//        // Retrieve the gameLibrary of the user
//        AccountMessage accountMessage = accountManager.getGames(username);

//        // Check if the account could not be found
//        if (!accountMessage.isSuccessful()) {
//            // Redirect to home
//            return "redirect:/home";
//        }
//
//        model.addAttribute("account", accountMessage);
        return "library";
    }

    //endregion
}
