package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * UseCase of handling deleting the user library
 */
@Getter
@Component
public class UseCaseLibraryDeleteGame {

    private final UserLibraryRepository libraryRepository;

    @Autowired
    public UseCaseLibraryDeleteGame(UserLibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public UserLibraryEntityMessage deleteLibrary(int accountID) {
        // Get the list of the entries and delete them
        List<UserLibraryEntity> accountLibraryEntities = libraryRepository.findByAccountID(accountID);
        libraryRepository.deleteAll(accountLibraryEntities);

        // Return a message with the success
        return new UserLibraryEntityMessage("Removed all games from account with ID: '%s'".formatted(accountID));
    }
}
