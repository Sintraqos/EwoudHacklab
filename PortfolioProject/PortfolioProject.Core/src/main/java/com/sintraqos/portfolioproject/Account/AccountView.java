package com.sintraqos.portfolioproject.Account;

import com.sintraqos.portfolioproject.Statics.Console;

public class AccountView {

    static AccountView instance;

    public static AccountView getInstance() {
        if (instance == null) {
            instance = new AccountView();
            instance.onNewInstance();
        }

        return instance;
    }

    void onNewInstance(){
        Console.writeLine("Created new instance of AccountView");
    }
}
