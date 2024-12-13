package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
import com.sintraqos.portfolioproject.Messages.UserLibraryEntityMessage;
import com.sintraqos.portfolioproject.Repositories.UserLibraryRepository;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import com.sintraqos.portfolioproject.Statics.Errors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLibraryService {

    @Autowired
    private UserLibraryRepository userLibraryRepository;
    @Autowired
    private GameRepository gameRepository;

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
        // Check if the gameID is valid
        if (gameRepository.findByGameID(gameID) == null) {
            return new UserLibraryEntityMessage(Errors.FIND_GAME_ID_FAILED.formatted(gameID));
        }

        // Check if the account contains the game
        if (userLibraryRepository.findByAccountIDAndGameID(accountID, gameID) != null) {
            return new UserLibraryEntityMessage(Errors.ACCOUNT_CONTAINS_GAME.formatted(gameID));
        }

        UserLibraryEntity accountLibrary = new UserLibraryEntity(accountID, gameID);
        return new UserLibraryEntityMessage(userLibraryRepository.save(accountLibrary),
                "Added game with ID: '%s' to account account with ID: '%s'".formatted(gameID, accountID));
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public UserLibraryEntityMessage deleteLibrary(int accountID) {
        // Get the list of the entries and delete them
        List<UserLibraryEntity> accountLibraryEntities = userLibraryRepository.findByAccountID(accountID);
        userLibraryRepository.deleteAll(accountLibraryEntities);

        // Return a message with the success
        return new UserLibraryEntityMessage("Removed all games from account with ID: '%s'".formatted(accountID));
    }

    public UserLibraryEntityMessage getGame(int accountID, int gameID) {
        UserLibraryEntity userLibraryEntity =  userLibraryRepository.findByAccountIDAndGameID(accountID, gameID);
        return new UserLibraryEntityMessage(userLibraryEntity,"Retrieved game with ID: '%s'".formatted(gameID));
    }
}
