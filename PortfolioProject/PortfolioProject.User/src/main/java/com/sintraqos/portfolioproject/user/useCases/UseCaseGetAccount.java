package com.sintraqos.portfolioproject.user.useCases;

import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import com.sintraqos.portfolioproject.user.service.UserService;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.DTO.UserLibraryDTO;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        logger.debug("Created user successfully");
        return new UserMessage(new UserDTO(userEntity, new UserLibraryDTO(gameList)), userEntity, "Account data retrieved");
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
            String message = "Account found with ID";
            logger.debug(message);

            return new UserMessage(userEntity, message);
        }
        // Otherwise return the message
        else {
            String message = Errors.FIND_ACCOUNT_ID_FAILED.formatted(accountID);
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
            String message = "Account found with username";
            logger.debug(message);

            return new UserMessage(accounts, message);
        } else {
            String message = Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username);
            logger.debug(message);

            return new UserMessage(Errors.FIND_ACCOUNT_NAME_FAILED.formatted(username));
        }
    }
}
