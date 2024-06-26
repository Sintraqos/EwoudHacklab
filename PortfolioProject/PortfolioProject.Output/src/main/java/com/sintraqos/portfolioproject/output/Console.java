package com.sintraqos.portfolioproject.output;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {

    public static void StringOutput(String outputText) {
        WriteTime();
        System.out.println(outputText);
    }

    public static void StringOutput() {
        StringOutput("##########################");
    }

    static void WriteTime() {
        System.out.print(String.format("[%s] - ", new SimpleDateFormat("HH:mm").format(new Date())));
    }

    public static void StringTitleOutput(String consoleText) {
        System.out.println(String.format("« %s »", consoleText));
    }
}
