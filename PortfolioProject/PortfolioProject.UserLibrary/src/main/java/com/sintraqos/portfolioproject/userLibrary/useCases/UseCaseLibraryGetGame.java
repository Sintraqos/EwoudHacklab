package com.sintraqos.portfolioproject.userLibrary.useCases;

// Project components
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.*;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;

// Spring components
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// External components
import org.slf4j.Logger;
import lombok.Getter;

/**
 * UseCase of handling getting the user library
 */
@Getter
@Component
public class UseCaseLibraryGetGame {
    private final UserLibraryRepository libraryRepository;
    private final Logger logger;

    @Autowired
    public UseCaseLibraryGetGame(UserLibraryRepository libraryRepository, Logger logger) {
        this.libraryRepository = libraryRepository;
        this.logger = logger;
    }

    public UserLibraryEntityMessage getGame(int accountID, int gameID) {
        UserLibraryEntity userLibraryEntity = libraryRepository.findByAccountIDAndGameID(accountID, gameID);

        if(userLibraryEntity == null) {
            return new UserLibraryEntityMessage(Errors.FIND_LIBRARY_FAILED.formatted(accountID, gameID));
        }

        String message = "Retrieved game with ID: '%s'".formatted(gameID);
        logger.debug(message);

        return new UserLibraryEntityMessage(userLibraryEntity, message);
    }
}
