package com.sintraqos.portfolioproject.Connect;

import com.fasterxml.jackson.databind.ser.std.SqlDateSerializer;
import com.sintraqos.portfolioproject.Account.Account;
import com.sintraqos.portfolioproject.Game.Game;
import com.sintraqos.portfolioproject.Statics.Console;
import com.sintraqos.portfolioproject.Statics.Message;
import com.sintraqos.portfolioproject.Statics.PasswordEncrypter;
import lombok.Getter;
import lombok.SneakyThrows;

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

    DatabaseSettings settings = new DatabaseSettings();

    String getConnectionURI(){
        return String.format("jdbc:mysql://%s/%s", settings.getDbFullAddress(), settings.getDbName());
    }

    //region Connection
    Connection con;
    @SneakyThrows   // Since we wish to throw an exception based on an SQLException
    @Override
    public void initializeConnection() {
        super.initializeConnection();
        settings = new DatabaseSettings();

        // Make test connection to the database
        try {
            con =  DriverManager.getConnection(getConnectionURI(), settings.getDbUser(), settings.getDbRootPassword());
            Console.writeLine("Connected to the database!");

            // When the connection has been made, try to create a new table if it doesn't exist
            createTables();

            // When the connection has been made, and all the tables are inside the database, set isConnected to true
            isConnected = true;
        } catch (SQLException ex) {
            throw new CustomSQLException(ex, "Failed to make a successful connection to the database");
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

    @SneakyThrows   // Since we wish to throw an exception based on an SQLException
    void createTables() {
        try (Statement st = con.createStatement()) {
            // Create new tables if they don't exist
            // Account:
            // - Integer (Primary Key, Unique, Auto Increment) userID : Use for identifying the corresponding account Library
            // - String (Unique) username
            // - String eMailAddress
            // - String passwordHash
            // - String passwordSalt
            // - Boolean (default false) isAdmin

            String createTableQuery;

            createTableQuery =
                    "CREATE TABLE IF NOT EXISTS `" + settings.accountTable + "` (" +        // Check if the table exists, if not execute the given query
                            "`accountID` int(11) NOT NULL AUTO_INCREMENT," +                        // Give an auto incrementing account ID
                            "`username` varchar(50) NOT NULL," +
                            "`email` varchar(50) NOT NULL," +
                            "`passwordHash` varchar(255) NOT NULL," +
                            "`passwordSalt` varchar(255) NOT NULL," +
                            "`isAdmin` tinyint(4) NOT NULL DEFAULT 0," +
                            "PRIMARY KEY (`accountID`)," +                                          // Make the accountID a primary key
                            "UNIQUE KEY `username` (`username`)" +                                  // Make the username unique
                            ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;";
            st.execute(createTableQuery);
            Console.writeLine("Table '" + settings.accountTable + "' Found!");

            // Account Library:
            // - Integer userID : Base on accountID
            // - Integer gameID : Base on gameID
            // - Time gameAcquired
            // - Time gameLastPlayed
            // - Time gamePlayTime
            createTableQuery =
                    "CREATE TABLE IF NOT EXISTS `" + settings.accountLibraryTable + "` (" + // Check if the table exists, if not execute the given query
                            "`userID` int(16) NOT NULL," +
                            "`gameID` int(16) NOT NULL," +
                            "`gameAcquired` datetime DEFAULT curdate()," +
                            "`gameLastPlayed` datetime DEFAULT NULL," +
                            "`gamePlayTime` int(16) DEFAULT NULL" +
                            ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;";
            st.execute(createTableQuery);
            Console.writeLine("Table '" + settings.accountLibraryTable + "' Found!");

            // Game:
            // - Integer (Primary Key, Auto Increment) gameID
            // - String gameName
            // - String gameDescription
            // - String gameDeveloper
            // - String gamePublisher
            createTableQuery =
                    "CREATE TABLE IF NOT EXISTS `" + settings.gamesTable + "` (" +      // Check if the table exists, if not execute the given query
                            "`gameID` int(11) NOT NULL AUTO_INCREMENT," +                       // Give an auto incrementing gameID
                            "`gameName` varchar(50) NOT NULL," +
                            "`gameDescription` varchar(250) NOT NULL," +
                            "`gameDeveloper` varchar(50) NOT NULL," +
                            "`gamePublisher` varchar(50) NOT NULL," +
                            "PRIMARY KEY (`gameID`)" +                                          // Make the gameID a primary key
                            ") ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;";
            st.execute(createTableQuery);
            Console.writeLine("Table '" + settings.gamesTable + "' Found!");
        } catch (SQLException ex) {
            throw new CustomSQLException(ex);
        }
    }

    //endregion

    //region Database Settings

    @Getter
    static class DatabaseSettings implements java.io.Serializable {
        private String dbAddress = "localhost";
        private int dbPort = 3306;
        private String dbName = "mariadb";
        private String dbUser = "mariadb";
        private String dbPassword = "mariadb";
        private String dbRootPassword = "mariadb";

        // Tables
        private final String accountTable = "accounts";
        private final String accountLibraryTable = "accountLibrary";
        private final String gamesTable = "games";

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

    @SneakyThrows   // Since we wish to throw an exception based on an SQLException
    @Override
    public Message createAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        //Check if the given username is available
        try (Statement st = con.createStatement()) {
            // Create the query
            String nameCheckQuery = "SELECT * FROM " + settings.accountTable + " WHERE username='" + account.getUserName() + "';";

            ResultSet rs = st.executeQuery(nameCheckQuery);
            // If the account already exists
            if (rs.next()) {
                return new Message(false, "Username already in use");
            }
        } catch (SQLException ex) {
            throw new CustomSQLException(ex);
        }

        // Create the query
        String accountInsertQuery = "INSERT INTO " + settings.accountTable + "(username, email, passwordSalt, passwordHash) VALUES (?,?,?,?);";

        try (PreparedStatement st = con.prepareStatement(accountInsertQuery)) {
            // Set the st
            st.setString(1, account.getUserName());  // Username
            st.setString(2, account.getEMail());     // E-Mail
            // Generate password with custom salt
            String[] password = PasswordEncrypter.encryptPassword(account.getPassword());
            st.setString(3, password[0]);  // Password Hash
            st.setString(4, password[1]);  // Password Salt

            // Finally execute the made statement
            st.execute();
        }
        catch (CustomSQLException ex){
            return new Message(false, "Failed add new user to the database");
        }

        // If created successfully return a new message back to the controller
        return super.createAccount(account);
    }

    @SneakyThrows   // Since we wish to throw an exception based on an SQLException
    @Override
    public Message loginAccount(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        // Send a message to the database, and try to get an account with the given name
        try (Statement st = con.createStatement()) {

            // Create the query to grab all the needed information from the database
            String nameCheckQuery = "SELECT accountID, username, passwordHash, passwordSalt FROM " + settings.accountTable + " WHERE username='" + account.getUserName() + "';";
            ResultSet rs = st.executeQuery(nameCheckQuery);
            // If the account exists inside the database
            if (rs.next()) {
                // Check if the password that was given was the same as was stored
                if (PasswordEncrypter.verifyPassword(account.getPassword(), rs.getString("passwordHash"), rs.getString("passwordSalt"))) {
                    return super.loginAccount(account);
                } else {
                    return new Message(false, "Password incorrect");
                }
            } else {
                Console.writeLine("Account could not be found!");
                return new Message(false, "Account could not be found");
            }

        } catch (SQLException ex) {
            throw new CustomSQLException(ex);
        }
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

    @SneakyThrows   // Since we wish to throw an exception based on an SQLException
    @Override
    public Message updateAccountLibrary(Account account) {
        // Check if there is an active  connection
        if (!getIsConnected()) {
            return new Message(false, "No active connection");
        }

        // Check if the account exists in the library
        try (Statement st = con.createStatement()) {
            // Create query
            String nameCheckQuery = "SELECT accountID, username FROM " + settings.accountTable + " WHERE username='" + account.getUserName() + "';";
            ResultSet rs = st.executeQuery(nameCheckQuery);
            // If the account exists inside the database
            if (!rs.next()) {
                return new Message(false, "Account could not be found");
            }
        } catch (SQLException ex) {
            throw new CustomSQLException(ex);
        }

        // Then loop trough each game inside the library
        //String addGameQuery = "INSERT INTO `" + settings.accountLibraryTable + "` (`userID`, `gameID`, `gameLastPlayed`, `gamePlayTime`) values (?, ?, ?, ?) ON DUPLICATE KEY UPDATE `gameLastPlayed`,`gamePlayTime`";
        String addGameQuery = "INSERT INTO `" + settings.accountLibraryTable + "` (`userID`, `gameID`) values (?, ?)";
        try (PreparedStatement st = con.prepareStatement(addGameQuery)) {
            for (Game game : account.getAccountLibrary().getGameLibrary()) {
                Console.writeLine("Current Game: " + game.getGameName());
                //TODO: Check if the game exists inside the library, if true overwrite the current stuff stored, otherwise add it

                // Set the st
                st.setInt(1, account.getAccountID());                       // Account ID
                st.setInt(2, game.getGameID());                             // Game ID
                //st.setDate(4, (Date) game.getGameLastPlayed());             // Game Last Played
                //st.setInt(5, game.getGamePlayTime().getTotalMinutes());     // Game Play Time

                // Finally execute the made statement
                st.addBatch();
            }
            st.executeBatch();
        } catch (CustomSQLException ex) {
            return new Message(false, "Failed add new user to the database");
        }

        return super.updateAccountLibrary(account);
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

    //region Exceptions

    public static class CustomSQLException extends SQLException {
        public CustomSQLException(SQLException ex) {
            super(ex.getMessage());

            Console.writeLine("SQLException: " + ex.getMessage());
            Console.writeLine("SQLState: " + ex.getSQLState());
            Console.writeLine("Error Code: " + ex.getErrorCode());
        }

        public CustomSQLException(SQLException ex, String message) {
            super(ex.getMessage());

            Console.writeLine("Exception Message: " + message);
            Console.writeLine("SQL Exception: " + ex.getMessage());
            Console.writeLine("SQL State: " + ex.getSQLState());
            Console.writeLine("Error Code: " + ex.getErrorCode());
        }
    }

    //endregion
}
