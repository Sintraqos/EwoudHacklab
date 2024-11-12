package com.sintraqos.portfolioproject.DTO;

import com.sintraqos.portfolioproject.User.UserLibrary;
import com.sintraqos.portfolioproject.Game.Game;
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

    public UserLibraryDTO(UserLibrary userLibrary) {
        for (Game game : userLibrary.getGameLibrary()) {
            gameLibrary.add(new GameDTO(game));
        }
    }

    public UserLibraryDTO(ArrayList<GameDTO> gameLibrary) {
        this.gameLibrary.addAll(gameLibrary);
    }
}
