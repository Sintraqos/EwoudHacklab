package com.sintraqos.portfolioproject.webservice.controllers;

import com.sintraqos.portfolioproject.statics.Message;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.user.useCases.UseCaseBanAccount;
import com.sintraqos.portfolioproject.user.useCases.UseCaseGetAccount;
import com.sintraqos.portfolioproject.user.entities.User;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebAdminController {
    private final UseCaseBanAccount banAccount;
    private final UseCaseGetAccount getAccount;

    @Autowired
    public WebAdminController(
            UseCaseBanAccount banAccount,
            UseCaseGetAccount getAccount
    ) {
        this.banAccount = banAccount;
        this.getAccount = getAccount;
    }

    //region Admin

    /**
     * Get the admin dashboard page
     *
     * @return the adminDashboard page
     */
    @PostMapping("/adminDashboard")
    public String getAdminDashboard(Model model) {
        model.addAttribute("headerText", "Dashboard");
        return "admin/adminDashboard"; // Render the home page
    }

    /**
     * Get the user manage page
     *
     * @return the user manage page
     */
    @PostMapping("/adminManageUsers")
    public String getAdminManageUsers(Model model) {
        model.addAttribute("headerText", "Manage Users");
        return "admin/adminManageUsers"; // Render the home page
    }

    @GetMapping("admin/adminManageUsers/findUser")
    public String searchUserByName(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes,
            Model model) {

        UserMessage getAccounts = getAccount.getAccounts(username);
        if (!getAccounts.isSuccessful()) {
            Console.writeError(getAccounts.getMessage());
            redirectAttributes.addAttribute("error", getAccounts.getMessage());
            return "redirect:/adminManageUsers";
        }

        model.addAttribute("users", getAccounts.getEntities());
        return getFragments("userResults");
    }

    @GetMapping("/adminAccountSettings")
    public String adminAccountSettings(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes,
            Model model) {

        UserMessage getAccountMessage = getAccount.getAccount(username);
        if (!getAccountMessage.isSuccessful()) {
            Console.writeError(getAccountMessage.getMessage());
            redirectAttributes.addAttribute("error", getAccountMessage.getMessage());
            return "redirect:/adminManageUsers";
        }

        model.addAttribute("headerText", "Settings of: %s".formatted(username));
        model.addAttribute("user", new User(getAccountMessage.getUserDTO()));
        model.addAttribute("showAdminPage", true);

        // Redirect to the settings page, add in the check for showAdminPage
        return "/settings";
    }

    @PostMapping("admin/adminBanUser")
    public String adminBanUser(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("username",username);

        // Get the account from the database
        UserMessage getAccountMessage = getAccount.getAccounts(username);
        if (!getAccountMessage.isSuccessful()) {
            Console.writeWarning(getAccountMessage.getMessage());
            redirectAttributes.addAttribute("warning", getAccountMessage.getMessage());
            return "redirect:/adminAccountSettings";
        }

        // Ban the account
        Message banAccountmessage = banAccount.banAccount(username);
        if (!banAccountmessage.isSuccessful()) {
            Console.writeWarning(banAccountmessage.getMessage());
            redirectAttributes.addAttribute("warning", banAccountmessage.getMessage());
            return "redirect:/adminAccountSettings";
        }

        redirectAttributes.addAttribute("admin", banAccountmessage.getMessage());
        return "redirect:/adminAccountSettings";
    }

    @PostMapping("admin/adminUnbanUser")
    public String adminUnbanUser(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("username",username);

        // Get the account from the database
        UserMessage getAccountMessage = getAccount.getAccounts(username);
        if (!getAccountMessage.isSuccessful()) {
            Console.writeWarning(getAccountMessage.getMessage());
            redirectAttributes.addAttribute("warning", getAccountMessage.getMessage());
            redirectAttributes.addAttribute("username",username);
            return "redirect:/adminAccountSettings";
        }

        // Unban the account
        Message unbanAccountmessage = banAccount.unbanAccount(username);
        if (!unbanAccountmessage.isSuccessful()) {
            Console.writeWarning(unbanAccountmessage.getMessage());
            redirectAttributes.addAttribute("warning", unbanAccountmessage.getMessage());
            return "redirect:/adminAccountSettings";
        }

        redirectAttributes.addAttribute("admin", unbanAccountmessage.getMessage());
        return "redirect:/adminAccountSettings";
    }

    @PostMapping("admin/adminSetUserRole")
    public String adminSetUserRole(
            @RequestParam("username") String username,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username",username);

        // Get the account from the database
        UserMessage getAccountMessage = getAccount.getAccounts(username);
        if (!getAccountMessage.isSuccessful()) {
            Console.writeWarning(getAccountMessage.getMessage());
            redirectAttributes.addAttribute("warning", getAccountMessage.getMessage());
            return "redirect:/adminAccountSettings";
        }

        redirectAttributes.addAttribute("admin", getAccountMessage.getMessage());
        return "redirect:/adminAccountSettings";
    }

    //endregion

    String getFragments(String fragmentsPage){
        return "fragments :: %s".formatted(fragmentsPage);
    }
}
