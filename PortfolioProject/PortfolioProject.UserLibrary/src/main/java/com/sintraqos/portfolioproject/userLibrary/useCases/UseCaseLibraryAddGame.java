package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.userLibrary.service.UserLibraryService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase of handling updating the user library
 */
@Getter
@Component
public class UseCaseLibraryAddGame {
    private final UserLibraryService userLibraryService;

    @Autowired
    public UseCaseLibraryAddGame(UserLibraryService userLibraryService) {
        this.userLibraryService = userLibraryService;
    }

    /**
     * Add a game using an ID
     *
     * @param userID   the ID of the user to add the game to
     * @param gameID the ID of the game
     */
    public UserLibraryEntityMessage addGame(int userID, int gameID) {
        return userLibraryService.addGame(userID, gameID);
    }
}
