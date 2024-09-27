package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Statics.Console;

public class AccountModel {

    static AccountModel instance;

    public static AccountModel getInstance() {
        if (instance == null) {
            instance = new AccountModel();
            instance.onNewInstance();
        }

        return instance;
    }

    void onNewInstance(){
        Console.writeLine("Created new instance of AccountModel");
    }
}
