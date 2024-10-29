package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.DTO.GameDTO;
import com.sintraqos.portfolioproject.Entities.AccountLibraryEntity;
import com.sintraqos.portfolioproject.Repositories.AccountLibraryRepository;
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
@RequestMapping("/api/accountLibraries")
public class AccountLibraryController {

    @Autowired
    private AccountLibraryRepository accountLibraryRepository;

    /**
     * Add new game to library
     *
     * @param accountDTO the new account to be added
     */
    @PostMapping("/api/accountLibraries/addGame")
    public ResponseEntity<AccountLibraryEntity> addGame(@RequestBody AccountDTO accountDTO, GameDTO gameDTO) {
        AccountLibraryEntity accountLibrary = new AccountLibraryEntity(accountDTO.getAccountID(), gameDTO);
        AccountLibraryEntity savedAccount = accountLibraryRepository.save(accountLibrary);

        return ResponseEntity.ok(savedAccount);
    }
}
