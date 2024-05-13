package lessonRevised;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Pattern;

import static java.util.Map.entry;

//region Main

public class Main {
    static Main instance;

    public static Main getInstance() {
        if (instance == null) {
            instance = new Main();
        }

        return instance;
    }

    StudentManager studentManager ;
    TeacherManager teacherManager ;
    MentorManager mentorManager ;
    ClassroomManager classroomManager ;
    Mailer mailer;

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
         studentManager = StudentManager.getInstance();
         teacherManager = MentorManager.getInstance();
         mentorManager = MentorManager.getInstance();
         classroomManager = ClassroomManager.getInstance();
         mailer = Mailer.getInstance();

        HandleInput();
    }

    void HandleInput()
    {
        Logger.Log("""
                Student Settings:
                Enter: '1' / 'student' to manage students
                Enter: '2' / 'teacher' to manage teachers
                Enter: '3' / 'mentor' to manage mentors
                """);

        String input = System.console().readLine();
        switch (input.toLowerCase()) {
            case "1", "student":
                studentManager.HandleStudentManager();
                break;

            case "2", "teacher":
                teacherManager.HandleTeacherManager();
                break;

            case "3", "mentor":
                mentorManager.HandleTeacherManager();
                break;

            default:
                Exceptions.InvalidInputException(input);
                HandleInput();
                break;
        }
    }
}

//endregion

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
        System.out.printf(String.format("%s", input));
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

    Map<Subject, int> studentSubjects;

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
        Logger.Log(String.format("""
                        Student Name: %s
                        Student Age: %s
                        Student Phone Number: %s
                        Student E-Mail: %s
                        Student Address: %s
                        Student Number: %s
                        """, personName, getAge(), personPhoneNumber,personEMail, getAddress(), studentNumber));
    }
}

class Teacher extends PersonMaster {
    // Variables
    int employeeNumber;

    Map<String, Boolean> hoursDeclared = Map.ofEntries(
            entry("januari", false),
            entry("februari", false),
            entry("march", false),
            entry("april", false),
            entry("may", false),
            entry("june", false),
            entry("juli", false),
            entry("august", false),
            entry("september", false),
            entry("october", false),
            entry("november", false),
            entry("december", false)
    );

    // Getters
    public int getEmployeeNumber() {
        return employeeNumber;
    }

    // Setters
    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public Teacher() {
    }

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

    public void getTeacherInfo() {
        Logger.Log(String.format("""
                Teacher Name: %s
                Teacher Age: %s
                Teacher Phone Number: %s
                Teacher E-Mail: %s
                Teacher Address: %s
                Teacher Number: %s
                """, personName, getAge(), personPhoneNumber, personEMail, getAddress(), employeeNumber));
    }

    public void DeclareHour(String month, Boolean hasDeclared) {
        // Check if the given month is valid
        if (!hoursDeclared.containsKey(month)) {
            Logger.LogWarning("Invalid Month!");
            return;
        }

        hoursDeclared.put(month, hasDeclared);
    }

    public Map<String, Boolean> getHoursDeclared() {
        return hoursDeclared;
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
        Logger.Log(String.format("""
                        Mentor Name: %s
                        Mentor Age: %s
                        Mentor Phone Number: %s
                        Mentor E-Mail: %s
                        Mentor Address: %s
                        Employee Number: %s
                        Mentor Class: %s
                        """, personName, getAge(), personPhoneNumber,personEMail, getAddress(), employeeNumber, mentorClass));
    }
}

//endregion

//region Managers

//region ---- Student Manager

class StudentManager {

    // Variables
    Main main;
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
        main = Main.getInstance();

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
                Enter: '5' / 'mail' to send a mail to all the students
                Enter: '6' / 'return' to return to the main functions
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

            case "5", "mail":
                SendMail();
                break;

