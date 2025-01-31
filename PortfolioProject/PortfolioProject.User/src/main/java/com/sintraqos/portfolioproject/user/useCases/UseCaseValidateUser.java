package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UseCaseValidateUser {

    private final UserRepository userRepository;
    private final SettingsHandler settingsHandler;
    private final CensorService censorService;
    private final Logger logger;

    String specialCharRegex = "[!@#$%^&*()\\-_=+\\[\\]{}]";
    String capitalRegex = "[A-Z]";
    String eMailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    @Autowired
    public UseCaseValidateUser(UserRepository userRepository,
                               SettingsHandler settingsHandler,
                               CensorService censorService,
                               Logger logger) {
        this.userRepository = userRepository;
        this.settingsHandler = settingsHandler;
        this.censorService = censorService;
        this.logger = logger;
    }

    public UserMessage validateUser(String username, String eMail, String password) {
        // Validate the given username
        UserMessage validateUsername = validateUsername(username);
        if(!validateUsername.isSuccessful()){
            return validateUsername;
        }

        // Validate the given eMail address
        UserMessage validateEMail = validateEMail(eMail);
        if(!validateEMail.isSuccessful()){
            return validateEMail;
        }

        // Validate the given password
        UserMessage validatePassword = validatePassword(password);
        if(!validatePassword.isSuccessful()){
            return validatePassword;
        }

        return new UserMessage(true, "User valid");
    }

    public UserMessage validateUsername(String username){
        // Check if the username is a valid length
        int usernameLength = username.length();

        logger.info("Incoming Username: %s".formatted(username));
        logger.info("Username Length: %s".formatted(usernameLength));
        logger.info("Username Min Length: %s".formatted(settingsHandler.getUsernameMinLength()));
        logger.info("Username Max Length: %s".formatted(settingsHandler.getUsernameMaxLength()));

        // Username is too short
        if (usernameLength < settingsHandler.getUsernameMinLength()) {
            return new UserMessage(Errors.USERNAME_INVALID_LENGTH_SHORT.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getUsernameMaxLength()));
        }
        // Username is too long
        if (usernameLength > settingsHandler.getUsernameMaxLength()) {
            return new UserMessage(Errors.USERNAME_INVALID_LENGTH_LONG.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getUsernameMaxLength()));
        }

        // Check if the username contains a banned word
        if (censorService.containsBannedWord(username)) {
            return new UserMessage(Errors.USERNAME_CONTAINS_BANNED_WORD);
        }

        // Check if an account with the given username already exists
        if (userRepository.findByUsername(username) != null) {
            return new UserMessage(Errors.USERNAME_ALREADY_IN_USE.formatted(username));
        }

        return new UserMessage(true, "Username Validated");
    }

    public UserMessage validateEMail(String eMail){
        // Check if the email matches the pattern
        if (!Pattern.compile(eMailRegex).matcher(eMail).matches()) {
            return new UserMessage(Errors.EMAIL_INVALID.formatted(eMail));
        }

        if (userRepository.findByEmail(eMail) != null) {
            return new UserMessage(Errors.EMAIL_ALREADY_IN_USE.formatted(eMail));
        }

        return new UserMessage(true, "E-Mail Validated");
    }

    public UserMessage validatePassword(String password){
        // Check if the password is a valid length
        int passwordLength = password.length();

        logger.debug("Incoming Password: %s".formatted(password));
        logger.debug("Password Length: %s".formatted(passwordLength));
        logger.debug("Password Min Length: %s".formatted(settingsHandler.getUsernameMinLength()));
        logger.debug("Password Max Length: %s".formatted(settingsHandler.getUsernameMaxLength()));

        // Password is too short
        if (passwordLength < settingsHandler.getPasswordMinLength()) {
            return new UserMessage(Errors.PASSWORD_INVALID_LENGTH_SHORT.formatted(settingsHandler.getPasswordMinLength(), settingsHandler.getPasswordMaxLength()));
        }
        // Password is too long
        if (passwordLength > settingsHandler.getPasswordMaxLength()) {
            return new UserMessage(Errors.PASSWORD_INVALID_LENGTH_LONG.formatted(settingsHandler.getPasswordMinLength(), settingsHandler.getPasswordMaxLength()));
        }

        // Check to see if the password needs to have a special character, IE: *
        if(settingsHandler.isPasswordContainSpecialChar()){
            if (!Pattern.compile(specialCharRegex).matcher(password).find()) {
                return new UserMessage(Errors.PASSWORD_INVALID_SPECIAL_CHAR);
            }
        }

        // Check if the password needs to contain a capitalized letter inside
        if(settingsHandler.isPasswordContainCapital()){
            if (!Pattern.compile(capitalRegex).matcher(password).find()) {
                return new UserMessage(Errors.PASSWORD_INVALID_CAPITAL_CHAR);
            }
        }

        return new UserMessage(true, "Password Validated");
    }
}
