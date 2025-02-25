package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase of handling updating the user library
 */
@Getter
@Component
public class UseCaseLibraryAddGame {
    private final UserLibraryRepository libraryRepository;
    private final GameService gameService;
    private final Logger logger;

    @Autowired
    public UseCaseLibraryAddGame(UserLibraryRepository libraryRepository,
                                 GameService gameService,
                                 Logger logger) {
        this.libraryRepository = libraryRepository;
        this.gameService = gameService;
        this.logger = logger;
    }

    /**
     * Add a game using an ID
     *
     * @param accountID the ID of the user to add the game to
     * @param gameID    the ID of the game
     */
    public UserLibraryEntityMessage addGame(int accountID, int gameID) {
        logger.debug("Attempting to add new game to library of user: '%s'".formatted(accountID));

        // Check if the gameID is valid
        if (!gameService.getGame(gameID).isSuccessful()) {
            logger.debug(Errors.FIND_GAME_ID_FAILED.formatted(gameID));
            return new UserLibraryEntityMessage(Errors.FIND_GAME_ID_FAILED.formatted(gameID));
        }

        // Check if the account contains the game
        if (libraryRepository.findByAccountIDAndGameID(accountID, gameID) != null) {
            logger.debug(Errors.ACCOUNT_CONTAINS_GAME.formatted(gameID));
            return new UserLibraryEntityMessage(Errors.ACCOUNT_CONTAINS_GAME.formatted(gameID));
        }

        String message = "Added game with ID: '%s' to account account with ID: '%s'".formatted(gameID, accountID);
        logger.debug(message);

        return new UserLibraryEntityMessage(libraryRepository.save(new UserLibraryEntity(accountID, gameID)), message);
    }
}
