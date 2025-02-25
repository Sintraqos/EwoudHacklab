package com.sintraqos.portfolioproject.userLibrary.DTO;

import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.game.entities.Game;
import com.sintraqos.portfolioproject.userLibrary.entities.UserLibrary;
import lombok.Getter;

import java.util.ArrayList;
/**
 * AccountLibrary DTO, use for transfer of accountLibrary data
 */
@Getter
public class UserLibraryDTO {
    private ArrayList<GameDTO> gameLibrary = new ArrayList<>();

    public UserLibraryDTO() {
        gameLibrary = new ArrayList<>();
    }

    public UserLibraryDTO(ArrayList<GameDTO> gameLibrary) {
        this.gameLibrary.addAll(gameLibrary);
    }
}
