package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.CensorService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.shared.SettingsHandler;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UseCaseValidateUser {

    private final UserRepository userRepository;
    private final SettingsHandler settingsHandler;
    private final CensorService censorService;
    private final Logger logger;
    private final PasswordEncoder passwordEncoder;

    String specialCharRegex = "[!@#$%^&*()\\-_=+\\[\\]{}]";
    String capitalRegex = "[A-Z]";
    String eMailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$";

    @Autowired
    public UseCaseValidateUser(UserRepository userRepository,
                               SettingsHandler settingsHandler,
                               CensorService censorService,
                               Logger logger,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.settingsHandler = settingsHandler;
        this.censorService = censorService;
        this.logger = logger;
        this.passwordEncoder = passwordEncoder;
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

    public UserMessage validateUsername(String username) {
        // Check if the username is a valid length
        int usernameLength = username.length();

        logger.debug("Incoming Username: %s".formatted(username));
        logger.debug("Username Length: %s".formatted(usernameLength));
        logger.debug("Username Min Length: %s".formatted(settingsHandler.getUsernameMinLength()));
        logger.debug("Username Max Length: %s".formatted(settingsHandler.getUsernameMaxLength()));

        // Username is too short
        if (usernameLength < settingsHandler.getUsernameMinLength()) {
            String message = Errors.USERNAME_INVALID_LENGTH_SHORT.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getUsernameMaxLength());
            logger.debug(message);

            return new UserMessage(message);
        }
        // Username is too long
        if (usernameLength > settingsHandler.getUsernameMaxLength()) {
            String message = Errors.USERNAME_INVALID_LENGTH_LONG.formatted(settingsHandler.getUsernameMinLength(), settingsHandler.getUsernameMaxLength());
            logger.debug(message);

            return new UserMessage(message);
        }

        // Check if the username contains a banned word
        if (censorService.containsBannedWord(username)) {
            logger.debug(Errors.USERNAME_CONTAINS_BANNED_WORD);

            return new UserMessage(Errors.USERNAME_CONTAINS_BANNED_WORD);
        }

        // Check if an account with the given username already exists
        if (userRepository.findByUsername(username) != null) {
            String message = Errors.USERNAME_ALREADY_IN_USE.formatted(username);
            logger.debug(message);

            return new UserMessage(message);
        }

        return new UserMessage(true, "Username Validated");
    }

    public UserMessage validateEMail(String eMail){
        // Check if the email matches the pattern
        if (!Pattern.compile(eMailRegex).matcher(eMail).matches()) {
            String message = Errors.EMAIL_INVALID.formatted(eMail);
            logger.debug(message);

            return new UserMessage(message);
        }

        if (userRepository.findByEmail(eMail) != null) {
            String message = Errors.EMAIL_ALREADY_IN_USE.formatted(eMail);
            logger.debug(message);

            return new UserMessage(message);
        }

        return new UserMessage(true, "E-Mail Validated");
    }

    public UserMessage validatePassword(String password){
        // Check if the password is a valid length
        int passwordLength = password.length();

        logger.debug("Incoming Password: %s".formatted(password));
        logger.debug("Password Length: %s".formatted(passwordLength));
        logger.debug("Password Min Length: %s".formatted(settingsHandler.getPasswordMinLength()));
        logger.debug("Password Max Length: %s".formatted(settingsHandler.getPasswordMaxLength()));

        // Password is too short
        if (passwordLength < settingsHandler.getPasswordMinLength()) {
            String message = Errors.PASSWORD_INVALID_LENGTH_SHORT.formatted(settingsHandler.getPasswordMinLength(), settingsHandler.getPasswordMaxLength());
            logger.debug(message);

            return new UserMessage(message);
        }
        // Password is too long
        if (passwordLength > settingsHandler.getPasswordMaxLength()) {
            String message = Errors.PASSWORD_INVALID_LENGTH_LONG.formatted(settingsHandler.getPasswordMinLength(), settingsHandler.getPasswordMaxLength());
            logger.debug(message);

            return new UserMessage(message);
        }

        // Check to see if the password needs to have a special character, IE: *
        if(settingsHandler.isPasswordContainSpecialChar()){
            if (!Pattern.compile(specialCharRegex).matcher(password).find()) {
                logger.debug(Errors.PASSWORD_INVALID_SPECIAL_CHAR);

                return new UserMessage(Errors.PASSWORD_INVALID_SPECIAL_CHAR);
            }
        }

        // Check if the password needs to contain a capitalized letter inside
        if(settingsHandler.isPasswordContainCapital()){
            if (!Pattern.compile(capitalRegex).matcher(password).find()) {
                logger.debug(Errors.PASSWORD_INVALID_CAPITAL_CHAR);
                return new UserMessage(Errors.PASSWORD_INVALID_CAPITAL_CHAR);
            }
        }

        return new UserMessage(true, "Password Validated");
    }

    /**
     * Compare the password that was stored with the given password
     *
     * @param storedPassword the stored password from inside the database
     * @param givenPassword  the given password from the user
     */
    UserMessage comparePassword(String storedPassword, String givenPassword) {
        if (passwordEncoder.matches(givenPassword, storedPassword)) {
            logger.debug(Errors.PASSWORD_MATCH);
            return new UserMessage(true, Errors.PASSWORD_MATCH);
        } else {
            logger.debug(Errors.PASSWORD_MISMATCH);
            return new UserMessage(Errors.PASSWORD_MISMATCH);
        }
    }
}
