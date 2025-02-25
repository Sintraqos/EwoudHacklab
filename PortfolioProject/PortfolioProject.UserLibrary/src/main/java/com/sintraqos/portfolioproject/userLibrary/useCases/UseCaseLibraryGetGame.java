package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import lombok.Getter;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        String message = "Retrieved game with ID: '%s'".formatted(gameID);
        logger.debug(message);

        return new UserLibraryEntityMessage(userLibraryEntity, message);
    }
}
