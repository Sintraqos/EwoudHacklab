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

// Java components
import java.util.List;

/**
 * UseCase of handling deleting the user library
 */
@Getter
@Component
public class UseCaseLibraryDeleteGame {
    private final UserLibraryRepository libraryRepository;
    private final Logger logger;

    @Autowired
    public UseCaseLibraryDeleteGame(UserLibraryRepository libraryRepository,Logger logger) {
        this.libraryRepository = libraryRepository;
        this.logger = logger;
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public UserLibraryEntityMessage deleteLibrary(int accountID) {
        // Get the list of the entries and delete them
        List<UserLibraryEntity> accountLibraryEntities = libraryRepository.findByAccountID(accountID);
        if(accountLibraryEntities == null || accountLibraryEntities.isEmpty()) {
            return new UserLibraryEntityMessage(Errors.LIBRARY_CLEAR.formatted(accountID));
        }

        libraryRepository.deleteAll(accountLibraryEntities);

        // Return a message with the success
        String message ="Removed all games from account with ID: '%s'".formatted(accountID);
        logger.debug(message);

        return new UserLibraryEntityMessage(true, message);
    }
}
