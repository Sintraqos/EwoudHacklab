package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.Account.AccountLibrary;
import com.sintraqos.portfolioproject.Game.Game;
import lombok.Getter;

import java.util.ArrayList;
/**
 * AccountLibrary DTO, use for transfer of accountLibrary data
 */
@Getter
public class AccountLibraryDTO {
    private ArrayList<GameDTO> gameLibrary = new ArrayList<>();

    public AccountLibraryDTO(AccountLibrary accountLibrary) {
        for(Game game : accountLibrary.getGameLibrary()){
            gameLibrary.add(new GameDTO(game));
        }
    }
}
