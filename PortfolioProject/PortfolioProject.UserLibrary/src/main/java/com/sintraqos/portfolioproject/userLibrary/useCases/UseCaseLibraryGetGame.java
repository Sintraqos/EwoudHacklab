package com.sintraqos.portfolioproject.userLibrary.useCases;

import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibraryEntityMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * UseCase of handling getting the user library
 */
@Getter
@Component
public class UseCaseLibraryGetGame {

    private final UserLibraryRepository libraryRepository;
@Autowired
    public UseCaseLibraryGetGame(UserLibraryRepository libraryRepository){
this.libraryRepository = libraryRepository;
    }

    public UserLibraryEntityMessage getGame(int accountID, int gameID) {
        UserLibraryEntity userLibraryEntity =  libraryRepository.findByAccountIDAndGameID(accountID, gameID);
        return new UserLibraryEntityMessage(userLibraryEntity,"Retrieved game with ID: '%s'".formatted(gameID));
    }
}
