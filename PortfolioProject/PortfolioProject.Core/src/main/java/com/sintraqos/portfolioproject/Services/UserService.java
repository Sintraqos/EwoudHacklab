package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.DTO.*;
import com.sintraqos.portfolioproject.Entities.*;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Messages.*;
import com.sintraqos.portfolioproject.Repositories.*;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Enums;
import com.sintraqos.portfolioproject.Statics.Errors;
import org.springframework.security.core.userdetails.*;
import com.sintraqos.portfolioproject.User.UserLibrary;
import com.sintraqos.portfolioproject.User.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService  implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLibraryService userLibraryService;
    @Autowired
    private UserLibraryRepository userLibraryRepository;
    @Autowired
    private GameRepository gameRepository;

    /**
     * Create a new account without a specified role
     *
     * @param username the userName of the account
     * @param eMail    the e-mail address of the account
     * @param password the password of the account
     */
    public UserMessage createAccount(String username, String eMail, String password) {
        return createAccount(username, eMail, password, Enums.Role.USER);
    }

    /**
     * Create a new account with specified role
     *
     * @param username the userName of the account
     * @param eMail    the e-mail address of the account
     * @param password the password of the account
     * @param role     the role of the account
     */
    public UserMessage createAccount(String username, String eMail, String password, Enums.Role role) {
        // Check if an account already exists
        if (userRepository.findByUsername(username) != null) {
            return new UserMessage(Errors.USERNAME_ALREADY_IN_USE.formatted(username));
        }

        // Create and save the new user
        UserEntity userEntity = new UserEntity(username, eMail, password, role);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setEnabled(true);
        userRepository.save(userEntity);

        // Cast the accountEntity to an AccountDTO object for transfer
        return new UserMessage(userEntity, "Created new account: '%s'".formatted(username));
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
            return new UserMessage(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
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
        return new Message(passwordEncoder.matches(givenPassword, storedPassword), Errors.PASSWORD_MISMATCH);
    }

    /**
     * Find an account using an ID
     *
     * @param accountID the ID of the account
     */
    public UserMessage getAccount(int accountID) {
        // Get the account
        UserEntity userEntity = userRepository.findByAccountID(accountID);

        // If the account was found return the retrieved account
        if (userEntity != null) {
            // Cast the accountEntity to an AccountDTO object for transfer
            return new UserMessage(userEntity, "Account found");
        }
        // Otherwise return the message
        else {
            return new UserMessage(Errors.FIND_GAME_ID_FAILED.formatted(accountID));
        }
    }

    /**
     * Find an account using a username
     *
     * @param username the ID of the account
     */
    public UserMessage getAccount(String username) {
        // Get the account
        UserEntity userEntity = userRepository.findByUsername(username);

        // Create the library of the user
        ArrayList<GameDTO> gameList = new ArrayList<>();
        for (UserLibraryEntity userLibraryEntity : userLibraryRepository.findByAccountID(userEntity.getAccountID())) {
            gameList.add(new GameDTO(userLibraryEntity, gameRepository.findByGameID(userLibraryEntity.getGameID())));
        }

        // Return the found user
        return new UserMessage(new UserDTO(userEntity, new UserLibraryDTO(gameList)),userEntity, "Account data retrieved");
    }

    /**
     * Set the role of an account
     *
     * @param accountID the ID of the account
     * @param role      the role which needs to be assigned to the account
     */
    public Message setAccountRole(int accountID, String password, int newRoleAccountID, Enums.Role role) {
        // Retrieve the user from the database
        UserMessage userMessage = getAccount(accountID);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }
        // Since the user needs to be an admin to update the roles of other users check if the user has a valid role
        if (userMessage.getUserDTO().getRole() == Enums.Role.USER) {
            return new Message("Invalid role");
        }

        // Check if the given password is valid
        Message passwordCheck = comparePassword(userMessage.getUserDTO().getPassword(), password);
        if (!passwordCheck.isSuccessful()) {
            return passwordCheck;
        }

        // Retrieve the user which role to update from the database
        userMessage = getAccount(newRoleAccountID);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }

        // Update the role inside the userRepository
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setRole(role);
        userRepository.save(user);

        return new Message("Role successfully updated to: '%s' for account with ID: %s".formatted(role, accountID));
    }

    /**
     * Ban the given account
     *
     * @param username
     */
    public Message banAccount(String username) {
        return handleBanAccount(username, true);
    }

    /**
     * Unban the given account
     *
     * @param username
     */
    public Message unbanAccount(String username) {
        return handleBanAccount(username, false);
    }

    /**
     * Handle banning / unbanning the given account
     *
     * @param username the username of the account
     * @param isBanned if the account should be banned or unbanned
     */
    Message handleBanAccount(String username, boolean isBanned){
        // Retrieve the account
        UserMessage userMessage = getAccount(username);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }

        // Set the account banned status
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setEnabled(!isBanned); // Since if the account is banned and receives a 'true' statement it should be set to 'false'
        user.setAccountNonLocked(!isBanned);
        userRepository.save(user);

        String returnMessage = "Successfully banned account: '%s'".formatted(username);
        if(!isBanned)
            returnMessage = "Successfully unbanned account: '%s'".formatted(username);

        // Return the message
        return new Message(returnMessage);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }

        return new User(userEntity);
    }

    public UserMessage getAccounts(String username) {
        List<UserEntity> accounts = userRepository.findByUsernameContaining(username);

        if (accounts != null) {
            return new UserMessage(accounts, "Accounts found");
        }
        else {
            return new UserMessage(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }
    }

    public Message changeUsername(String currentUsername, String newUsername, String password) {
        // Retrieve the account
        UserMessage userMessage = getAccount(currentUsername);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }

        Message passwordCheck = comparePassword(userMessage.getUserEntity().getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            return new Message(Errors.PASSWORD_INCORRECT);
        }

        UserEntity user = userMessage.getUserEntity();
        if(userRepository.findByUsername(newUsername) != null){
            return new Message(Errors.USERNAME_ALREADY_IN_USE);
        }

        // Return the message
        return handleUpdateAccount(user, newUsername, user.getEMail(),  user.getPassword(), user.getRole());
    }

    public Message changePassword(String userName,  String currentPassword, String newPassword) {
        // Retrieve the account
        UserMessage userMessage = getAccount(userName);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }
        // Compare the password that was given to the stored password
        Message passwordMessage = comparePassword(currentPassword, userMessage.getUserEntity().getPassword());
        if (!passwordMessage.isSuccessful()) {
            return passwordMessage;
        }

        // Get the entity from the message
        UserEntity user = userMessage.getUserEntity();

        // Return the message
        return handleUpdateAccount(user, userName, user.getEMail(), newPassword, user.getRole());
    }

    public Message changeEMail(String userName, String eMail, String password) {
        // Retrieve the account
        UserMessage userMessage = getAccount(userName);
        if (!userMessage.isSuccessful()) {
            return userMessage;
        }

        Message passwordCheck = comparePassword(userMessage.getUserEntity().getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            return new Message(Errors.PASSWORD_INCORRECT);
        }

        UserEntity user = userMessage.getUserEntity();

        // Return the message
        return handleUpdateAccount(user, userName, eMail, user.getPassword(), user.getRole());
    }

    Message handleUpdateAccount(UserEntity user, String username, String eMail, String password, Enums.Role role){
        user.setUsername(username);
        user.setEMail(eMail);
        user.setPasswordHash(password);
        user.setRole(role);
        userRepository.save(user);

        return new Message("Successfully updated account");
    }
}
