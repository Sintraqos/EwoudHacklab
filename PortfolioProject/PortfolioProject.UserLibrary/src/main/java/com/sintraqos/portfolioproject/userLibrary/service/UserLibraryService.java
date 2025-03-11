package com.sintraqos.portfolioproject.userLibrary.service;

import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.userLibrary.useCases.UseCaseLibraryDeleteGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLibraryService {
    private final UseCaseLibraryDeleteGame deleteGame;

    @Autowired
    public UserLibraryService(UseCaseLibraryDeleteGame deleteGame) {
        this.deleteGame = deleteGame;
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public UserLibraryEntityMessage deleteLibrary(int accountID) {
        return deleteGame.deleteLibrary(accountID);
    }
}
