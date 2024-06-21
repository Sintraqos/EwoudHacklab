import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnect extends ConnectBase {

    // Settings
    static String filePath;
    SQLSettings settings;

    public SQLConnect() {

        // Get current settings and apply where needed
        HandleSettings();

        // Make connection to the database
        try (Connection con = DriverManager
                .getConnection(String.format("jdbc:mysql://%s/%s", settings.getDbFullAddress(), settings.getDbName()), settings.getDbUser(), settings.getDbRootPassword())) {

            //System.out.println("Connected to the database!");

            // If the connection has been made, check if table exists, otherwise create the needed tables

            // Player
            try (Statement stmt = con.createStatement()) {
                // Create new table if it doesn't exist
                String tableSql = "CREATE TABLE IF NOT EXISTS players"
                        + "(player_ID int PRIMARY KEY AUTO_INCREMENT,"
                        + "userName varchar(128), eMail varchar(32), password varchar(32), signUpDate varchar(32))";
                stmt.execute(tableSql);
            }

            // Character
            try (Statement stmt = con.createStatement()) {
                // Create new table if it doesn't exist
                String tableSql = "CREATE TABLE IF NOT EXISTS characters"
                        + "(character_ID int PRIMARY KEY AUTO_INCREMENT, player_ID int, "
                        + "characterName varchar(32), characterClass varchar(32)))";
                stmt.execute(tableSql);
            }

            // Items
            try (Statement stmt = con.createStatement()) {
                // Create new table if it doesn't exist
                String tableSql = "CREATE TABLE IF NOT EXISTS items"
                        + "(item_ID int PRIMARY KEY AUTO_INCREMENT, "
                        + "itemName varchar(32), itemDescription varchar(64), itemType varchar(32))";
                stmt.execute(tableSql);
            }
        } catch (SQLException ex) {
            // Handle exception
            //System.out.println("SQLException: " + ex.getMessage());
            //System.out.println("SQLState: " + ex.getSQLState());
            //System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    @Override
    public void Connect() {

    }

    void HandleSettings() {

        settings = new SQLSettings();

        // filepath = the current directory the project is run from, the settings file would be the databaseSettings.json file inside
        filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "databaseSettings");

        // Create a new Gson object, this will be used to read / write the json file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Check if the settings file exists
        if (!new File(filePath).exists()) {
//            System.out.println("Couldn't find a settings file!");
            // Create a new settings file from the default databaseSettings object
            try (Writer writer = new FileWriter(filePath)) {
                settings = new SQLSettings();
                gson.toJson(settings, writer);
//                System.out.println("Created a new settings file!");
            } catch (IOException e) {
//                System.out.println("Failed to create a new settings file!");
                throw new RuntimeException(e);
            }
        }
        // Otherwise, read the JSON and apply read values
        else {
//            System.out.println("Found a settings file!");
            try (Reader reader = new FileReader(filePath)) {
                settings.setDbVariables(gson.fromJson(reader, SQLSettings.class));
//                System.out.println("Applied settings from file!");
            } catch (IOException e) {
//                System.out.println("Failed to apply settings from file!");
                throw new RuntimeException(e);
            }
        }

        // Print the current database settings out
//        System.out.println(String.format("Database Address: %s", settings.getDbAddress()));
//        System.out.println(String.format("Database Name: %s", settings.getDbName()));
//        System.out.println(String.format("Database User: %s", settings.getDbUser()));
//        System.out.println(String.format("Database Password: %s", settings.getDbPassword()));
//        System.out.println(String.format("Database Root Password: %s", settings.getDbRootPassword()));
    }
}

class SQLSettings implements java.io.Serializable{
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
    public void setDbVariables(SQLSettings sqlSettings)    {
        dbAddress = sqlSettings.dbAddress;
        dbPort = sqlSettings.dbPort;
        dbName = sqlSettings.dbName;
        dbUser = sqlSettings.dbUser;
        dbPassword = sqlSettings.dbPassword;
        dbRootPassword = sqlSettings.dbRootPassword;
    }
}