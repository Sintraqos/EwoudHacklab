package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.statics.Functions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    private Console() {
        throw new IllegalStateException("Utility class");
    }

    // Writers

    public static void writeLine(String consoleText) {
        System.out.println(writeTime() + Functions.capitalize(Functions.punctuate(consoleText)));
    }

    public static void writeLine() {
        writeLine("##########################");
    }

    public static void writeHeader(String consoleText) {
        System.out.printf("« %s »%n", Functions.toTitleCase(consoleText));
    }

    static String writeTime() {
        return String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date()));
    }
}