            case "6", "return":
                main.HandleInput();
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
        Logger.Log("Which mentor do you wish to remove?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            Logger.LogWarning("The student couldn't be found!");
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
            Logger.Log(studentListItem.toString());
    }

    void StudentInfo() {
        Logger.Log("Which student do you wish to see the information of?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            Logger.LogWarning("The student couldn't be found!");
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

    public void SendMail() {
        // Go through the entire student list
        for (Student student : studentList) {
            // Go through all the subject the student takes, and check if the student has failed the class, if true send the email
            for (Map.Entry<Subject, int> entry : student.studentSubjects.entrySet()) {

                StringBuilder stringBuilder = new StringBuilder();
                if (entry.getValue() < 6) {
                    stringBuilder.append(String.format("%s class grade: %d,\n", entry.getKey(), entry.getValue()));
                }

                // Check if the string builder is empty, if not send the mail
                if (!stringBuilder.toString().isEmpty()) {
                    Mailer.instance.SendMail(student, String.format("""
                            Dear %s,
                                                        
                            You have failed %s, you currently have an %d for this subject.
                                                        
                            This is an automated mail, and you don't need to respond back
                            """, student.personName, stringBuilder, entry.getValue()));
                }
            }
        }
    }
}

//endregion

//region ---- Teacher Manager

class TeacherManager {
    // Variables
    Main main;
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
        main = Main.getInstance();
    }

    // Getters
    public ArrayList<Teacher> getTeacherList() {
        return teacherList;
    }

    public void HandleTeacherManager() {
        Logger.Log("""
                Teacher Settings:
                Enter: '1' / 'create' to make a new teacher
                Enter: '2' / 'remove' to remove given teacher
                Enter: '3' / 'list' to show all teacher
                Enter: '4' / 'info' to show info of given teacher
                Enter: '5' / 'mail' to send a mail to all the teachers
                Enter: '6' / 'return' to return to the main functions
                """);

        String input = System.console().readLine();
        switch (input.toLowerCase()) {
            case "1", "create":
                AddTeacher();
                break;

            case "2", "remove":
                RemoveTeacher();
                break;

            case "3", "list":
                TeacherList();
                break;

            case "4", "info":
                TeacherInfo();
                break;

            case "5", "mail":
                SendMail();
                break;

            case "6", "return":
                main.HandleInput();
                break;

            default:
                Exceptions.InvalidInputException(input);
                HandleTeacherManager();
                break;
        }
    }

    void AddTeacher() {
        String teacherName = Functions.TryParseInfoString("Enter Teacher Name: IE: John Smith", TextFormatting.namePattern);
        Logger.Log("Enter Teacher Age: IE: 16");
        DateOfBirth teacherAge = new DateOfBirth();
        String teacherPhoneNumber = Functions.TryParseInfoString("Enter Teacher Phone Number: IE: 06 12341234", TextFormatting.phoneNumberPattern);
        String teacherEMail = Functions.TryParseInfoString("Enter Teacher E-Mail Address: IE: John@Smith.com", TextFormatting.eMailPattern);
        Logger.Log("Enter Teacher Address: IE: SmithStreet a05");
        Address teacherAddress = new Address("", 1, "", "");
        String teacherResidence = Functions.TryParseInfoString("Enter Teacher City Of Residence: SmithCity", TextFormatting.residencePattern);
        int employeeNumber = Functions.TryParseInfoInt("Enter Employee Number: IE: 12341234");

        Teacher teacher = new Teacher(teacherName, teacherAge, teacherPhoneNumber, teacherEMail, teacherAddress, employeeNumber);

        // Check if the student is valid, or if a student with the given name already exists
        if (GetTeacher(teacher.getName()) != null) {
            System.out.println("The teacher already exists!");
            HandleTeacherManager();
            return;
        }

        teacherList.add(teacher);
    }

    void RemoveTeacher() {
        Logger.Log("Which mentor do you wish to remove?");

        Teacher teacher = GetTeacher(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (teacher == null) {
            Logger.LogWarning("The teacher couldn't be found!");
            HandleTeacherManager();
            return;
        }

        // Otherwise remove the student from the list
        teacherList.remove(teacher);
    }

    void TeacherList() {
        if (teacherList.isEmpty()) return;

        Logger.Log("All Teachers:");
        for (Teacher teacher : teacherList)
            Logger.Log(teacher.toString());
    }

    void TeacherInfo() {
        Logger.Log("Which teacher do you wish to see the information of?");

        Teacher teacher = GetTeacher(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (teacher == null) {
            Logger.LogWarning("The teacher couldn't be found!");
            HandleTeacherManager();
            return;
        }

        teacher.getTeacherInfo();
    }

    public void SendMail() {
        // Go through the entire teacher list
        for (Teacher teacher : teacherList) {
            // Go through all the subject the student takes, and check if the student has failed the class, if true send the email
            for (Map.Entry<String, Boolean> entry : teacher.hoursDeclared.entrySet()) {
                StringBuilder stringBuilder = new StringBuilder();
                if (!entry.getValue()) {
                        stringBuilder.append(String.format("%s,\n", entry.getKey()));
                }

                // Check if the string builder is empty, if not send the mail
                if(!stringBuilder.toString().isEmpty())                {
                    Mailer.instance.SendMail(teacher, String.format("""
                            Dear %s,
                                                        
                            You have not yet filled the months of: %s please do so as soon as possible
                                                        
                            This is an automated mail, and you don't need to respond back
                            """, teacher.personName, stringBuilder));
                }
            }
        }
    }

    Teacher GetTeacher(String teacherName) {
        // Get first teacher with given name, return null if it isn't in the list
        return teacherList.stream()
                .filter(teacher -> teacher.getName().equalsIgnoreCase(teacherName))
                .findFirst().orElse(null);
    }
}

//endregion

//region ---- Mentor Manager

class MentorManager extends TeacherManager {
    // Variables
    Main main;
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
        main = Main.getInstance();
    }

    public ArrayList<Mentor> getMentorList() {
        return mentorList;
    }
}

class ClassroomManager {
    // Variables
    Main main;
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
        main = Main.getInstance();

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

//region Mailer

class Mailer{

    static Mailer instance;

    public static Mailer getInstance() {
        if (instance == null) {
            instance = new Mailer();
        }

        return instance;
    }

    // Since both teachers and students are derived from PersonMaster, use it as the input
    public void SendMail(PersonMaster person, String eMailContent)    {
        Logger.Log(String.format("""
                Send Mail to: %s
                Email Content:
                %s
                """, person.getEMail(), eMailContent));

    }
}

//endregion

//region Subject

class Subject{
    String subjectName;
    Teacher subjectTeacher;
    int subjectMaxStudentCount = 10;

    public String getSubjectName(){
        return subjectName;
    }

    public Subject(String subjectName,    Teacher subjectTeacher,    int subjectMaxStudentCount) {
        this.subjectName = subjectName;
        this.subjectTeacher = subjectTeacher;
        this.subjectMaxStudentCount = subjectMaxStudentCount;
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
            Exceptions.InvalidInputException(userInput);
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
            return String.format("%s.",string);
    }

    static String capitalize(String string) {
        // Get the first character of the string, make that uppercase, then add the remaining text after it
        if (string.isEmpty()) return string;
        else
            return String.format("%s%s",string.substring(0, 1).toUpperCase(),string.substring(1));
    }

    static String toTitleCase(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String stringPart : string.toLowerCase().split(" "))
            stringBuilder.append(String.format("%s ",capitalize(stringPart)));

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

    public static void InvalidInputException(String input) {
        ThrowException(String.format("Invalid Input: %s!", input));
    }

    //endregion

    public static void ThrowException(String exception) {
        Logger.LogError(exception);
    }
}

//endregion
