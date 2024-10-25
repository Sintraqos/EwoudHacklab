package com.sintraqos.portfolioproject.Controllers;

import com.sintraqos.portfolioproject.DTO.AccountDTO;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Messages.Message;
import com.sintraqos.portfolioproject.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Account Controller, use for communication between application and database
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    /**
     * Create new account
     *
     * @param accountDTO the new account to be added
     */
    @PostMapping("/api/accounts/createAccount")
    public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountEntity account = new AccountEntity(accountDTO);
        AccountEntity savedAccount = accountRepository.save(account);
        return ResponseEntity.ok(savedAccount);
    }

    /**
     * Delete an account
     *
     * @param accountDTO the account to be removed
     */
    @DeleteMapping("/api/accounts/deleteAccount")
    public Message deleteAccount(@RequestBody AccountDTO accountDTO) {

        // Check if the account exists
        if (accountRepository.findByUsername(accountDTO.getUsername()) == null) {
            return new Message("Could not find account with username: '%s'".formatted(accountDTO.getUsername()));
        }

        // Delete the account from the database
        AccountEntity account = new AccountEntity(accountDTO);
        accountRepository.delete(account);

        return new Message(true, "Successfully removed account: '%s'".formatted(accountDTO.getUsername()));
    }

    /**
     * Get all accounts
     */
    @GetMapping
    public List<AccountEntity> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Get account by ID
     *
     * @param id the accountID
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountEntity> getAccountById(@PathVariable Integer id) {
        return accountRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an account by ID
     *
     * @param id the accountID
     * @param accountDTO the new account information
     */
    @PutMapping("/{id}")
    public ResponseEntity<AccountEntity> updateAccount(@PathVariable Integer id, @RequestBody AccountDTO accountDTO) {
        return accountRepository.findById(id)
                .map(account -> {
                    account.setUsername(accountDTO.getUsername());
                    account.setEMail(accountDTO.getEMail());
                    // Ideally, hash the new password and set it
                    account.setPasswordHash(accountDTO.getPassword());
                    return ResponseEntity.ok(accountRepository.save(account));
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
        if (accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
