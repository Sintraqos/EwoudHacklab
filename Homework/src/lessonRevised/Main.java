package lessonRevised;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public void main(String[] args) {

        //region Test Logs

        Logger.Log("Test Log, no log formatting!");
        Logger.Log("beWarE, BeWARe, ThE DAUGHTER oF tHe SEa");

        Logger.Log("Test Log, punctuate, lowercase and capitalize!");
        Logger.Log("beWarE, BeWARe, ThE DAUGHTER oF tHe SEa",
                new Enums.LogStyle[]{
                        Enums.LogStyle.LOG_STYLE_punctuate,
                        Enums.LogStyle.LOG_STYLE_lowerCase,
                        Enums.LogStyle.LOG_STYLE_capitalize
                });

        Logger.Log("Test Log, punctuate and titleCase!");
        Logger.Log("beWarE, BeWARe, ThE DAUGHTER oF tHe SEa",
                new Enums.LogStyle[]{
                        Enums.LogStyle.LOG_STYLE_punctuate,
                        Enums.LogStyle.LOG_STYLE_titleCase
                });
        Logger.LogWarning("Test Log Warning!");
        Logger.LogError("Test Log Error!");

        //endregion

        // Create instance of all the managers
        StudentManager studentManager = StudentManager.getInstance();
        TeacherManager teacherManager = MentorManager.getInstance();
        MentorManager mentorManager = MentorManager.getInstance();
        ClassroomManager classroomManager = ClassroomManager.getInstance();

        studentManager.HandleStudentManager();
    }
}

//region Text Formats

class TextFormatting{

    // Input Patterns
  static   Pattern namePattern = Pattern.compile("[a-zA-Z._%+-]+\\s");
    static Pattern eMailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    static  Pattern phoneNumberPattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
    static  Pattern addressPattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");
    static  Pattern residencePattern = Pattern.compile("[a-zA-Z._%+-]");
    static  Pattern classroomNamePattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");

    // Log Formatting
    static  String logPattern = "%s";
    static  String logWarningPattern = "WARNING: %s";
    static  String logErrorPattern = "ERROR: %s";
}

//endregion

//region Log

class Logger {

    // Log
    public static void Log(String input) {
        Log(TextFormatting.logPattern, input, new Enums.LogStyle[]{Enums.LogStyle.LOG_STYLE_upperCase, Enums.LogStyle.LOG_STYLE_punctuate});
    }

    public static void Log(String input, Enums.LogStyle[] logStyles) {
        Log(TextFormatting.logPattern, input, logStyles);
    }

    // Log Warning
    public static void LogWarning(String input) {
        Log(TextFormatting.logWarningPattern, input);
    }

    public static void LogWarning(String input, Enums.LogStyle[] logStyles) {
        Log(TextFormatting.logWarningPattern, input, logStyles);
    }

    // Log Error
    public static void LogError(String input) {
        Log(TextFormatting.logErrorPattern, input);
    }

    public static void LogError(String input, Enums.LogStyle[] logStyles) {
        Log(TextFormatting.logErrorPattern, input, logStyles);
    }

    // Log Style
    static void Log(String textFormat, String input, Enums.LogStyle[] logStyles) {
        for (Enums.LogStyle logStyle : logStyles) {
            switch (logStyle) {
                case LOG_STYLE_lowerCase:
                    input = input.toLowerCase();
                    break;

                case LOG_STYLE_upperCase:
                    input = input.toUpperCase();
                    break;

                case LOG_STYLE_capitalize:
                    input = Functions.capitalize(input);
                    break;

                case LOG_STYLE_titleCase:
                    input = Functions.toTitleCase(input);
                    break;

                case LOG_STYLE_punctuate:
                    input = Functions.punctuate(input);
                    break;

                default:
                    break;
            }
        }

        Log(textFormat, input);
    }

    // Log writer
    static void Log(String textFormat, String input) {
        System.out.printf(STR."\{textFormat}%n", input);
    }
}

//endregion

//region People

class PersonMaster {
    // Variables
    String personName;
    String personEMail;
    String personPhoneNumber;
    Address personAddress;
    DateOfBirth personAge;

    // Getters
    public String getName() {
        return personName;
    }
    public int getAge() {
        return personAge.getAge();
    }
    public String getPhoneNumber() {
        return personPhoneNumber;
    }
    public String getEMail() {
        return personEMail;
    }
    public Address getAddress() {
        return personAddress;
    }

