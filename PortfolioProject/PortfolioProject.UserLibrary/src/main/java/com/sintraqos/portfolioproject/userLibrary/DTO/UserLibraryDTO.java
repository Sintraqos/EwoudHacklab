package com.sintraqos.portfolioproject.userLibrary.DTO;

// Project components
import com.sintraqos.portfolioproject.game.DTO.GameDTO;

// External components
import lombok.Getter;

// Java components
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
