package com.sintraqos.portfolioproject.user.service;

import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.entities.User;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.user.useCases.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements UserDetailsService {
    private final UseCaseDeleteAccount deleteAccount;
    private final UseCaseBanAccount banAccount;
    private final UseCaseGetAccount getAccount;
    private final UseCaseRegisterAccount registerAccount;
    private final UseCaseUpdateAccount updateAccount;

    @Autowired
    public UserService(UseCaseDeleteAccount deleteAccount,
                       UseCaseBanAccount banAccount,
                       UseCaseGetAccount getAccount,
                       UseCaseRegisterAccount registerAccount,
                       UseCaseUpdateAccount updateAccount) {
        this.banAccount = banAccount;
        this.deleteAccount = deleteAccount;
        this.getAccount = getAccount;
        this.registerAccount = registerAccount;
        this.updateAccount = updateAccount;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = getAccount.getAccount(username).getUserEntity();
        if (userEntity == null) {
            throw new UsernameNotFoundException(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }

        // Account is banned, so no login should occur
        if (!userEntity.isAccountNonLocked() || !userEntity.isEnabled()) {
            throw new BadCredentialsException(Errors.ACCOUNT_BANNED.formatted(username));
        }

        return new User(userEntity);
    }

    /**
     * Create a new account without a specified role
     *
     * @param username the userName of the account
     * @param eMail    the e-mail address of the account
     * @param password the password of the account
     */
    public UserMessage registerAccount(String username, String eMail, String password) {
        return registerAccount(username, eMail, password, Enums.Role.USER);
    }

    /**
     * Create a new account with specified role
     *
     * @param username the userName of the account
     * @param eMail    the e-mail address of the account
     * @param password the password of the account
     * @param role     the role of the account
     */
    public UserMessage registerAccount(String username, String eMail, String password, Enums.Role role) {
        UserMessage message = registerAccount.registerAccount(username, eMail, password);
        if (!message.isSuccessful()) {
            return new UserMessage("Failed to create user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        } else {
            return message;
        }
    }

    /**
     * Delete an account
     *
     * @param username the userName of the account
     * @param password the password of the account
     */
    public UserMessage deleteAccount(String username, String password) {
        UserMessage message = deleteAccount.deleteAccount(username, password);

        if (message.isSuccessful()) {
            return message;
        } else {
            return new UserMessage("Failed to remove user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }
    }

    /**
     * Find an account using an ID
     *
     * @param accountID the ID of the account
     */
    public UserMessage getAccount(int accountID) {
        UserMessage message = getAccount.getAccount(accountID);

        if (!message.isSuccessful()) {
            return new UserMessage("Failed to retrieve user with ID: '%s', reason: '%s'".formatted(accountID, message.getMessage()));
        }

        return message;
    }

    /**
     * Find an account using a username
     *
     * @param username the ID of the account
     */
    public UserMessage getAccount(String username) {
        UserMessage message = getAccount.getAccount(username);

        if (!message.isSuccessful()) {
            return new UserMessage("Failed to retrieve user with username: '%s', reason: '%s'".formatted(username, message.getMessage()));
        }

        return message;
    }

    /**
     * Find all accounts containing the given username
     *
     * @param username the ID of the account
     */
    public UserMessage getAccounts(String username) {
        return getAccount.getAccounts(username);
    }

    /**
     * Set the role of an account
     *
     * @param accountID the ID of the account
     * @param role      the role which needs to be assigned to the account
     */
    public UserMessage setAccountRole(int accountID, String password, int newRoleAccountID, Enums.Role role) {
        return updateAccount.changeRole(accountID, password, newRoleAccountID, role);
    }

    /**
     * Ban the given account
     *
     * @param username the username of the account
     */
    public UserMessage banAccount(String username) {
        return banAccount.banAccount(username);
    }

    /**
     * Unban the given account
     *
     * @param username the username of the account
     */
    public UserMessage unbanAccount(String username) {
        return banAccount.unbanAccount(username);
    }

    public UserMessage changeUsername(String currentUsername, String newUsername, String password) {
        return updateAccount.changeUsername(currentUsername, newUsername, password);
    }

    public UserMessage changePassword(String username, String currentPassword, String newPassword) {
        return updateAccount.changePassword(username, currentPassword, newPassword);
    }

    public UserMessage changeEMail(String username, String eMail, String password) {
        return updateAccount.changeEMail(username, eMail, password);
    }
}
