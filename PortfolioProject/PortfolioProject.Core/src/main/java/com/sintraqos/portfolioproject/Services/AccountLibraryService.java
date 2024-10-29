package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import com.sintraqos.portfolioproject.Messages.AccountLibraryEntityMessage;
import com.sintraqos.portfolioproject.Repositories.AccountLibraryRepository;
import com.sintraqos.portfolioproject.Repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountLibraryService {

    @Autowired
    private AccountLibraryRepository accountLibraryRepository;
    @Autowired
    private GameRepository gameRepository;

    public List<AccountLibraryEntity> getLibrary(int accountID) {
        return accountLibraryRepository.findByAccountID(accountID);
    }

    /**
     * Add a game to the library
     *
     * @param accountID the ID of the account
     * @param gameID    the ID of the game
     */
    public AccountLibraryEntityMessage addGame(int accountID, int gameID) {
        // Check if the gameID is valid
        if (gameRepository.findByGameID(gameID) == null) {
            return new AccountLibraryEntityMessage("Could not find game with ID: '%s'".formatted(gameID));
        }

        // Check if the account contains the game
        if (accountLibraryRepository.findByAccountIDAndGameID(accountID, gameID) != null) {
            return new AccountLibraryEntityMessage("Game with ID: '%s' already in account with ID: '%s'".formatted(gameID, accountID));
        }

        AccountLibraryEntity accountLibrary = new AccountLibraryEntity(accountID, gameID);
        return new AccountLibraryEntityMessage(accountLibraryRepository.save(accountLibrary),
                "Added game with ID: '%s' to account account with ID: '%s'".formatted(accountID, gameID));
    }

    /**
     * Delete the entire library of the given accountID
     *
     * @param accountID the ID of the account
     */
    public AccountLibraryEntityMessage deleteLibrary(int accountID) {
        // Get the list of the entries and delete them
        List<AccountLibraryEntity> accountLibraryEntities = accountLibraryRepository.findByAccountID(accountID);
        accountLibraryRepository.deleteAll(accountLibraryEntities);

        // Return a message with the success
        return new AccountLibraryEntityMessage("Removed all games from account with ID: '%s'".formatted(accountID));
    }

    public AccountLibraryEntityMessage getGame(int accountID, int gameID) {
        AccountLibraryEntity accountLibraryEntity =  accountLibraryRepository.findByAccountIDAndGameID(accountID, gameID);
        return new AccountLibraryEntityMessage(accountLibraryEntity,"Retrieved game with ID: '%s'".formatted(gameID));
    }
}
