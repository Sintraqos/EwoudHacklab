package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Messages.AccountMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Statics.Console;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    // Your controller methods here, using the authenticationManager if necessary

    @Autowired
    AccountManager accountManager;

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
            RedirectAttributes redirectAttributes) {

        // Check for password mismatch
        if (!password.equals(passwordConfirm)) {
            redirectAttributes.addAttribute("error", "Password Mismatch!");
            return "redirect:/register";
        }

        // Create the account and check for errors
        Message message = accountManager.createAccount(username, eMail, new BCryptPasswordEncoder().encode(password));
        if (!message.isSuccessful()) {
            redirectAttributes.addAttribute("error", message.getMessage());
            return "redirect:/register";
        }

        // If registration is successful, redirect to the home page
        return "redirect:/home";
    }

    //endregion

    //region Login

    @GetMapping("/login")
    public String getLoginPage() {
        Console.writeLine("Loading login page");

        return "login";
    }

//    @PostMapping("/loginAccount")
//    public String handleLogin(
//            @RequestParam(required = true, name = "username") String username,
//            @RequestParam(required = true, name = "password") String password,
//            RedirectAttributes redirectAttributes, HttpSession session) {
//
//        // Log in to the account and check for errors
//        AccountMessage accountMessage = accountManager.loginAccount(username, password);
//        if (!accountMessage.isSuccessful()) {
//            redirectAttributes.addAttribute("error", accountMessage.getMessage());
//            return "redirect:/login";
//        }
//
//
//        Console.writeLine("Login!");
//
//        AccountDTO account = accountMessage.getAccount();
//
//        // If login is successful, redirect to the account page
//        // Fill in the required variables using the HttpSession instead of filling it inside the URL
//        session.setAttribute("account", account); // Storing the account in session
//        return "redirect:/account";
//    }

    @PostMapping("/loginAccount")
    public String handleLogin(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "password") String password,
            RedirectAttributes redirectAttributes, HttpSession session) {
                return "Login failed!";
    }

    //endregion

    //region Account

    @GetMapping("/account")
    public String getAccountPage(HttpSession session, Model model) {

        Console.writeLine("Get Account info");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            // Assuming the principal contains an AccountDTO object
            AccountDTO accountDTO = (AccountDTO) authentication.getPrincipal();  // Or however you store the user info

            // Add the account to the model
            model.addAttribute("account", accountDTO);
        } else {
            return "redirect:/login";  // Redirect if the user is not authenticated
        }
        return "account";
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
