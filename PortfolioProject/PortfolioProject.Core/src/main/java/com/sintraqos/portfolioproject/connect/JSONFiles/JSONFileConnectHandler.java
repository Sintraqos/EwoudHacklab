package com.sintraqos.portfolioproject.connect.JSONFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sintraqos.portfolioproject.connect.ConnectHandler;
import com.sintraqos.portfolioproject.dialogue.DialogueTree;
import com.sintraqos.portfolioproject.statics.Console;
import com.sintraqos.portfolioproject.statics.DataObjects.ItemObject;
import com.sintraqos.portfolioproject.statics.Functions;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class JSONFileConnectHandler  extends ConnectHandler {

    String basePath;
    String itemPath;

    @Override
    public void initializeConnection() {
        Console.writeHeader("Setup JSON Files Connection");
        basePath = "JsonFiles";

        //TODO: Check if directory of save data exists, if not, create new directories
        String directoryPath = String.format("%s/%s", System.getProperty("user.dir"), basePath);
        createDirectory(directoryPath);
        Console.writeLine(directoryPath);

        // Create Player Save Directory
        createDirectory(directoryPath + "/Save");
        // Create Item Directory
        itemPath = basePath + "/Items/";
        createDirectory(String.format("%s%s", System.getProperty("user.dir"), itemPath));
        Console.writeLine(itemPath);
        // Create Companion Directory
        createDirectory(directoryPath + "/Companions");

        getItemObjects();

//        String dialogueFilePath = ResourcePaths.getResourceFilepathDialogueDirectory(locationPath, fileName);
//
//        try (Writer writer = new FileWriter(dialogueFilePath)) {
//            gson.toJson(newDialogueTree, writer);
//
//            // Check if filePaths contains key, otherwise create new one
//            filePaths.computeIfAbsent(locationPath, _ -> new ArrayList<>());
//            filePaths.get(locationPath).add(ResourcePaths.getDataPath(locationPath,fileName));
//
//        } catch (IOException ex) {
//            throw new Functions.ExceptionHandler("Failed to create new dialogue file", ex);
//        }
    }

    static void createDirectory(String directoryPath) {
        if (!new File(directoryPath).mkdirs() && !new File(directoryPath).exists()) {
            throw new Functions.ExceptionHandler("Failed to create new directory: " + directoryPath);
        }
    }

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
        ArrayList<ItemObject> itemObjects = new ArrayList<>();

//        for (File file : new File(itemPath).listFiles()) {
//            itemObjects.add(getItemObject(file.getPath()));
//        }

        return itemObjects;
    }

    ItemObject getItemObject(String itemName) {
        Console.writeLine(itemName);
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(getClass().getResourceAsStream(itemName)))) {

            ItemObject itemObject = new Gson().fromJson(reader, ItemObject.class);
            Console.writeLine("Loaded in item: " + itemObject.getItemName());

            return itemObject;
        } catch (IOException e) {
            throw new Functions.ExceptionHandler("Failed to load in item from file: " + itemPath, e);
        }
    }
}
