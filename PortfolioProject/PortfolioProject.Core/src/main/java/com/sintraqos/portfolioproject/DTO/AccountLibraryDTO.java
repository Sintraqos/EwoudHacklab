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
    private ArrayList<GameDTO> gameLibrary;

    public AccountLibraryDTO(AccountLibrary accountLibrary) {
        this.gameLibrary = accountLibrary.getGameLibrary();
    }
}
