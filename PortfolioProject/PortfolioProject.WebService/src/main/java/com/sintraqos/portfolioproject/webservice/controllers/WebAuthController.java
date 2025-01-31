package com.sintraqos.portfolioproject.webservice.controllers;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.useCases.UseCaseRegisterAccount;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebAuthController {
    private final PasswordEncoder passwordEncoder;
    private final UseCaseRegisterAccount registerAccount;
    private final Logger logger;

    @Autowired
    public WebAuthController(
            PasswordEncoder passwordEncoder,
            UseCaseRegisterAccount registerAccount,
            Logger logger
    ) {
        this.registerAccount = registerAccount;
        this.passwordEncoder = passwordEncoder;
        this.logger = logger;
    }

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
            logger.warn(Errors.PASSWORD_MISMATCH);
            redirectAttributes.addAttribute("warning",Errors.PASSWORD_MISMATCH);
            return "redirect:/register";
        }

        // Hash the password
//        String passwordHash = passwordEncoder.encode(password);

        // Go to the userManager to save the account
        UserMessage registerAccountMessage = registerAccount.registerAccount(username, eMail, password);

        // If the account failed to register display the error on the page
        if (!registerAccountMessage.isSuccessful()) {
            logger.warn(registerAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", registerAccountMessage.getMessage());
            return "redirect:/register";
        }

        model.addAttribute("headerText", "Register");
        model.addAttribute("message", "Registration successful");
        logger.info("Registration successful");
        return "redirect:/login";  // Redirect to login page after successful registration
    }

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
}
