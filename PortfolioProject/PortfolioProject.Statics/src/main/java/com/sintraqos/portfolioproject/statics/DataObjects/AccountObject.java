package com.sintraqos.portfolioproject.statics.DataObjects;

import java.util.ArrayList;

public class AccountObject implements java.io.Serializable  {
    String userName;
    String password;

    ArrayList<SaveObject> userSaves;

    public String getUserName(){return userName;}

    public AccountObject(){}

    public AccountObject(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.userSaves = new ArrayList<>();
    }

    public AccountObject(String userName, String password, ArrayList<SaveObject> userSaves){
        this.userName = userName;
        this.password = password;
        this.userSaves = userSaves;
    }
}
