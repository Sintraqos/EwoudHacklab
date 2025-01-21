package com.sintraqos.portfolioproject.userLibrary.controller;

import com.sintraqos.portfolioproject.game.DTO.GameDTO;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryRepository;
import com.sintraqos.portfolioproject.userLibrary.DAL.UserLibraryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Account Controller, use for communication between application and database
 */
@RestController
@RequestMapping("/api/userLibraries")
public class UserLibraryController {

    private final UserLibraryRepository userLibraryRepository;

    @Autowired
    public UserLibraryController(UserLibraryRepository userLibraryRepository){
        this.userLibraryRepository = userLibraryRepository;
    }

    /**
     * Add new game to library
     *
     * @param userID the ID of the account to add the game to
     * @param gameDTO the DTO Object of the game to add
     */
    @PostMapping("/api/userLibraries/addGame")
    public ResponseEntity<UserLibraryEntity> addGame(@RequestBody int userID, GameDTO gameDTO) {
        UserLibraryEntity accountLibrary = new UserLibraryEntity(userID, gameDTO);
        UserLibraryEntity savedAccount = userLibraryRepository.save(accountLibrary);

        return ResponseEntity.ok(savedAccount);
    }
}
