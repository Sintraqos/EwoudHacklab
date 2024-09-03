package com.sintraqos.portfolioproject.connect.JSONFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.core.gamemanager.GameManager;
import com.sintraqos.portfolioproject.dialogue.DialogueTree;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.DataObjects.AccountObject;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;
import com.sintraqos.portfolioproject.statics.Functions;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class JSONFileConnectHandler  extends ConnectHandler {

    String basePath;
    String itemPath;
    String accountPath;

    @Override
    public void initializeConnection() {
        Console.writeHeader("Setup JSON files connection");
        basePath = "JsonFiles";

        //TODO: Check if directory of save data exists, if not, create new directories
        String directoryPath = String.format("%s/%s", System.getProperty("user.dir"), basePath);
        Functions.createDirectory(directoryPath);

        // Create Account Data Directory
        accountPath = basePath + "/Account/";
        Functions.createDirectory(String.format("%s/%s", System.getProperty("user.dir"), accountPath));

        // Create Player Save Directory
        Functions.createDirectory(directoryPath + "/Save");
        // Create Item Directory
        itemPath = basePath + "/Items/";
        Functions.createDirectory(String.format("%s/%s", System.getProperty("user.dir"), itemPath));
        // Create Companion Directory
        Functions.createDirectory(directoryPath + "/Companions");

        getItemObjects();

        super.initializeConnection();
    }

    //region Account

    @Override
    public void registerAccount(AccountObject accountObject) {
        String filePath = String.format("%s%s.json", accountPath, accountObject.getUserName());

        try (Writer writer = new FileWriter(filePath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(accountObject, writer);

        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to create new account file", ex);
        }
    }

    @Override
    public AccountObject getAccount(String userName, String password) {
        return new AccountObject();
    }

    //endregion

    //region Item

    @Override
    public void createItemObject(ItemObject itemObject) {
        String filePath = String.format("%s%s.json", itemPath, itemObject.getItemName());

        try (Writer writer = new FileWriter(filePath)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(itemObject, writer);

        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to create new item file", ex);
        }
    }

    @Override
    public ArrayList<ItemObject> getItemObjects() {
        if (!itemObjects.isEmpty()) return itemObjects;

        itemObjects = new ArrayList<>();

        File[] files = new File(itemPath).listFiles();
        if (files.length > 0) {
            for (File file : files) {
                itemObjects.add(getItemObject(file.getPath()));
            }
        }

        return itemObjects;
    }

    ItemObject getItemObject(String itemPath) {
        try {
            ItemObject itemObject = new Gson().fromJson(
                    new BufferedReader(
                            new FileReader(itemPath)).lines().collect(Collectors.joining()),
                    ItemObject.class);
            Console.writeLine("Loaded in item: " + itemObject.getItemName());

            return itemObject;
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("Failed to apply settings from file", ex);
        }
    }

    //endregion
}
