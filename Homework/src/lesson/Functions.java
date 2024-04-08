package lesson;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Functions {

    // Check if given string is valid using the given pattern
    public static String TryParseInfoString(String printText, Pattern textPattern)
    {
        System.out.println(printText);
        String userInput = System.console().readLine();

        if(!userInput.isEmpty() && textPattern.matcher(userInput).find())
            return userInput;
        else
        {
            System.out.println(STR."Invalid Input: \{userInput}");
            return TryParseInfoString(printText, textPattern);
        }
    }

    // Check if given string is a valid int
    public static int TryParseInfoInt(String printText)
    {
        System.out.println(printText);

        Scanner scanner = new Scanner(System.in);
        while(!scanner.hasNextInt())
            scanner.next();

        return  scanner.nextInt();
    }
}
