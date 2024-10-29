package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.Messages.AccountEntityMessage;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Repositories.AccountRepository;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountLibraryService accountLibraryService;

    /**
     * Create a new account
     *
     * @param username the userName of the account
     * @param eMail the e-mail address of the account
     * @param password the password of the account
     */
    public AccountEntityMessage createAccount(String username, String eMail, String password) {
        // Check if an account already exists
        if (accountRepository.findByUsername(username) != null) {
            return new AccountEntityMessage("Account already exists");
        }

        // Create new Account object
        AccountEntity account = new AccountEntity(username, eMail, password);
        return new AccountEntityMessage(accountRepository.save(account), "Created new account: '%s'".formatted(username));
    }

    /**
     * Delete an account
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public Message deleteAccount(String username, String password) {
        // Check if an account already exists
        AccountEntity account = accountRepository.findByUsername(username);
        if (account == null) {
            return new AccountEntityMessage("Account doesn't exist");
        }

        // Compare the given password to the stored password
        Message passwordCheck = comparePassword(account.getPasswordHash(), password);
        if(passwordCheck.isSuccessful()){

            // Clear the stored library of the account
            accountLibraryService.deleteLibrary(account.getAccountID());

            // Delete the account from the database
            accountRepository.delete(account);
            return new Message(true, "Successfully removed account with username: '%s'".formatted(username));
        }
        else {
            return passwordCheck;
        }
    }

    Message comparePassword(String storedPassword, String givenPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return new Message(passwordEncoder.matches(givenPassword, storedPassword),"Incorrect password");
    }

    /**
     * Find an account using a username
     *
     * @param username the username of the account
     */
    public AccountEntityMessage loginAccount(String username, String password) {
        // Get the account
        AccountEntity account = accountRepository.findByUsername(username);

        if (account == null) {
            return new AccountEntityMessage("Could not find find user by username: '%s'".formatted(username));
        }

        // Compare the given password to the stored password
        Message passwordCheck = comparePassword(account.getPasswordHash(), password);
        if (passwordCheck.isSuccessful()) {
            return new AccountEntityMessage(account, "Successfully logged in");
        } else {
            return new AccountEntityMessage(passwordCheck.getMessage());
        }
    }

    /**
     * Find an account using a username
     *
     * @param accountID the ID of the account
     */
    public AccountEntityMessage getAccount(int accountID) {
        // Get the account
        AccountEntity account = accountRepository.findByAccountID(accountID);

        // If the account was found return the retrieved account
        if (account != null) {
            return new AccountEntityMessage(account, "Account found");
        }
        // Otherwise return the message
        else {
            return new AccountEntityMessage("Failed to find user by account ID: '%s' found".formatted(accountID));
        }
    }

    /**
     * Find an account using an accountID
     *
     * @param username the ID of the account
     */
    public AccountEntityMessage getAccount(String username) {
        // Get the account
        AccountEntity account = accountRepository.findByUsername(username);

        // If the account was found return the retrieved account
        if (account != null) {
            return new AccountEntityMessage(account, "Account found");
        }
        // Otherwise return the message
        else {
            return new AccountEntityMessage("Could not find find user by username: '%s'".formatted(username));
        }
    }
}