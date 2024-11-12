package com.sintraqos.portfolioproject.User;

import com.sintraqos.portfolioproject.Statics.Console;

/**
 * Use for displaying account data
 */
public class UserView {
    static UserView instance;

    public static UserView getInstance() {
        if (instance == null) {
            instance = new UserView();
            instance.onNewInstance();
        }

        return instance;
    }

    protected void onNewInstance() {
        Console.writeLine("Created new instance of AccountView");
    }
}
