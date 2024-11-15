package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Entities.UserLibraryEntity;
import com.sintraqos.portfolioproject.Repositories.UserLibraryRepository;
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

    private UserLibraryRepository userLibraryRepository;

    @Autowired
    public UserLibraryController(UserLibraryRepository userLibraryRepository){
        this.userLibraryRepository = userLibraryRepository;
    }

    /**
     * Add new game to library
     *
     * @param userDTO the new account to be added
     */
    @PostMapping("/api/userLibraries/addGame")
    public ResponseEntity<UserLibraryEntity> addGame(@RequestBody UserDTO userDTO, GameDTO gameDTO) {
        UserLibraryEntity accountLibrary = new UserLibraryEntity(userDTO.getAccountID(), gameDTO);
        UserLibraryEntity savedAccount = userLibraryRepository.save(accountLibrary);

        return ResponseEntity.ok(savedAccount);
    }
}
