package lesson.managers;

import lesson.Functions;
import lesson.objects.Student;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class StudentManager {
    ArrayList<Student> studentList;

    Main main;
    Pattern namePattern;
    Pattern eMailPattern;
    Pattern phoneNumberPattern;
    Pattern addressPattern;
    Pattern residencePattern;

    public StudentManager(Main main, Pattern namePattern, Pattern eMailPattern, Pattern phoneNumberPattern, Pattern addressPattern, Pattern residencePattern) {
        this.main = main;
        this.namePattern = namePattern;
        this.eMailPattern = eMailPattern;
        this.phoneNumberPattern = phoneNumberPattern;
        this.addressPattern = addressPattern;
        this.residencePattern = residencePattern;

        studentList = new ArrayList<>();

        TESTAddStudents();
    }

    void TESTAddStudents() {
        studentList.add(new Student("Student 1", 15, "06 12341234", "student1@mail.com", "streetName", "cityName", 1234));
        studentList.add(new Student("Student 2", 15, "06 12341234", "student2@mail.com", "streetName", "cityName", 1235));
        studentList.add(new Student("Student 3", 15, "06 12341234", "student3@mail.com", "streetName", "cityName", 1236));
        studentList.add(new Student("Student 4", 15, "06 12341234", "student4@mail.com", "streetName", "cityName", 1237));
        studentList.add(new Student("Student 5", 15, "06 12341234", "student5@mail.com", "streetName", "cityName", 1238));
        studentList.add(new Student("Student 6", 15, "06 12341234", "student6@mail.com", "streetName", "cityName", 1239));
        studentList.add(new Student("Student 7", 15, "06 12341234", "student7@mail.com", "streetName", "cityName", 1240));
        studentList.add(new Student("Student 8", 15, "06 12341234", "student8@mail.com", "streetName", "cityName", 1241));
        studentList.add(new Student("Student 9", 15, "06 12341234", "student9@mail.com", "streetName", "cityName", 1242));
        studentList.add(new Student("Student 10", 15, "06 12341234", "student10@mail.com", "streetName", "cityName", 1243));
    }

    void HandleStudent() {
        System.out.println("""
                Student Settings:
                Enter: '1' or 'create' to make a new student
                Enter: '2' or 'remove' to remove given
                Enter: '3' or 'list' to show all students
                Enter: '4' or 'info' to show info of given student
                """);

        switch (System.console().readLine().toLowerCase()) {
            // Student: Create - Remove - List - Info
            case "create":
                StudentCreate();
                break;

            case "remove":
                StudentRemove();
                break;

            case "list":
                StudentList();
                break;

            case "info":
                StudentInfo();
                break;

            // Return
            case "return":
                main.chooseOption();

                // Default
            default:
                System.out.println("Invalid Input!");
                HandleStudent();
                break;
        }

        main.chooseOption();
    }

    void StudentCreate() {
        String studentName = Functions.TryParseInfoString("Enter Student Name: IE: John Smith", namePattern);
        int studentAge = Functions.TryParseInfoInt("Enter Student Age: IE: 16");
        String studentPhoneNumber = Functions.TryParseInfoString("Enter Student Phone Number: IE: 06 12341234", phoneNumberPattern);
        String studentEMail = Functions.TryParseInfoString("Enter Student E-Mail Address: IE: John@Smith.com", eMailPattern);
        String studentAddress = Functions.TryParseInfoString("Enter Student Address: IE: SmithStreet a05", addressPattern);
        String studentResidence = Functions.TryParseInfoString("Enter Student City Of Residence: SmithCity", residencePattern);
        int studentNumber = Functions.TryParseInfoInt("Enter Student Number: IE: 12341234");

        Student student = new Student(studentName, studentAge, studentPhoneNumber, studentEMail, studentAddress, studentResidence, studentNumber);

        // Check if the student is valid, or if a student with the given name already exists
        if (GetStudent(student.getName()) != null) {
            System.out.println("The student already exists!");
            HandleStudent();
            return;
        }

        studentList.add(student);
    }

    void StudentRemove() {
        System.out.println("Which mentor do you wish to remove?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleStudent();
            return;
        }

        // Otherwise remove the student from the list
        studentList.remove(student);
    }

    void StudentList() {
        if (studentList.isEmpty()) return;

        System.out.println("All Students:");
        for (Student studentListItem : studentList)
            System.out.println(STR."Student Name: \{studentListItem.getName()} - Student Number: \{studentListItem.getStudentNumber()}");
        System.out.println();
    }

    void StudentInfo() {
        System.out.println("Which student do you wish to see the information of?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleStudent();
            return;
        }

        System.out.println(STR."""
                        Student Name:\{student.getName()}
                        Student Age:\{student.getAge()}
                        Student Phone Number:\{student.getPhoneNumber()}
                        Student E-Mail:\{student.getEMail()}
                        Student Address:\{student.getAddress()}
                        Student City:\{student.getResidence()}
                        Student Number:\{student.getStudentNumber()}
                        """);
    }

    Student GetStudent(String studentName) {
        // Get first student with given name, return null if it isn't in the list
        return studentList.stream()
                .filter(student -> student.getName().equalsIgnoreCase(studentName))
                .findFirst().orElse(null);
    }
}
