package com.sintraqos.portfolioproject.Services;

import com.sintraqos.portfolioproject.Repositories.AccountRepository;
import com.sintraqos.portfolioproject.Entities.AccountEntity;
import com.sintraqos.portfolioproject.Statics.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public AccountEntity createAccount(String username, String eMail, String password) {
        // Check if an account already exists
        if (accountRepository.findByUsername(username) != null) {
            Console.writeLine("Account already exists");
            return null;
        }

        // Create new Account object
        AccountEntity account = new AccountEntity(username, eMail, password);
        return accountRepository.save(account);
    }
}