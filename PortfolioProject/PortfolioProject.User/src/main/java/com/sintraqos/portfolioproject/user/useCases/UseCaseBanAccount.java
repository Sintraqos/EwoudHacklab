package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.entities.UserMessage;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

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
        UserEntity userEntity = new UserEntity(userMessage.getUserDTO());
        userEntity.setEnabled(!isBanned); // Since if the account is banned and receives a 'true' statement it should be set to 'false'
        userEntity.setAccountNonLocked(!isBanned);
        userEntity=   userRepository.save(userEntity);

        // Check if the save was successful
        if (userEntity.getAccountID() >= 0) {
            String message = (isBanned ? "Successfully banned account: '%s'".formatted(username) :"Successfully unbanned account: '%s'".formatted(username)) ;
            logger.debug(message);

            return new UserMessage(userEntity, message);
        } else {
            String errorMessage = "Failed to update user: '%s'".formatted(userEntity.getUsername());
            logger.debug(errorMessage);

            return new UserMessage(errorMessage);
        }
    }
}
