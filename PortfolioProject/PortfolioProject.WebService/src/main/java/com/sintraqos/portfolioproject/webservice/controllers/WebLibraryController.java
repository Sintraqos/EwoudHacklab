package com.sintraqos.portfolioproject.webservice.controllers;

import com.sintraqos.portfolioproject.game.entities.GameEntityMessage;
import com.sintraqos.portfolioproject.game.useCase.UseCaseGetGame;
import com.sintraqos.portfolioproject.statics.Message;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Errors;
import com.sintraqos.portfolioproject.user.useCases.UseCaseGetAccount;
import com.sintraqos.portfolioproject.user.useCases.UseCaseUpdateAccount;
import com.sintraqos.portfolioproject.user.entities.User;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WebLibraryController implements WebMvcConfigurer {
    private final UseCaseGetGame getGame;
    private final UseCaseUpdateAccount updateAccount;
    private final UseCaseGetAccount getAccount;

    @Autowired
    public WebLibraryController(
            UseCaseGetGame getGame,
            UseCaseUpdateAccount updateAccount,
            UseCaseGetAccount getAccount
    ) {
        this.getGame = getGame;
        this.updateAccount = updateAccount;
        this.getAccount = getAccount;
    }

    /**
     * Get the libraryPage URL, use for displaying account library
     *
     * @param user  the user object
     * @param model use for adding variables which the page can read out
     * @return the libraryPage
     */
    @GetMapping("/library")
    public String getLibraryPage(@SessionAttribute("userObject") UserDTO user, Model model) {
        model.addAttribute("headerText", "Library of user: %s".formatted(user.getUsername()));
        model.addAttribute("user", user);
        return "library";
    }

    /**
     * Get the library addGame page URL, use for displaying adding a new game to the account library
     *
     * @param user               the user object
     * @param gameID             the game ID to add to the library
     * @param redirectAttributes use for adding variables when redirecting, IE: error messages
     * @param session            use for storing variables to store whilst the session is active
     * @return the libraryPage
     */
    @GetMapping("/library/addGame")
    public String addGameById(
            @SessionAttribute("userObject") User user,
            @RequestParam("gameID") String gameID,
            RedirectAttributes redirectAttributes,
            HttpSession session) {
        // Check if the gameID is a valid numeric value
        try {
            int parsedGameID = Integer.parseInt(gameID); // Try to convert the string to an integer

            // Try to add the game to the given user
            Message updateAccountMessage = updateAccount.addGame(user, parsedGameID);
            if (!updateAccountMessage.isSuccessful()) {
                Console.writeWarning(updateAccountMessage.getMessage());
                redirectAttributes.addAttribute("error", updateAccountMessage.getMessage());
                return "redirect:/library/library";
            }

            // Since we updated the account we need to get it again from the database
            UserMessage getAccountMessage = getAccount.getAccount(user.getUsername());
            if (!getAccountMessage.isSuccessful()) {
                Console.writeWarning(getAccountMessage.getMessage());
                redirectAttributes.addAttribute("error", getAccountMessage.getMessage());
                return "redirect:/library/library";
            }

            // Update the user in the session
            session.setAttribute("userObject", getAccountMessage.getUserDTO());
            Console.writeLine(getAccountMessage.getMessage());
        } catch (NumberFormatException e) {
            // If the conversion fails, the gameID is not a valid number
            Console.writeError(Errors.NUMERIC_VALUE_TYPE);
            redirectAttributes.addAttribute("error", Errors.NUMERIC_VALUE_TYPE);
        }

        return "redirect:/library/library";
    }

    @GetMapping("library/findGame")
    public String searchGameByName(
            @RequestParam("gameName") String gameName,
            RedirectAttributes redirectAttributes,
            Model model) {

        GameEntityMessage getGamesMessage = getGame.getGames(gameName);
        if (!getGamesMessage.isSuccessful()) {
            Console.writeError(getGamesMessage.getMessage());
            redirectAttributes.addAttribute("error", getGamesMessage.getMessage());
            return "redirect:/library/library";
        }

        model.addAttribute("games", getGamesMessage.getEntities());
        return getFragments("gameResults");
    }

    String getFragments(String fragmentsPage) {
        return "fragments :: %s".formatted(fragmentsPage);
    }
}
