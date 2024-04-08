import java.util.HashMap;
import java.text.MessageFormat;


public class Main
{
    public void main(String[] args)
    {
        ChooseLesson();
    }

    //region Choose Lesson

    void ChooseLesson()
    {
        System.out.println("Enter lesson you want to open:");
        System.out.println("Lesson 01 - How Far Away Is The Lightning");
        System.out.println("Lesson 02 - Add / Remove Students");

        switch(System.console().readLine().toLowerCase())
        {
            case "intro lesson 1":
                IntroLesson01();
                break;

            case "intro lesson 2":
                IntroLesson02();
                break;

            case "intro lesson 3":
                IntroLesson03();
                break;

            case "1":
                Lesson01();
                break;

            case "2":
                Lesson02();
                break;

            default:
                ChooseLesson();
                break;
        }
    }

    //endregion

    //region Intro Lesson 01 - Check if number is even or odd

    void IntroLesson01() {
        // Create number list and check if the values are even or odd
        int[] numberList = new int[]{42, 67, 35, 89, 24, 76, 58, 93, 7, 30, 83, 46, 13, 25, 98, 53, 17, 79, 57, 8};
        // HasMap Settings
        String evenKey = "Even";
        String oddKey = "Odd";
        HashMap<String, Integer> numbers = new HashMap<String, Integer>() {{
            put(evenKey, 0);
            put(oddKey, 0);
        }};

        // Loop through the numberList
        for (int number : numberList) {
            // If _number is even
            if (number % 2 == 0)
                numbers.put(evenKey, numbers.get(evenKey) + 1);
            else
                numbers.put(oddKey, numbers.get(oddKey) + 1);
        }

        // Print the hashmap
        System.out.println(numbers);

        // Await input, then return to ChooseLesson()
        System.console().readLine();
        ChooseLesson();
    }

    //endregion

    //region Intro Lesson 02 - Gallons To Liters
    // There are 3.7854 liters in a gallon

    void IntroLesson02()
    {
        float gallons;
        float liters;

        gallons = 12;
        liters = gallons * 3.7854f;

        System.out.println(MessageFormat.format("{0} gallons is equal to: {1} liters", gallons, liters));

        // Await input, then return to ChooseLesson()
        System.console().readLine();
        ChooseLesson();
    }

    //endregion

    //region Intro Lesson 03 - Gallons To Liters - Expanded
    // There are 3.7854 liters in a gallon

    void IntroLesson03() {
        float gallons = 100;
        float liters = 0;
        int counter = 0;

        for (float currentGallon = 0; currentGallon <= gallons; currentGallon++) {
            liters = currentGallon * 3.7854f;
            System.out.println(MessageFormat.format("{0} gallons is equal to: {1} liters", currentGallon, liters));

            // Add to the counter, reset if it reaches 10
            counter++;
            if (counter >= 10) {
                System.out.println();
                counter = 0;
            }
        }

        // Await input, then return to ChooseLesson()
        System.console().readLine();
        ChooseLesson();
    }

    //endregion

    //region Lesson 01 - Lightning

    void Lesson01()
    {
        System.out.println("Lesson 01 - How Far Away Is The Lightning");

        // Speed of sound in M/s 343
        // Speed of sound in Ft/s 1100
        float distance;
        float time;

        System.out.println("Enter The Time:");

        try
        {
            time = Float.parseFloat(System.console().readLine());
        }
        catch (NumberFormatException _exception)
        {
            System.out.println("Invalid Input!");
            System.console().readLine();
            Lesson01();

            return;
        }

        System.out.println("Enter The Distance Type You Want:\nft / feet\nm / meter");
        float distanceMultiplier = 0;
        String distanceType = "";
        // Switch over the user input
        switch (System.console().readLine().toLowerCase()) {
            case "m", "meter":
                distanceMultiplier = 343;
                distanceType = "meters";
                break;

            case "feet", "ft":
                distanceMultiplier = 1100;
                distanceType = "feet";
                break;

            default:
                Lesson01();
                break;
        }

        distance = time * distanceMultiplier;

        System.out.println(MessageFormat.format("The lightning is: {0} {1} away", distance, distanceType));

        // Await input, then return to ChooseLesson()
        System.console().readLine();
        ChooseLesson();
    }

    //endregion

    //region Lesson 02 - Students

    void Lesson02()
    {
        System.out.println("Lesson 02 - Add / Remove Students");

        Lesson02_StudentFunctions(new HashMap<String, Integer>());
    }

    void Lesson02_StudentFunctions(HashMap<String, Integer> _students)
    {
        System.out.println("Enter the number of what you want to do:\nAdd Student: 1\nRemove Student: 2\nShow Students: 3");

        switch (System.console().readLine().toLowerCase())
        {
            case "1":   // Add Student
                System.out.println("Write out the student name and age, IE: John Smith 99");
                // Get the userinput
                String _addStudentUserInput = System.console().readLine();
                // Split the user input by spaces
                String[] _userInputSplit = _addStudentUserInput.split(" ");

                // If input is invalid
                if(_userInputSplit.length < 3)
                {
                    // Wait for userinput and return to the start
                    System.out.println("Invalid Input: " + _addStudentUserInput);
                    System.console().readLine();
                    Lesson02_StudentFunctions(_students);

                    return;
                }

                int _studentAge = 0;
                // Check if age is a valid number, since java doesn't have a tryParse method use a try catch
                try
                {
                    _studentAge = Integer.parseInt(_userInputSplit[2]);
                }
                catch (NumberFormatException _exception)
                {
                    System.out.println("Invalid Input: " + _addStudentUserInput);
                    System.console().readLine();
                    Lesson02_StudentFunctions(_students);

                    return;
                }

                // Otherwise add it to the hashmap
                String _studentName =  String.join(" ",_userInputSplit[0], _userInputSplit[1]);
                _students.put(_studentName, _studentAge);

                break;

            case "2":   // Remove Student
                System.out.println("Write out the student name, IE: John Smith");
                // Get userinput for name of the student
                String _removeStudentUserInput = System.console().readLine();

                // check if the hashmap contains the student name
                if(!_students.containsKey(_removeStudentUserInput))
                {
                    // if it doesn't exist in the hashmap wait for userinput and return to the start of this lesson
                    System.out.println("Invalid Student Name: " + _removeStudentUserInput);
                    System.console().readLine();
                    Lesson02_StudentFunctions(_students);
                }

                // remove the student
                _students.remove(_removeStudentUserInput);

                break;

            case "3":   // Show Students
                System.out.println(_students);
                break;

            case "return":
                ChooseLesson();
                break;

            default:
                Lesson02_StudentFunctions(_students);
                break;
        }

        Lesson02_StudentFunctions(_students);
    }

    //endregion
}
