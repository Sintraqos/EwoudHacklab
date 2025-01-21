package com.sintraqos.portfolioproject.user.controller;

import com.sintraqos.portfolioproject.user.DAL.UserEntity;
import com.sintraqos.portfolioproject.user.DAL.UserRepository;
import com.sintraqos.portfolioproject.user.DTO.UserDTO;
import com.sintraqos.portfolioproject.user.entities.UserMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Account Controller, use for communication between application and database
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository)    {
        this.userRepository = userRepository;
    }

    /**
     * Create new account
     *
     * @param userDTO the new account to be added
     */
    @PostMapping("/api/users/register")
    public ResponseEntity<UserEntity> createAccount(@Valid @RequestBody UserDTO userDTO) {
        UserEntity account = new UserEntity(userDTO);
        UserEntity savedAccount = userRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    /**
     * Delete an account
     *
     * @param userDTO the account to be removed
     */
    @DeleteMapping("/api/users/remove")
    public UserMessage deleteAccount(@Valid @RequestBody UserDTO userDTO) {

        // Check if the account exists
        if (userRepository.findByUsername(userDTO.getUsername()) == null) {
            return new UserMessage("Could not find account with username: '%s'".formatted(userDTO.getUsername()));
        }

        // Delete the account from the database
        UserEntity account = new UserEntity(userDTO);
        userRepository.delete(account);

        return new UserMessage(true, "Successfully removed account: '%s'".formatted(userDTO.getUsername()));
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
