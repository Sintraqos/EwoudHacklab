package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Logger.Console;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AccountManager {

    ArrayList<Account> accountList;

    public Account getAccount(String userName) {
        // Loop through the list and find the first Account object with the name given. If the Account doesn't exist in the list, return null
         return accountList.stream()
                 .filter(account -> account.getUserName().equalsIgnoreCase(userName))
                 .findFirst().orElse(null);
    }

    public void createAccount(String username, String eMail, String password) {
        // Check if there already is an account with the given name
        if(getAccount(username) != null){
            Console.writeLine("Account already exists!");
        }

        // Create new Account object and add it to the list
        Account account = new Account(username, eMail, password);
        accountList.add(account);

        Console.writeLine("Created new account: " + username);
    }
}
