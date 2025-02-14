package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.game.service.GameService;
import com.sintraqos.portfolioproject.shared.Errors;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
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
    private final UserLibraryRepository libraryRepository;
    private final GameService gameService;

    @Autowired
    public UseCaseLibraryAddGame(UserLibraryRepository libraryRepository,
                                 GameService gameService) {
        this.libraryRepository = libraryRepository;
        this.gameService = gameService;
    }

    /**
     * Add a game using an ID
     *
     * @param accountID the ID of the user to add the game to
     * @param gameID    the ID of the game
     */
    public UserLibraryEntityMessage addGame(int accountID, int gameID) {
        // Check if the gameID is valid
        if (!gameService.getGame(gameID).isSuccessful()) {
            return new UserLibraryEntityMessage(Errors.FIND_GAME_ID_FAILED.formatted(gameID));
        }

        // Check if the account contains the game
        if (libraryRepository.findByAccountIDAndGameID(accountID, gameID) != null) {
            return new UserLibraryEntityMessage(Errors.ACCOUNT_CONTAINS_GAME.formatted(gameID));
        }

        UserLibraryEntity accountLibrary = new UserLibraryEntity(accountID, gameID);
        return new UserLibraryEntityMessage(libraryRepository.save(accountLibrary),
                "Added game with ID: '%s' to account account with ID: '%s'".formatted(gameID, accountID));
    }
}
