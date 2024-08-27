package com.sintraqos.portfolioproject.connect.MariaDB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.Functions;

import java.io.*;

public class MariaDBConnectHandler extends ConnectHandler {

    // Settings
    static String filePath;
    MariaDBSettings settings;

    @Override
    public void initializeConnection(){
        Console.writeHeader("Setup Database Connection");

        // Get current settings and apply where needed
        handleSettings();

//        // Make connection to the database
//        try (Connection con = DriverManager
//                .getConnection(String.format("jdbc:mysql://%s/%s", settings.getDbFullAddress(), settings.getDbName()), settings.getDbUser(), settings.getDbRootPassword())) {
//
//            //System.out.println("Connected to the database!");
//
//            // If the connection has been made, check if table exists, otherwise create the needed tables
//
//            // Player
//            try (Statement stmt = con.createStatement()) {
//                // Create new table if it doesn't exist
//                String tableSql = "CREATE TABLE IF NOT EXISTS players"
//                        + "(player_ID int PRIMARY KEY AUTO_INCREMENT,"
//                        + "userName varchar(128), eMail varchar(32), password varchar(32), signUpDate varchar(32))";
//                stmt.execute(tableSql);
//            }
//
//            // Character
//            try (Statement stmt = con.createStatement()) {
//                // Create new table if it doesn't exist
//                String tableSql = "CREATE TABLE IF NOT EXISTS characters"
//                        + "(character_ID int PRIMARY KEY AUTO_INCREMENT, player_ID int, "
//                        + "characterName varchar(32), characterClass varchar(32)))";
//                stmt.execute(tableSql);
//            }
//
//            // Items
//            try (Statement stmt = con.createStatement()) {
//                // Create new table if it doesn't exist
//                String tableSql = "CREATE TABLE IF NOT EXISTS items"
//                        + "(item_ID int PRIMARY KEY AUTO_INCREMENT, "
//                        + "itemName varchar(32), itemDescription varchar(64), itemType varchar(32))";
//                stmt.execute(tableSql);
//            }
//        } catch (SQLException ex) {
//            throw new Functions.ExceptionHandler("SQLException", ex);
//        }

        Console.writeLine("Database Settings");
        Console.writeLine(String.format("Database Address: %s", settings.getDbAddress()));
        Console.writeLine(String.format("Database Name: %s", settings.getDbName()));
        Console.writeLine(String.format("Database User: %s", settings.getDbUser()));
        Console.writeLine(String.format("Database Password: %s", settings.getDbPassword()));
        Console.writeLine(String.format("Database Root Password: %s", settings.getDbRootPassword()));

        Console.writeLine("Finished Setup Database Connection");
        Console.writeLine();
    }

    @Override
    public void connect() {

    }

    void handleSettings() {

        settings = new MariaDBSettings();

        // filepath = the current directory the project is run from, the settings file would be the databaseSettings.json file inside
        filePath = String.format("%s/%s.json", System.getProperty("user.dir"), "DatabaseSettings");

        // Create a new Gson object, this will be used to read / write the json file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Check if the settings file exists
        if (!new File(filePath).exists()) {
            // Create a new settings file from the default databaseSettings object
            try (Writer writer = new FileWriter(filePath)) {
                settings = new MariaDBSettings();
                gson.toJson(settings, writer);
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to create settings file", ex);
            }
        }
        // Otherwise, read the JSON and apply read values
        else {
            try (Reader reader = new FileReader(filePath)) {
                settings.setup(gson.fromJson(reader, MariaDBSettings.class));
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("Failed to apply settings from file", ex);
            }
        }
    }
}