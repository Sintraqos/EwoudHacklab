package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.Account.AccountManager;
import com.sintraqos.portfolioproject.Messages.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/registerAccount")
    public String handleRegister(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "eMail") String eMail,
            @RequestParam(required = true, name = "password") String password,
            @RequestParam(required = true, name = "passwordConfirm") String passwordConfirm,
            RedirectAttributes redirectAttributes) {

        // Check for password mismatch
        if(!password.equals(passwordConfirm)){
            redirectAttributes.addAttribute("error", "Password Mismatch!");
            return "redirect:/%s".formatted(getRegisterPage());
        }

        // Create the account and check for errors
        Message message = accountManager.createAccount(username, eMail, new BCryptPasswordEncoder().encode(password));
        if(!message.isSuccessful()){
            redirectAttributes.addAttribute("error", message.getMessage());
            return "redirect:/%s".formatted(getRegisterPage());
        }

        // If registration is successful, redirect to the home page
        return "redirect:/%s".formatted(getHomePage());
    }

    @PostMapping("/loginAccount")
    public String handleLogin(
            @RequestParam(required = true, name = "username") String username,
            @RequestParam(required = true, name = "password") String password,
            RedirectAttributes redirectAttributes) {

        // Log in to the account and check for errors
        Message message = accountManager.loginAccount(username, password);
        if(!message.isSuccessful()){
            redirectAttributes.addAttribute("error", message.getMessage());
            return "redirect:/%s".formatted(getLoginPage());
        }

        // If login is successful, redirect to the account page
        return "redirect:/%s".formatted(getHomePage());
    }
}
