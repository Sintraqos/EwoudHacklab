package com.sintraqos.portfolioproject.Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private Console() {
        throw new IllegalStateException("Utility class");
    }

    public static void writeLine(String consoleText) {
        // Get the current time in the format of: '[12:34] - '
        String currentTime = String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date()));
        // Write the text to the log file
        writeLog(currentTime + consoleText);
        // And write it out to the console
        System.out.println(currentTime + consoleText);
    }

    //region Write to log file

    static boolean firstLog = true;
    static String filePath = "Log.txt";

    static void writeLog(String consoleText) {
        // Check if there has been a previous log
        if (firstLog) {
            createLogFile();
        }

        try {
            //the true will append the new data, keeping the previous text
            FileWriter fw = new FileWriter(filePath, true);
            //appends the string to the file
            fw.write(consoleText + System.lineSeparator());
            // Close the writer
            fw.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    static void createLogFile() {
        firstLog = false;
        try {
            // Create a new file at the given path
            PrintWriter pw = new PrintWriter(filePath);
            // Close the writer
            pw.close();
        } catch (IOException ex) {
            throw new RuntimeException("Failed to create log file", ex);
        }
    }

    //endregion
}
