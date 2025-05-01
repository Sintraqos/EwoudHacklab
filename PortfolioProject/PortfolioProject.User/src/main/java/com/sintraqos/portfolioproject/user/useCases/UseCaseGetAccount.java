package com.sintraqos.portfolioproject.user.useCases;

// Project components
import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.*;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.statics.Enums;
import com.sintraqos.portfolioproject.userLibrary.DAL.*;
import com.sintraqos.portfolioproject.userLibrary.DTO.UserLibraryDTO;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

// Java components
import java.util.*;

/**
 * UseCase for getting accounts from the database
 */
@Getter
@Component
public class UseCaseGetAccount {
    private final UserRepository userRepository;
    private final UserLibraryRepository libraryRepository;
    private final GameRepository gameRepository;
    private final Logger logger;

    @Autowired
    public UseCaseGetAccount(UserRepository userRepository,
                             UserLibraryRepository libraryRepository,
                             GameRepository gameRepository,
                             Logger logger) {
        this.userRepository = userRepository;
        this.libraryRepository = libraryRepository;
        this.gameRepository = gameRepository;
        this.logger = logger;
    }

    /**
     * Get user using the username
     *
     * @param username the username of the user
     */
    public UserMessage getAccount(String username) {
        logger.debug("Attempting to get account with username: '%s'".formatted(username));
        // Get the account
        UserEntity userEntity = userRepository.findByUsername(username);

        if (userEntity != null) {
            // Create the library of the user
            ArrayList<GameDTO> gameList = new ArrayList<>();
            logger.debug("Creating new library for account");
            for (UserLibraryEntity userLibraryEntity : libraryRepository.findByAccountID(userEntity.getAccountID())) {
                logger.debug("Adding game with ID: '%s' to user library".formatted(userLibraryEntity.getGameID()));
                gameList.add(new GameDTO(
                        gameRepository.findByGameID(userLibraryEntity.getGameID()
                        ),
                        userLibraryEntity.getGameAcquired(),
                        userLibraryEntity.getGameLastPlayed(),
                        userLibraryEntity.getGamePlayTime()));
            }

            // Return the found user
            logger.debug("Retrieved user successfully");
            return new UserMessage(new UserDTO(userEntity, new UserLibraryDTO(gameList)), "Account data retrieved");
        } else {
            logger.debug(Errors.FIND_USER_NAME_FAILED.formatted(username));
            return new UserMessage(Errors.FIND_USER_NAME_FAILED.formatted(username));
        }
    }

    /**
     * Get user using the accountID
     *
     * @param accountID the ID of the user
     */
    public UserMessage getAccount(int accountID) {
        logger.debug("Attempting to get account with ID: '%s'".formatted(accountID));
        // Get the account
        UserEntity userEntity = userRepository.findByAccountID(accountID);

        // If the account was found return the retrieved account
        if (userEntity != null) {
            String message = "Account data retrieved";
            logger.debug(message);

            return new UserMessage(userEntity, message);
        }
        // Otherwise return the message
        else {
            String message = Errors.FIND_USER_ID_FAILED.formatted(accountID);
            logger.debug(message);

            return new UserMessage(message);
        }
    }

    /**
     * Get all users containing the username
     *
     * @param username the username of the user
     */
    public UserMessage getAccounts(String username) {
        logger.debug("Attempting to get accounts containing: '%s'".formatted(username));
        List<UserEntity> accounts = userRepository.findByUsernameContaining(username);

        if (accounts != null) {
            String message = "Accounts found containing: '%s'".formatted(username);
            logger.debug(message);

            return new UserMessage(accounts, message);
        } else {
            String message = Errors.FIND_USER_NAME_FAILED.formatted(username);
            logger.debug(message);

            return new UserMessage(Errors.FIND_USER_NAME_FAILED.formatted(username));
        }
    }

    /**
     * Get all users containing the username
     *
     * @param role the role of the users
     */
    public UserMessage getAccounts(Enums.Role role) {
        logger.debug("Attempting to get accounts with role: '%s'".formatted(role));
        List<UserEntity> accounts = userRepository.findAllByRole(role);

        if (accounts != null) {
            String message = "Accounts found containing: '%s'".formatted(role);
            logger.debug(message);

            return new UserMessage(accounts, message);
        } else {
            String message = Errors.FIND_USER_ROLE_FAILED.formatted(role);
            logger.debug(message);

            return new UserMessage(Errors.FIND_USER_ROLE_FAILED.formatted(role));
        }
    }
}
