package com.sintraqos.portfolioproject.userLibrary.service;

import com.sintraqos.portfolioproject.game.DAL.GameRepository;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.userLibrary.useCases.UseCaseLibraryAddGame;
import com.sintraqos.portfolioproject.userLibrary.useCases.UseCaseLibraryDeleteGame;
import com.sintraqos.portfolioproject.userLibrary.useCases.UseCaseLibraryGetGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLibraryService {
    private final UserLibraryRepository userLibraryRepository;
    private final GameRepository gameRepository;
    private final UseCaseLibraryAddGame addGame;
    private final UseCaseLibraryDeleteGame deleteGame;
    private final UseCaseLibraryGetGame getGame;

    @Autowired
    public UserLibraryService(UserLibraryRepository userLibraryRepository,
                              GameRepository gameRepository,
                              UseCaseLibraryAddGame addGame,
                              UseCaseLibraryDeleteGame deleteGame,
                              UseCaseLibraryGetGame getGame) {
        this.userLibraryRepository = userLibraryRepository;
        this.gameRepository = gameRepository;
        this.addGame = addGame;
        this.deleteGame = deleteGame;
        this.getGame = getGame;
    }


    public List<UserLibraryEntity> getLibrary(int accountID) {
        return userLibraryRepository.findByAccountID(accountID);
    }

    /**
     * Add a game to the library
     *
     * @param accountID the ID of the account
     * @param gameID    the ID of the game
     */
    public UserLibraryEntityMessage addGame(int accountID, int gameID) {
        return addGame.addGame(accountID, gameID);
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public UserLibraryEntityMessage deleteLibrary(int accountID) {
        return deleteGame.deleteLibrary(accountID);
    }

    public UserLibraryEntityMessage getGame(int accountID, int gameID) {
        return getGame.getGame(accountID, gameID);
    }
}
