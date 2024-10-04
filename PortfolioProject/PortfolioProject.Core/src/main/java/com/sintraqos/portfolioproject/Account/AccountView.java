package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Statics.Console;

/**
 * Use for displaying account data
 */
public class AccountView {
    static AccountView instance;

    public static AccountView getInstance() {
        if (instance == null) {
            instance = new AccountView();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of AccountView");
    }
}
