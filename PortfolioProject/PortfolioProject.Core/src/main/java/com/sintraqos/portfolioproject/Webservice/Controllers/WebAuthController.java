package com.sintraqos.portfolioproject.Webservice.Controllers;

import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Errors;
import com.sintraqos.portfolioproject.User.UseCases.UseCaseRegisterAccount;
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

    @Autowired
    public WebAuthController(
            PasswordEncoder passwordEncoder,
            UseCaseRegisterAccount registerAccount
    ) {
        this.registerAccount = registerAccount;
        this.passwordEncoder = passwordEncoder;
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
            Console.writeWarning(Errors.PASSWORD_MISMATCH);
            redirectAttributes.addAttribute("warning",Errors.PASSWORD_MISMATCH);
            return "redirect:/auth/register";
        }

        // Hash the password
        String passwordHash = passwordEncoder.encode(password);

        // Go to the userManager to save the account
        Message registerAccountMessage = registerAccount.registerAccount(username, eMail, passwordHash);

        // If the account failed to register display the error on the page
        if (!registerAccountMessage.isSuccessful()) {
            Console.writeWarning(registerAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", registerAccountMessage.getMessage());
            return "redirect:/auth/register";
        }

        model.addAttribute("headerText", "Register");
        model.addAttribute("message", "Registration successful");
        Console.writeLine("Registration successful");
        return "redirect:/auth/login";  // Redirect to login page after successful registration
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

    @GetMapping("/login/test")
    @ResponseBody
    public String testLogin() {
        return "WebAuthController is working!";
    }
}
