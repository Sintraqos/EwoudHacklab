package com.sintraqos.portfolioprojectAPI.webservice.controllers;

import com.sintraqos.portfolioprojectAPI.shared.Errors;
import com.sintraqos.portfolioprojectAPI.user.entities.*;
import com.sintraqos.portfolioprojectAPI.user.entities.UserMessage;
import com.sintraqos.portfolioprojectAPI.user.useCases.*;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebAccountController {
    private final PasswordEncoder passwordEncoder;
    private final UseCaseGetAccount getAccount;
    private final UseCaseUpdateAccount updateAccount;
    private final Logger logger;

    @Autowired
    public WebAccountController(
            PasswordEncoder passwordEncoder,
            UseCaseGetAccount getAccount,
            UseCaseUpdateAccount updateAccount,
            Logger logger
    ) {
        this.passwordEncoder = passwordEncoder;
        this.getAccount = getAccount;
        this.updateAccount = updateAccount;
        this.logger = logger;
    }

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
    public String getAccountPage(
            Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn(Errors.ACCOUNT_NOT_LOGGED_IN);
            redirectAttributes.addAttribute("warning", Errors.ACCOUNT_NOT_LOGGED_IN);
            return "redirect:/login";
        }

        UserMessage userMessage = getAccount.getAccount(authentication.getName());
        if (!userMessage.isSuccessful()) {
            redirectAttributes.addFlashAttribute("error", userMessage.getMessage());
            logger.error(userMessage.getMessage());
            return "redirect:/login";
        }

        // Store the User in the session
        session.setAttribute("userObject", userMessage.getUserDTO());

        // Pass the created UserDTO to the model to be used on the page
        model.addAttribute("headerText", "Account");
        model.addAttribute("user", userMessage.getUserDTO());

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
    public String getSettingsPage(
            @SessionAttribute("userObject") User user,
            @RequestParam(value = "showAdminPage", required = false, defaultValue = "false") boolean showAdminPage,
            Model model) {
        model.addAttribute("headerText", "Settings");
        model.addAttribute("user", user);
        model.addAttribute("showAdminPage", showAdminPage);

        return "settings";
    }

    @PostMapping("/settings/changeUsername")
    public String settingsChangeUsername(
            @RequestParam("currentUsername") String currentUsername,
            @RequestParam("newUsername") String newUsername,
            @RequestParam("password") String password,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        // Check if the current username is the same as the new one
        if (currentUsername.equals(newUsername)) {
            logger.warn(Errors.USERNAME_MATCH);
            redirectAttributes.addAttribute("warning", Errors.USERNAME_MATCH);
            return "redirect:/settings";
        }

        String passwordHash = passwordEncoder.encode(password);

        // Try to update the account
        UserMessage updateAccountMessage = updateAccount.changeUsername(currentUsername, newUsername, passwordHash);
        if (!updateAccountMessage.isSuccessful()) {
            logger.warn(updateAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", updateAccountMessage.getMessage());
            return "redirect:/settings";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn(Errors.ACCOUNT_NOT_LOGGED_IN);
            redirectAttributes.addAttribute("warning", Errors.ACCOUNT_NOT_LOGGED_IN);
            return "redirect:/login";
        }

        // Fetch the updated account with the new username
        UserMessage userMessage = getAccount.getAccount(newUsername);
        if (!userMessage.isSuccessful()) {
            redirectAttributes.addFlashAttribute("error", userMessage.getMessage());
            logger.warn(userMessage.getMessage());
            return "redirect:/login";
        }

        // Store the User in the session
        session.setAttribute("userObject", userMessage.getUserDTO());

        // Update the authentication object with the new username
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                userMessage.getUserDTO(),
                authentication.getCredentials(),
                authentication.getAuthorities());

        // Set the new authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        redirectAttributes.addAttribute("message", updateAccountMessage.getMessage());
        return "redirect:/settings";
    }

    @GetMapping("/settings/changeUsernameFragment")
    public String getChangeUsernameFragment(Model model) {
        return getFragments("changeUsername");  // Return the fragment as a view
    }

    @PostMapping("/settings/changeEMail")
    public String settingsChangeEMail(
            @SessionAttribute("userObject") User user,
            @RequestParam("eMail") String eMail,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {
        // Try to update the account
        UserMessage updateAccountMessage = updateAccount.changeEmail(user.getUsername(), eMail, password);
        if (!updateAccountMessage.isSuccessful()) {
            logger.warn(updateAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", updateAccountMessage.getMessage());
            return "redirect:/settings";
        }

        redirectAttributes.addAttribute("message", updateAccountMessage.getMessage());
        return "redirect:/settings";
    }

    @GetMapping("/settings/changeEMailFragment")
    public String getChangeEMailFragment(Model model) {
        return getFragments("changeEMail");  // Return the fragment as a view
    }

    @PostMapping("/settings/changePassword")
    public String settingsChangePassword(
            @SessionAttribute("userObject") User user,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            RedirectAttributes redirectAttributes) {
        // Check if the current username is the same as the new one
        if (currentPassword.equals(newPassword)) {
            logger.warn(Errors.PASSWORD_MATCH);
            redirectAttributes.addAttribute("warning", Errors.PASSWORD_MATCH);
            return "redirect:/settings";
        }

        // Try to update the account
        UserMessage updateAccountMessage = updateAccount.changePassword(user.getUsername(), currentPassword, newPassword);
        if (!updateAccountMessage.isSuccessful()) {
            logger.warn(updateAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", updateAccountMessage.getMessage());
            return "redirect:/settings";
        }

        redirectAttributes.addAttribute("message", updateAccountMessage.getMessage());
        return "redirect:/settings";
    }

    @GetMapping("/settings/changePasswordFragment")
    public String getChangePasswordFragment(Model model) {
        return getFragments("changePassword");  // Return the fragment as a view
    }

    //endregion

    String getFragments(String fragmentsPage) {
        return "fragments :: %s".formatted(fragmentsPage);
    }
}
