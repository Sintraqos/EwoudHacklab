package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.UserDTO;
import com.sintraqos.portfolioproject.Entities.UserEntity;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Account Controller, use for communication between application and database
 */
@RestController
@RequestMapping("/api/accounts")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    /**
     * Create new account
     *
     * @param userDTO the new account to be added
     */
    @PostMapping("/api/accounts/createAccount")
    public ResponseEntity<UserEntity> createAccount(@RequestBody UserDTO userDTO) {
        UserEntity account = new UserEntity(userDTO);
        UserEntity savedAccount = userRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    /**
     * Delete an account
     *
     * @param userDTO the account to be removed
     */
    @DeleteMapping("/api/accounts/deleteAccount")
    public Message deleteAccount(@RequestBody UserDTO userDTO) {

        // Check if the account exists
        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            return new Message("Could not find account with username: '%s'".formatted(userDTO.getUsername()));
        }

        // Delete the account from the database
        UserEntity account = new UserEntity(userDTO);
        userRepository.delete(account);

        return new Message(true, "Successfully removed account: '%s'".formatted(userDTO.getUsername()));
    }

    /**
     * Get all accounts
     */
    @GetMapping
    public List<UserEntity> getAllAccounts() {
        return userRepository.findAll();
    }

    /**
     * Get account by ID
     *
     * @param id the accountID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getAccountById(@PathVariable Integer id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an account by ID
     *
     * @param id the accountID
     * @param userDTO the new account information
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateAccount(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
        return userRepository.findById(id)
                .map(account -> {
                    account.setUsername(userDTO.getUsername());
                    account.setEMail(userDTO.getEMail());
                    // Ideally, hash the new password and set it
                    account.setPasswordHash(userDTO.getPassword());
                    return ResponseEntity.ok(userRepository.save(account));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Delete an account by ID
     *
     * @param id the accountID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
