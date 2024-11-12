package com.sintraqos.portfolioproject.Webservice;

import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.DTO.UserLibraryDTO;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import com.sintraqos.portfolioproject.Repositories.UserLibraryRepository;
import com.sintraqos.portfolioproject.Repositories.UserRepository;
import com.sintraqos.portfolioproject.DTO.UserDTO;
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

import java.util.ArrayList;

@Controller
@RequestMapping("/")
public class WebServiceController implements WebMvcConfigurer {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLibraryRepository userLibraryRepository;
    @Autowired
    private GameRepository gameRepository;

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

        // Fetch the complete UserEntity from the database using the username
        UserEntity userEntity = userRepository.findByUsername(authentication.getName());

        // Create the library of the user
        ArrayList<GameDTO> gameList = new ArrayList<>();
        for (UserLibraryEntity userLibraryEntity : userLibraryRepository.findByAccountID(userEntity.getAccountID())) {
            gameList.add(new GameDTO(userLibraryEntity, gameRepository.findByGameID(userLibraryEntity.getGameID())));
        }

        // Create a userDTO Object for storage and transfer
        UserDTO userDTO = new UserDTO(userEntity, new UserLibraryDTO(gameList));

        // Store the UserDTO in the session
        session.setAttribute("userDTO", userDTO);

        // Pass the created UserDTO to the model to be used on the page
        model.addAttribute("user", userDTO);

        return "account";
    }

    //endregion

    //region Settings

    @GetMapping("/settings")
    public String getSettingsPage(Model model) {
        //TODO: Get current account information, request the information from the DB and fill it in inside the settings page
        return "settings";
    }

    //endregion

    //region Library

    @GetMapping("/library")
    public String getLibraryPage(@SessionAttribute("userDTO") UserDTO userDTO, Model model) {
        model.addAttribute("user", userDTO);
        return "library";
    }

    //endregion
}
