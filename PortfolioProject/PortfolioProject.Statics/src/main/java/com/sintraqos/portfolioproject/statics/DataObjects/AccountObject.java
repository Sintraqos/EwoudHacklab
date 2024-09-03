package com.sintraqos.portfolioproject.statics.DataObjects;

public class AccountObject implements java.io.Serializable  {
    String userName;
    String password;

    public String getUserName(){return userName;}

    public AccountObject(){}

    public AccountObject(String userName, String password){
        this.userName = userName;
        this.password = password;
    }
}
