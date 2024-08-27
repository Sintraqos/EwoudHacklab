package com.sintraqos.portfolioproject.connect.MariaDB;

public class MariaDBSettings implements java.io.Serializable  {
    private String dbAddress = "localhost";
    private int dbPort = 3306;
    private String dbName = "mydatabase";
    private String dbUser = "user";
    private String dbPassword = "password";
    private String dbRootPassword = "password";

    // Getters
    public String getDbAddress(){return dbAddress;}
    public int getDbPort(){return dbPort;}
    public String getDbFullAddress(){return String.format("%s:%s", dbAddress, dbPort);}
    public String getDbName(){return dbName;}
    public String getDbUser(){return dbUser;}
    public String getDbPassword(){return dbPassword;}
    public String getDbRootPassword(){return dbRootPassword;}

    // Setters
    public void setDbAddress(String dbAddress){this.dbAddress = dbAddress;}
    public void setDbPort(int dbPort){this.dbPort = dbPort;}
    public void setDbName(String dbName){this.dbName = dbName;}
    public void setDbUser(String dbUser){this.dbUser = dbUser;}
    public void setDbPassword(String dbPassword){this.dbPassword = dbPassword;}
    public void setDbRootPassword(String dbRootPassword){this.dbRootPassword = dbRootPassword;}

    public void setup(MariaDBSettings sqlSettings)    {
        dbAddress = sqlSettings.dbAddress;
        dbPort = sqlSettings.dbPort;
        dbName = sqlSettings.dbName;
        dbUser = sqlSettings.dbUser;
        dbPassword = sqlSettings.dbPassword;
        dbRootPassword = sqlSettings.dbRootPassword;
    }
}
