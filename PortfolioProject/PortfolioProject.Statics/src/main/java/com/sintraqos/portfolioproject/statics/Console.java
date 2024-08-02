package com.sintraqos.portfolioproject.statics;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private Console() {
        throw new IllegalStateException("Utility class");
    }

    // Writers
    public static void writeLine() {
        writeLine("####################################################");
    }

    public static void writeLine(String consoleText) {
        Functions.writeLog(getTime() + Functions.capitalize(Functions.punctuate(consoleText)));
        System.out.println(getTime() + Functions.capitalize(Functions.punctuate(consoleText)));
    }

    public static void writeHeader(String consoleText) {
        Functions.writeLog(String.format("« %s »", Functions.toTitleCase(consoleText)));
        System.out.println(String.format("« %s »", Functions.toTitleCase(consoleText)));
    }

    static String getTime() {
        return String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date()));
    }
}
