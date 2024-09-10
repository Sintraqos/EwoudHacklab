package com.sintraqos.portfolioproject.statics;

import java.io.*;

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

    //region Directory

    public static void createDirectory(String directoryPath) {
        // Use the File().mkdirs() to create a new directory and check if it failed, then check if the directory doesn't exist for whatever reason,
        // Throw exception when that happens, otherwise log the success
        if (!new File(directoryPath).mkdirs() && !new File(directoryPath).exists()) {
            throw new Functions.ExceptionHandler("Failed to create new directory: " + directoryPath);
        }

        Console.writeLine("Created directory at: " + directoryPath);
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

        // Check if the program should write out logs
        if (!GameSettings.getInstance().getLogActive()) {
            return;
        }

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
            fw.write(consoleText + System.lineSeparator());//appends the string to the file
            fw.close();
        } catch (IOException ex) {
            throw new Functions.ExceptionHandler("IOException", ex);
        }
    }

    //endregion

    //region Classes

    // Exception
    public static class ExceptionHandler extends RuntimeException {

        // Message with cause
        public ExceptionHandler(String message, Throwable cause) {
            Console.writeLine(capitalize(punctuate(message + ": " + cause.getMessage())));
        }

        // Message only
        public ExceptionHandler(String message) {
            Console.writeLine(capitalize(punctuate(message)));
        }

    }

    //endregion

    //region Get Alignment

    static int alignmentMaxValue = 100;

    public static Enums.alignment getCurrentAlignment(int currentAlignment) {
        int clampedValue = Math.clamp(currentAlignment, -alignmentMaxValue, alignmentMaxValue);

        return Enums.alignment.values()[clampedValue / alignmentMaxValue * Enums.alignment.values().length];
    }

    //endregion

    //region Parse

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //endregion
}