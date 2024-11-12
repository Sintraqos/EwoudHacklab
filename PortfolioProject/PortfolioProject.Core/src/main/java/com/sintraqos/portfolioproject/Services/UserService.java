package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.Messages.UserMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Repositories.UserRepository;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLibraryService userLibraryService;

    /**
     * Create a new account
     *
     * @param username the userName of the account
     * @param eMail    the e-mail address of the account
     * @param password the password of the account
     */
    public UserMessage createAccount(String username, String eMail, String password) {
        // Check if an account already exists
        if (userRepository.findByUsername(username) != null) {
            return new UserMessage("Account with username: '%s' already exists".formatted(username));
        }

        // Save the account inside the database
        UserEntity account = userRepository.save(new UserEntity(username, eMail, password));
        // Cast the accountEntity to an AccountDTO object for transfer
        return new UserMessage(new UserDTO(account), "Created new account: '%s'".formatted(username));
    }

    /**
     * Delete an account
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public Message deleteAccount(String username, String password) {
        // Check if an account already exists
        UserEntity account = userRepository.findByUsername(username);
        if (account == null) {
            return new UserMessage("Account doesn't exist");
        }

        // Compare the given password to the stored password
        Message passwordCheck = comparePassword(account.getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {

            // Clear the stored library of the account
            userLibraryService.deleteLibrary(account.getAccountID());

            // Delete the account from the database
            userRepository.delete(account);
            return new Message(true, "Successfully removed account with username: '%s'".formatted(username));
        } else {
            return passwordCheck;
        }
    }

    /**
     * Compare the password that was stored with the given password
     *
     * @param storedPassword the stored password from inside the database
     * @param givenPassword  the given password from the user
     */
    Message comparePassword(String storedPassword, String givenPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new Message(passwordEncoder.matches(givenPassword, storedPassword), "Incorrect password");
    }

    /**
     * Find an account using a username
     *
     * @param username the username of the account
     */
    public UserMessage loginAccount(String username, String password) {
        // Get the account
        UserEntity account = userRepository.findByUsername(username);

        if (account == null) {
            return new UserMessage("Could not find find user by username: '%s'".formatted(username));
        }

        // Compare the given password to the stored password
        Message passwordCheck = comparePassword(account.getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            // Cast the accountEntity to an AccountDTO object for transfer
            return new UserMessage(new UserDTO(account), "Successfully logged in");
        } else {
            return new UserMessage(passwordCheck.getMessage());
        }
    }

    /**
     * Find an account using a username
     *
     * @param accountID the ID of the account
     */
    public UserMessage getAccount(int accountID) {
        // Get the account
        UserEntity account = userRepository.findByAccountID(accountID);

        // If the account was found return the retrieved account
        if (account != null) {
            // Cast the accountEntity to an AccountDTO object for transfer
            return new UserMessage(new UserDTO(account), "Account found");
        }
        // Otherwise return the message
        else {
            return new UserMessage("Failed to find user by account ID: '%s' found".formatted(accountID));
        }
    }

    /**
     * Find an account using an accountID
     *
     * @param username the ID of the account
     */
    public UserMessage getAccount(String username) {
        // Get the account
        UserEntity account = userRepository.findByUsername(username);

        // If the account was found return the retrieved account
        if (account != null) {
            // Cast the accountEntity to an AccountDTO object for transfer
            return new UserMessage(new UserDTO(account), "Account found");
        }
        // Otherwise return the message
        else {
            return new UserMessage("Could not find find user by username: '%s'".formatted(username));
        }
    }
}
