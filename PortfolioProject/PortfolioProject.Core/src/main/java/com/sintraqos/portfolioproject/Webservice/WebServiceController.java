package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Statics.Console;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

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
        return "login";
    }

    @PostMapping("/loginAccount")
    public String handleLogin(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "password") String password,
            RedirectAttributes redirectAttributes, HttpSession session) {

        // Log in to the account and check for errors
        Message message = accountManager.loginAccount(username, password);
        if (!message.isSuccessful()) {
            redirectAttributes.addAttribute("error", message.getMessage());
            return "redirect:/login";
        }

        // If login is successful, redirect to the account page
        // Fill in the required variables using the HttpSession instead of filling it inside the URL
        session.setAttribute("username", username);
        return "redirect:/account";
    }

    //endregion

    //region Account

    @GetMapping("/account")
    public String getAccountPage(HttpSession session, Model model) {
        // Retrieve the username from the session
        String username = (String) session.getAttribute("username");

        // Check if the username is not found in session
        if (username == null || username.isEmpty()) {
            // Redirect to home
            return "redirect:/home";
        }

        model.addAttribute("username", username);
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
    public String getLibraryPage() {
        //TODO: Get current account information, request the library from the DB and fill it in inside the library page
        return "library";
    }

    //endregion
}