    // Builder
    class Builder {

        private String personName;
        private String personEMail;
        private String personPhoneNumber;
        private Address personAddress;
        private DateOfBirth personAge;

        public PersonMaster.Builder setPersonName(String personName) {
            this.personName = personName;

            return this;
        }

        public PersonMaster.Builder setPersonEMail(String personEMail) {
            this.personEMail = personEMail;

            return this;
        }

        public PersonMaster.Builder setPersonPhoneNumber(String personPhoneNumber) {
            this.personPhoneNumber = personPhoneNumber;

            return this;
        }

        public PersonMaster.Builder setPersonName(Address personAddress) {
            this.personAddress = personAddress;

            return this;
        }

        public PersonMaster.Builder setPersonName(DateOfBirth personAge) {
            this.personAge = personAge;

            return this;
        }
    }
}

class Student extends PersonMaster {
    // Variables
    int studentNumber;

    // Getters
    public int getStudentNumber() {
        return studentNumber;
    }

    // Setters
    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public Student(
            String studentName,
            DateOfBirth studentAge,
            String studentPhoneNumber,
            String studentEMail,
            Address studentAddress,
            int studentNumber) {
        personName = studentName;
        personAge = studentAge;
        personPhoneNumber = studentPhoneNumber;
        personEMail = studentEMail;
        personAddress = studentAddress;
        this.studentNumber = studentNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "personName=" + personName +
                ", studentNumber='" + studentNumber + '\'' +
                '}';
    }

    public void getStudentInfo()
    {
        Logger.Log(STR."""
                        Student Name:\{getName()}
                        Student Age:\{getAge()}
                        Student Phone Number:\{getPhoneNumber()}
                        Student E-Mail:\{getEMail()}
                        Student Address:\{getAddress()}
                        Student Number:\{getStudentNumber()}
                        """);
    }
}

class Teacher extends PersonMaster {
    // Variables
    int employeeNumber;

    // Getters
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    // Setters
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public  Teacher(){}

    public Teacher(
            String teacherName,
            DateOfBirth teacherAge,
            String teacherPhoneNumber,
            String teacherEMail,
            Address teacherAddress,
            int employeeNumber) {
        personName = teacherName;
        personAge = teacherAge;
        personPhoneNumber = teacherPhoneNumber;
        personEMail = teacherEMail;
        personAddress = teacherAddress;
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "personName=" + personName +
                ", employeeNumber='" + employeeNumber + '\'' +
                '}';
    }

    public void getTeacherInfo()
    {
        Logger.Log(STR."""
                        Teacher Name:\{getName()}
                        Teacher Age:\{getAge()}
                        Teacher Phone Number:\{getPhoneNumber()}
                        Teacher E-Mail:\{getEMail()}
                        Teacher Address:\{getAddress()}
                        Employee Number:\{getEmployeeNumber()}
                        """);
    }
}

class Mentor extends Teacher {
    // Variables
    Classroom mentorClass;

    // Getters
    public Classroom getMentorClass() {
        return mentorClass;
    }

    // Setters
    public void setMentorClass(Classroom mentorClass) {
        this.mentorClass = mentorClass;
    }

    public Mentor(
            String mentorName,
            DateOfBirth mentorAge,
            String mentorPhoneNumber,
            String mentorEMail,
            Address mentorAddress,
            int mentorNumber,
            Classroom mentorClass) {
        personName = mentorName;
        personAge = mentorAge;
        personPhoneNumber = mentorPhoneNumber;
        personEMail = mentorEMail;
        personAddress = mentorAddress;
        employeeNumber = mentorNumber;
        this.mentorClass = mentorClass;
    }

    public void getTeacherInfo()
    {
        Logger.Log(STR."""
                        Mentor Name:\{getName()}
                        Mentor Age:\{getAge()}
                        Mentor Phone Number:\{getPhoneNumber()}
                        Mentor E-Mail:\{getEMail()}
                        Mentor Address:\{getAddress()}
                        Employee Number:\{getEmployeeNumber()}
                        Mentor Class:\{getMentorClass()}
                        """);
    }
}

//endregion

//region Managers

class StudentManager {

