package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Usecase for handling the (un)banning of accounts
 */
@Getter
@Component
public class UseCaseBanAccount {
    private final UseCaseGetAccount getAccount;
    private final UserRepository userRepository;
    private final Logger logger;

    @Autowired
    public UseCaseBanAccount(UseCaseGetAccount getAccount,
                             UserRepository userRepository,
                             Logger logger) {
        this.getAccount = getAccount;
        this.userRepository = userRepository;
        this.logger = logger;
    }

    /**
     * Ban the given account
     *
     * @param username the username of the user
     */
    public UserMessage banAccount(String username) {
        logger.debug("Attempting to ban account: '%s'".formatted(username));
        return handleBanAccount(username, true);
    }

    /**
     * Unban the given account
     *
     * @param username the username of the user
     */
    public UserMessage unbanAccount(String username) {
        logger.debug("Attempting to unban account: '%s'".formatted(username));
        return handleBanAccount(username, false);
    }

    /**
     * Handle banning / unbanning the given account
     *
     * @param username the username of the account
     * @param isBanned if the account should be banned or unbanned
     */
    UserMessage handleBanAccount(String username, boolean isBanned) {
        // Retrieve the account
        UserMessage userMessage = getAccount.getAccount(username);
        if (!userMessage.isSuccessful()) {
            logger.debug(userMessage.getMessage());
            return userMessage;
        }

        // Set the account banned status
        UserEntity user = new UserEntity(userMessage.getUserDTO());
        user.setEnabled(!isBanned); // Since if the account is banned and receives a 'true' statement it should be set to 'false'
        user.setAccountNonLocked(!isBanned);
        userRepository.save(user);

        String returnMessage = "Successfully banned account: '%s'".formatted(username);
        if (!isBanned) {
            returnMessage = "Successfully unbanned account: '%s'".formatted(username);
        }
        logger.debug(returnMessage);

        // Return the message
        return new UserMessage(true, returnMessage);
    }
}
