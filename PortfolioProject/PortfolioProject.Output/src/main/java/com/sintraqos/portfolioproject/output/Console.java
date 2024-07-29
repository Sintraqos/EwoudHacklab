package com.sintraqos.portfolioproject.output;

import com.sintraqos.portfolioproject.statics.Functions;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListSet;

public class Console {

    private Console() {
        throw new IllegalStateException("Utility class");
    }

    // Writers

    public static void writeLine() {
        writeLine("##########################");
    }

    public static void writeLine(String consoleText) {
        Functions. writeLog(consoleText);
        System.out.println(Functions.getTime() + Functions.capitalize(Functions.punctuate(consoleText)));
    }

    public static void writeHeader(String consoleText) {
       Functions.writeLog(consoleText);
        System.out.printf("« %s »%n", Functions.toTitleCase(consoleText));
    }
}