    // Variables
    ArrayList<Student> studentList;
    static StudentManager instance;

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }

        return instance;
    }

    StudentManager() {
        studentList = new ArrayList<>();

        studentList.add(new Student("Student 1", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1234));
        studentList.add(new Student("Student 2", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1235));
        studentList.add(new Student("Student 3", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1236));
        studentList.add(new Student("Student 4", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1237));
        studentList.add(new Student("Student 5", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1238));
        studentList.add(new Student("Student 6", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1239));
        studentList.add(new Student("Student 7", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1240));
        studentList.add(new Student("Student 8", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1241));
        studentList.add(new Student("Student 9", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1242));
        studentList.add(new Student("Student 10", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1243));
        studentList.add(new Student("Student 11", new DateOfBirth(), "06 12341234", "student1@mail.com", new Address("SmithStreet", 1, "Smith Town", "1234AB"), 1244));
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    public void HandleStudentManager() {
        Logger.Log("""
                Student Settings:
                Enter: '1' / 'create' to make a new student
                Enter: '2' / 'remove' to remove given student
                Enter: '3' / 'list' to show all students
                Enter: '4' / 'info' to show info of given student
                """);

        String input = System.console().readLine();
        switch (input.toLowerCase()) {
            case "1", "create":
                AddStudent();
                break;

            case "2", "remove":
                RemoveStudent();
                break;

            case "3", "list":
                StudentList();
                break;

            case "4", "info":
                StudentInfo();
                break;

            default:
                Exceptions.InvalidInputException(input);
                HandleStudentManager();
                break;
        }
    }

    void AddStudent() {
        String studentName = Functions.TryParseInfoString("Enter Student Name: IE: John Smith", TextFormatting.namePattern);
        Logger.Log("Enter Student Age: IE: 16");
        DateOfBirth studentAge = new DateOfBirth();
        String studentPhoneNumber = Functions.TryParseInfoString("Enter Student Phone Number: IE: 06 12341234", TextFormatting.phoneNumberPattern);
        String studentEMail = Functions.TryParseInfoString("Enter Student E-Mail Address: IE: John@Smith.com", TextFormatting.eMailPattern);
        Logger.Log("Enter Student Address: IE: SmithStreet a05");
        Address studentAddress = new Address("", 1, "", "");
        String studentResidence = Functions.TryParseInfoString("Enter Student City Of Residence: SmithCity", TextFormatting.residencePattern);
        int studentNumber = Functions.TryParseInfoInt("Enter Student Number: IE: 12341234");

        Student student = new Student(studentName, studentAge, studentPhoneNumber, studentEMail, studentAddress, studentNumber);

        // Check if the student is valid, or if a student with the given name already exists
        if (GetStudent(student.getName()) != null) {
            System.out.println("The student already exists!");
            HandleStudentManager();
            return;
        }

        studentList.add(student);
    }

    void RemoveStudent() {
        System.out.println("Which mentor do you wish to remove?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleStudentManager();
            return;
        }

        // Otherwise remove the student from the list
        studentList.remove(student);
    }

    void StudentList() {
        if (studentList.isEmpty()) return;

        Logger.Log("All Students:");
        for (Student studentListItem : studentList)
            Logger.Log(STR."\{studentListItem.toString()}");
    }

    void StudentInfo() {
        Logger.Log("Which student do you wish to see the information of?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleStudentManager();
            return;
        }

        student.getStudentInfo();
    }

    Student GetStudent(String studentName) {
        // Get first student with given name, return null if it isn't in the list
        return studentList.stream()
                .filter(student -> student.getName().equalsIgnoreCase(studentName))
                .findFirst().orElse(null);
    }
}

class TeacherManager {
    // Variables
    ArrayList<Teacher> teacherList;

    static TeacherManager instance;

    public static TeacherManager getInstance() {
        if (instance == null) {
            instance = new TeacherManager();
        }

        return instance;
    }

    TeacherManager() {
        teacherList = new ArrayList<>();
    }

    // Getters
    public ArrayList<Teacher> getStudentList() {
        return teacherList;
    }
}

class MentorManager extends TeacherManager {
    // Variables
    ArrayList<Mentor> mentorList;

    static MentorManager instance;

    public static MentorManager getInstance() {
        if (instance == null) {
            instance = new MentorManager();
        }
        return instance;
    }

    MentorManager() {
        mentorList = new ArrayList<>();
    }

    public ArrayList<Mentor> getMentorList() {
        return mentorList;
    }
}

class ClassroomManager {
    // Variables
    ArrayList<Classroom> classrooms;

