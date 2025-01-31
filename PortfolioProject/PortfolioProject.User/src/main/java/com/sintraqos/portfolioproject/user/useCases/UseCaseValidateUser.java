package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UseCaseValidateUser {

    private final UserRepository userRepository;
    private final SettingsHandler settingsHandler;
    private final CensorService censorService;

    @Autowired
    public UseCaseValidateUser(UserRepository userRepository,
                               SettingsHandler settingsHandler,
                               CensorService censorService) {
        this.userRepository = userRepository;
        this.settingsHandler = settingsHandler;
        this.censorService = censorService;
    }

    public UserMessage validateUser(String username, String eMail, String password) {
        // Check if the username is a valid length
        int usernameLength = username.length();

        // Username is too short
        if (usernameLength < settingsHandler.getMessageMinLength()) {
            return new UserMessage(Errors.USERNAME_INVALID_LENGTH_SHORT.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getUsernameMaxLength()));
        }
        // Username is too long
        if (usernameLength > settingsHandler.getUsernameMaxLength()) {
            return new UserMessage(Errors.USERNAME_INVALID_LENGTH_LONG.formatted(settingsHandler.getMessageMinLength(), settingsHandler.getUsernameMaxLength()));
        }

        // Check if the username contains a banned word
        if (censorService.containsBannedWord(username)) {
            return new UserMessage(Errors.USERNAME_CONTAINS_BANNED_WORD);
        }

        // Check if an account with the given username already exists
        if (userRepository.findByUsername(username) != null) {
            return new UserMessage(Errors.USERNAME_ALREADY_IN_USE.formatted(username));
        }

        // Check if an account with the given E-Mail address already exists
        if (userRepository.findByEmail(eMail) != null) {
            return new UserMessage(Errors.EMAIL_ALREADY_IN_USE.formatted(eMail));
        }

        // Check if the given password is valid

        return new UserMessage(true, "User valid");
    }
}
