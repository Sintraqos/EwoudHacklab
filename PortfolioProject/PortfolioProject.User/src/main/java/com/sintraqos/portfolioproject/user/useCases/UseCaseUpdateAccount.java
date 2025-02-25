package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * UseCase of handling the updating of the stored information of a given account
 */
@Getter
@Component
public class UseCaseUpdateAccount {
    private final UserRepository userRepository;
    private final UseCaseGetAccount getAccount;
    private final UseCaseValidateUser validateUser;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger;

    @Autowired
    public UseCaseUpdateAccount(UserRepository userRepository,
                                UseCaseGetAccount getAccount,
                                UseCaseValidateUser validateUser,
                                PasswordEncoder passwordEncoder,
                                Logger logger) {
        this.userRepository = userRepository;
        this.getAccount = getAccount;
        this.validateUser = validateUser;
        this.passwordEncoder = passwordEncoder;
        this.logger = logger;
    }

    /**
     * Update the username of the given account
     *
     * @param currentUsername the current username of the user
     * @param newUsername     the new username of the user
     * @param password        the new password of the user
     */
    public UserMessage changeUsername(String currentUsername, String newUsername, String password) {
        logger.debug("Attempting to change the username of account: '%s'".formatted(currentUsername));
        // Check if the user is valid
        UserMessage validateUserMessage = validateUser.validateUsername(newUsername);
        if (!validateUserMessage.isSuccessful()) {
            logger.debug(validateUserMessage.getMessage());
            return validateUserMessage;
        }

        // Retrieve the account
        UserMessage userMessage = getAccount. getAccount(currentUsername);
        if (!userMessage.isSuccessful()) {
            logger.debug(userMessage.getMessage());
            return userMessage;
        }

        UserMessage passwordCheck = validateUser.comparePassword(userMessage.getUserEntity().getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            logger.debug(Errors.PASSWORD_INCORRECT);
            return new UserMessage(Errors.PASSWORD_INCORRECT);
        }

        UserEntity user = userMessage.getUserEntity();
        if (userRepository.findByUsername(newUsername) != null) {
            logger.debug(Errors.USERNAME_ALREADY_IN_USE);
            return new UserMessage(Errors.USERNAME_ALREADY_IN_USE);
        }

        // Return the message
        UserMessage updateAccount = handleUpdateAccount(user, newUsername, user.getEmail(), user.getPassword(), user.getRole());
        logger.debug(updateAccount.getMessage());

        return updateAccount;
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username the username of the user
     * @param eMail    the new E-Mail address of the user
     */
    public UserMessage changeEMail(String username, String eMail, String password) {
        logger.debug("Attempting to change the E-Mail of account: '%s'".formatted(username));
        // Retrieve the account
        UserMessage userMessage = getAccount.getAccount(username);
        if (!userMessage.isSuccessful()) {
            String message = userMessage.getMessage();
            logger.debug(message);

            return userMessage;
        }

        UserMessage passwordCheck = validateUser.comparePassword(userMessage.getUserEntity().getPasswordHash(), password);
        if (!passwordCheck.isSuccessful()) {
            logger.debug(Errors.PASSWORD_INCORRECT);

            return new UserMessage(Errors.PASSWORD_INCORRECT);
        }

        // Check if the user is valid
        UserMessage validateUserMessage = validateUser.validateEMail(eMail);
        if (!validateUserMessage.isSuccessful()) {
            String message = validateUserMessage.getMessage();
            logger.debug(message);

            return validateUserMessage;
        }

        UserEntity user = userMessage.getUserEntity();

        // Return the message
        UserMessage updateAccount = handleUpdateAccount(user, username, eMail, user.getPassword(), user.getRole());
        logger.debug(updateAccount.getMessage());

        return updateAccount;
    }

    /**
     * Update the E-Mail address of the given account
     *
     * @param username        the username of the user
     * @param currentPassword the current password of the user
     * @param newPassword     the new password of the user
     */
    public UserMessage changePassword(String username, String currentPassword, String newPassword) {
        logger.debug("Attempting to change the password of account: '%s'".formatted(username));

        // Retrieve the account
        UserMessage userMessage =getAccount. getAccount(username);
        if (!userMessage.isSuccessful()) {
            String message = userMessage.getMessage();
            logger.debug(message);

            return userMessage;
        }

        // Check if the user is valid
        UserMessage validateUserMessage = validateUser.validatePassword(newPassword);
        if (!validateUserMessage.isSuccessful()) {
            String message = validateUserMessage.getMessage();
            logger.debug(message);

            return validateUserMessage;
        }

        // Compare the password that was given to the stored password
        UserMessage passwordMessage =validateUser.comparePassword(userMessage.getUserEntity().getPasswordHash(), currentPassword);
        if (!passwordMessage.isSuccessful()) {
            String message = passwordMessage.getMessage();
            logger.debug(message);

            return passwordMessage;
        }

        // Get the entity from the message
        UserEntity user = userMessage.getUserEntity();

        // Return the message
        UserMessage updateAccount =  handleUpdateAccount(user, username, user.getEmail(), passwordEncoder.encode(newPassword), user.getRole());
        logger.debug(updateAccount.getMessage());

        return updateAccount;
    }

    /**
     * Set the role of an account
     *
     * @param accountID the ID of the account, needs to be an admin to update this
     * @param newRoleAccountID the ID of the account
     * @param role      the role which needs to be assigned to the account
     */
    public UserMessage changeRole(int accountID, String password, int newRoleAccountID, Enums.Role role) {
        logger.debug("Attempting to change the role of account with ID: '%s'".formatted(accountID));
        // Retrieve the user from the database
        UserMessage userMessage = getAccount.getAccount(accountID);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }

        // Since the user needs to be an admin to update the roles of other users check if the user has a valid role
        if (userMessage.getUserDTO().getRole() == Enums.Role.USER) {
            String message = "Invalid role";
            logger.debug(message);

            return new UserMessage(message);
        }

        // Check if the given password is valid
        UserMessage passwordCheck = validateUser.comparePassword(userMessage.getUserDTO().getPassword(), password);
        if (!passwordCheck.isSuccessful()) {
            logger.debug(passwordCheck.getMessage());
            return passwordCheck;
        }

        // Retrieve the user which role to update from the database
        userMessage = getAccount.getAccount(newRoleAccountID);
        if (!userMessage.isSuccessful()) {
            logger.debug(userMessage.getMessage());
            return userMessage;
        }

        // Update the role inside the userRepository
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setRole(role);
        userRepository.save(user);

        UserMessage updateMessage = new UserMessage("Role successfully updated to: '%s' for account with ID: %s".formatted(role, accountID));
        logger.debug(updateMessage.getMessage());

        return updateMessage;
    }

    UserMessage handleUpdateAccount(UserEntity user, String username, String eMail, String password, Enums.Role role) {
        user.setUsername(username);
        user.setEmail(eMail);
        user.setPasswordHash(password);
        user.setRole(role);
        userRepository.save(user);

        return new UserMessage(true, "Successfully updated account");
    }
}