    static ClassroomManager instance;

    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }

        return instance;
    }

    public ClassroomManager()
    {
        classrooms = new ArrayList<>();

        classrooms.add(new Classroom("Class 1"));
        classrooms.add(new Classroom("Class 2"));
        classrooms.add(new Classroom("Class 3"));
        classrooms.add(new Classroom("Class 4"));
        classrooms.add(new Classroom("Class 5"));
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }
}

//endregion

//region Classroom

class Classroom {
    // Variables
    String classroomName;
    Mentor classMentor;
    ArrayList<Student> classStudents;

    public Classroom(String classroomName) {
        this.classroomName = classroomName;
    }

    // Mentor
    public Mentor getMentor() {
        return classMentor;
    }

    public void setMentor(Mentor mentor) {
        classMentor = mentor;
    }

    // Student
    public ArrayList<Student> getStudents() {
        return classStudents;
    }

    public void setClassStudents(ArrayList<Student> classStudents) {
        this.classStudents = classStudents;
    }

    public void addStudent(Student student) {
        classStudents.add(student);
    }

    public void removeStudent(Student student) {
        classStudents.remove(student);
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomName='" + classroomName + '\'' +
                ", classMentor=" + classMentor +
                '}';
    }
}

//endregion

//region Objects

class DateOfBirth{
    LocalDateTime birthday;

    public int getAge() {
        return 9999; // TODO: Calculate age from current day with birthday
    }
}

class Address {
    String streetName;
    int houseNumber;
    String residence;
    String postalCode;

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getResidence() {
        return residence;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Address(String streetName, int houseNumber, String residence, String postalCode) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.residence = residence;
        this.postalCode = postalCode;
    }

    public  class Builder {
        private String streetName;
        private int houseNumber;
        private String residence;
        private String postalCode;

        public Address.Builder setStreetName(String streetName) {
            this.streetName = streetName;

            return this;
        }

        public Address.Builder setHouseNumber(int houseNumber) {
            this.houseNumber = houseNumber;

            return this;
        }

        public Address.Builder setResidence(String residence) {
            this.residence = residence;

            return this;
        }

        public Address.Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;

            return this;
        }
    }
}

//endregion

//region Functions

class Functions {

    // Check if given string is valid using the given pattern
    public static String TryParseInfoString(String printText, Pattern textPattern) {
        Logger.Log(printText);
        String userInput = System.console().readLine();

        if (!userInput.isEmpty() && textPattern.matcher(userInput).find())
            return userInput;
        else {
            Logger.LogError(STR."Invalid Input: \{userInput}");
            return TryParseInfoString(printText, textPattern);
        }
    }

    // Check if given string is a valid int
    public static int TryParseInfoInt(String printText) {
        Logger.Log(printText);

        Integer returnValue = null;
        while (returnValue == null) {
            try {
                returnValue = Integer.parseInt(System.console().readLine());
            } catch (NumberFormatException e) {
                returnValue = null;
            }
        }

        return returnValue;
    }

    public static Integer parseIntOrNull(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // String Formatting
    static String punctuate(String string) {
        // Check if the given string ends with punctuation, if true just return it, otherwise add a dot to the end
        if (string.matches(".*\\p{Punct}")) return string;
        else
            return STR."\{string}.";
    }

    static String capitalize(String string) {
        // Get the first character of the string, make that uppercase, then add the remaining text after it
        if (string.isEmpty()) return string;
        else
            return STR."\{string.substring(0, 1).toUpperCase()}\{string.substring(1)}";
    }

    static String toTitleCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringPart : string.toLowerCase().split(" "))
            stringBuilder.append(STR."\{capitalize(stringPart)} ");

        return stringBuilder.toString();
    }
}

//endregion

//region Enums

class Enums {

    // Log Style
    public enum LogStyle {
        LOG_STYLE_lowerCase,
        LOG_STYLE_upperCase,
        LOG_STYLE_capitalize,
        LOG_STYLE_titleCase,
        LOG_STYLE_punctuate
    }
}

//endregion

//region Exceptions

class Exceptions {

    //region Invalid Input

    public static void InvalidInputException() {
        ThrowException("Invalid Input!");
    }

    public static void InvalidInputException(String input) {
        ThrowException(STR."Invalid Input:\{input}!");
    }

    //endregion

    public static void ThrowException(String exception) {
        Logger.LogError(exception);
    }
}

//endregion
