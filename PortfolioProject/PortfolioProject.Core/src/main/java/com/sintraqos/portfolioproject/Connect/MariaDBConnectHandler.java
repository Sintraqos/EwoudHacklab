package com.sintraqos.portfolioproject.Connect;

import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;
import lombok.Getter;

import java.sql.*;
import java.util.ArrayList;

/**
 * The MariaDB form of the connectionHandler.
 * REQUIRES: Usage and running of a Maria DataBase
 */
public class MariaDBConnectHandler extends ConnectionHandler {

    static MariaDBConnectHandler instance;

    /**
     * Create a new instance of the MariaDBConnectHandler, and initialize connection of the instance
     */
    public static MariaDBConnectHandler getInstance() {
        if (instance == null) {
            instance = new MariaDBConnectHandler();
            instance.initializeConnection();
        }

        return instance;
    }

    //region Connection

    @Override
    public void initializeConnection() {
        super.initializeConnection();
        DatabaseSettings settings = new DatabaseSettings();

        // Make test connection to the database
        try (Connection con = DriverManager
                .getConnection(String.format(
                                "jdbc:mysql://%s/%s", settings.getDbFullAddress(), settings.getDbName()),
                        settings.getDbUser(),
                        settings.getDbRootPassword())) {

            Console.writeLine("Connected to the database!");

            // If the connection has been made, try to create a new table if it doesn't exist
            try (Statement stmt = con.createStatement()) {
                // Create new tables if they don't exist
                // Account:
                    // - Integer (Primary Key, Unique, Auto Incr) userID
                    // - String (Unique) username
                    // - String eMailAddress
                    // - String passwordSalt
                    // - String passwordHash
                    // - Boolean (default false) isAdmin
                stmt.execute("CREATE TABLE IF NOT EXISTS `accounts` (" +        // Check if the table exists, if not execute the given query
                                "`accountID` int(11) NOT NULL AUTO_INCREMENT," +    // Give an auto incrementing account ID
                                "`username` varchar(50) NOT NULL," +
                                "`email` varchar(50) NOT NULL," +
                                "`passwordSalt` varchar(50) NOT NULL," +
                                "`passwordHash` varchar(50) NOT NULL," +
                                "`isAdmin` tinyint(4) NOT NULL DEFAULT 0," +
                                "PRIMARY KEY (`accountID`)," +                      // Make the accountID a primary key
                                "UNIQUE KEY `username` (`username`)" +              // Make the username unique
                                ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;");
                Console.writeLine("Table 'Account' Found!");

                // Account Library:
                    // - Integer userID : Base on accountID
                    // - Integer gameID : Base on gameID
                    // - Time gameAcquired
                    // - Time gameLastPlayed
                    // - Time gamePlayTime
                stmt.execute("CREATE TABLE IF NOT EXISTS `accountLibrary` (" + // Check if the table exists, if not execute the given query
                                "`accountID` int(11) NOT NULL," +
                                "`gameID` int(11) NOT NULL," +
                                "`gameAquired` datetime DEFAULT curdate()," +
                                "`gameLastPlayed` datetime DEFAULT NULL," +
                                "`gamePlayTime` float DEFAULT NULL" +
                                ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;");
                Console.writeLine("Table 'AccountLibrary' Found!");

                // Game:
                    // - Integer (Primary Key, Auto Incr) gameID
                    // - String gameName
                    // - String gameDescription
                    // - String gameDeveloper
                    // - String gamePublisher
                stmt.execute("CREATE TABLE IF NOT EXISTS `games` (" +       // Check if the table exists, if not execute the given query
                                "`gameID` int(11) NOT NULL AUTO_INCREMENT," +   // Give an auto incrementing gameID
                                "`gameName` varchar(50) NOT NULL," +
                                "`gameDescription` varchar(250) NOT NULL," +
                                "`gameDeveloper` varchar(50) NOT NULL," +
                                "`gamePublisher` varchar(50) NOT NULL," +
                                "PRIMARY KEY (`gameID`)" +                       // Make the gameID a primary key
                                ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;");
                Console.writeLine("Table 'Game' Found!");
            }

//                // Get every employee from the table
//                String selectSql = "SELECT * FROM employees";
//                try (ResultSet resultSet = stmt.executeQuery(selectSql)) {
//                    // Print resultSet
//                    while (resultSet.next()) {
//                        System.out.println(String.format("ID: %s - Name: %s - Salary: %s - Position: %s", resultSet.getInt("emp_id"), resultSet.getString("name"), resultSet.getDouble("salary"), resultSet.getString("position")));
//                    }
//                }
//            }
        } catch (SQLException ex) {
            // Handle exception
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void startConnection() {
        super.startConnection();

        //TODO: connect to the database and check if the connection is usable

        isConnected = true;
    }

    @Override
    public void stopConnection() {
        super.stopConnection();

        isConnected = false;
    }

    //endregion

    //region Database Settings

    class DatabaseSettings implements java.io.Serializable {
        @Getter
        private String dbAddress = "localhost";
        @Getter
        private int dbPort = 3306;
        @Getter
        private String dbName = "mariadb";
        @Getter
        private String dbUser = "mariadb";
        @Getter
        private String dbPassword = "mariadb";
        @Getter
        private String dbRootPassword = "mariadb";

        // Getters
        public String getDbFullAddress() {
            return String.format("%s:%s", dbAddress, dbPort);
        }

        public void setDbVariables(DatabaseSettings databaseSettings) {
            dbAddress = databaseSettings.dbAddress;
            dbPort = databaseSettings.dbPort;
            dbName = databaseSettings.dbName;
            dbUser = databaseSettings.dbUser;
            dbPassword = databaseSettings.dbPassword;
            dbRootPassword = databaseSettings.dbRootPassword;
        }
    }

    //endregion

    //region Account

    @Override
    public Message createAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the userName isn't already taken by another account, if it is available add it to the table

        return super.createAccount(account);
    }

    @Override
    public Message getAccount(String userName, String password) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the account exists inside the database
        return super.getAccount(userName, password);
    }

    @Override
    public Message removeAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the account exists inside the database, if it does remove it from the table
        // if(!ACCOUNT_EXISTS_IN_DB){
        //      return new Message(false, "Account with userName: " + account.getUserName() + " couldn't be found!")
        // }

        return super.removeAccount(account);
    }

    @Override
    public Message updateAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the account exists inside the database, if it does update it with the new information

        return super.updateAccount(account);
    }

    @Override
    public Message updateAccountLibrary(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //TODO: Check if the accountLibrary exists inside the database, if it does update it with the new information

        return super.updateAccount(account);
    }
    //endregion

    //region Game

    @Override
    public Message addGame(Game game) {
        return super.addGame(game);
    }

    @Override
    public Message removeGame(Game game) {
        return super.removeGame(game);

    }

    @Override
    public ArrayList<Game> getGameList() {
        return super.getGameList();
    }

    @Override
    public Message updateGameList(ArrayList<Game> accountLibrary) {
        return super.updateGameList(accountLibrary);

    }

    //endregion
}
