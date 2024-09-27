package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Connect.ConnectionHandlerBase;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AccountController {

    ArrayList<Account> accountList;

    /**
     * Create a new AccountLibrary object with a filled in list
     *
     * @param userName the name of the account we're looking for
     */
    public Account getAccount(String userName) {
        // Loop through the list and find the first Account object with the name given. If the Account doesn't exist in the list, return null
         return accountList.stream()
                 .filter(account -> account.getUserName().equalsIgnoreCase(userName))
                 .findFirst().orElse(null);
    }

    /**
     * Create a new Account object
     *
     * @param userName the name of the new account
     * @param eMail the e-Mail of the new account
     * @param password the password of the new account
     */
    public void createAccount(String userName, String eMail, String password) {
        // Check if there already is an account with the given name
        if(getAccount(userName) != null){
            Console.writeLine("Account already exists!");
        }

        // Create new Account object
        Account account = new Account(userName, eMail, password);

        //TODO: Check if the account was successfully created inside the database
        Message createAccount = ConnectionHandlerBase.getInstance().createAccount(account);
        if(!createAccount.isSuccessful()){
            Console.writeLine("Failed to create new account: " + userName + " - Reason: " + createAccount.getMessage());
            return;
        }

        accountList.add(account);

        Console.writeLine("Created new account: " + userName);
    }
}
