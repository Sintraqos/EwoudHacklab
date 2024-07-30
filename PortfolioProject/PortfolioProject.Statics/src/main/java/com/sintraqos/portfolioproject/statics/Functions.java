package com.sintraqos.portfolioproject.statics;

import com.google.gson.Gson;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Functions {

    private Functions() {
        throw new IllegalStateException("Utility class");
    }

    //region File names

    public static String getFileNameWithoutExtension(String fileName) {
        if (fileName.indexOf(".") >= 1) {
            return fileName.substring(0, fileName.lastIndexOf("."));
        } else {
            return fileName;
        }
    }

    //endregion

    //region Get Files

    public static ResourcePaths.ResourcePathsFile readPathsFile(String pathType) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(Functions.class.getResourceAsStream(ResourcePaths.PATH_SEPARATOR + ResourcePaths.getDataPath(ResourcePaths.FILEPATH_DIRECTORY, pathType))))) {
            return new Gson().fromJson(reader, ResourcePaths.ResourcePathsFile.class);
        } catch (IOException ex) {
            throw new ExceptionHandler("Error reading paths file", ex);
        }
    }

    //endregion

    //region String format

    public static String capitalize(String string) {
        // Get the first character of the string, make that uppercase, then add the remaining text after it
        if (string.isEmpty() || string.length() == 1) {
            return string;
        } else {
            return String.format("%s%s", string.substring(0, 1).toUpperCase(), string.substring(1));
        }
    }

    public static String punctuate(String string) {
        // Check if the given string ends with punctuation, if true just return it, otherwise add a dot to the end
        if (string.matches(".*\\p{Punct}")) {
            return string;
        } else {
            return String.format("%s.", string);
        }
    }

    public static String toTitleCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringPart : string.toLowerCase().split(" ")) {
            stringBuilder.append(String.format("%s ", capitalize(stringPart)));
        }

        return stringBuilder.toString().trim(); // Add the trim to the end to remove leading and trailing spaces
    }

    //endregion

    //region Log

    static boolean firstLog = true;

    public static void writeLog(String consoleText) {
        String filePath = "Log.txt";
        if (firstLog) {
            firstLog = false;
            try {
                PrintWriter pw = new PrintWriter(filePath);
                pw.close();
            } catch (IOException ex) {
                throw new Functions.ExceptionHandler("IOException", ex);
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath, true); //the true will append the new data
            fw.write(getTime() + Functions.capitalize(Functions.punctuate(consoleText)) + System.lineSeparator());//appends the string to the file
            fw.close();
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("IOException", ex);
        }
    }

    public static String getTime() {
        return String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date()));
    }

    //endregion

    //region Classes

    // Exception
    public static class ExceptionHandler extends RuntimeException {

        // Message with cause
        public ExceptionHandler(String message, Throwable cause) {
            System.out.println(capitalize(punctuate(message + ": " + cause.getMessage())));
        }

        // Message only
        public ExceptionHandler(String message) {
            System.out.println(capitalize(punctuate(message)));
        }

    }

    //endregion
}