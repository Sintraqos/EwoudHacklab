package lesson.managers;

import java.util.regex.Pattern;

public class Main {
    ClassroomManager classroomManager;
    StudentManager studentManager;
    MentorManager mentorManager;

    public void main() {
        System.out.println("Lesson 03 - Classrooms, Mentors And Students");

        // Patterns
        Pattern namePattern = Pattern.compile("[a-zA-Z._%+-]+\\s");
        Pattern eMailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        Pattern phoneNumberPattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
        Pattern addressPattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");
        Pattern residencePattern = Pattern.compile("[a-zA-Z._%+-]");
        Pattern classroomNamePattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");

        // Script References
        studentManager = new StudentManager(this, namePattern, eMailPattern, phoneNumberPattern, addressPattern, residencePattern);
        mentorManager = new MentorManager(this, namePattern, eMailPattern, phoneNumberPattern, addressPattern, residencePattern);
        classroomManager = new ClassroomManager(this, classroomNamePattern);

        chooseOption();
    }

    void chooseOption() {
        System.out.println("""
                Enter: '1' or 'classroom' to see classroom settings
                Enter: '2' or 'mentor' to see mentor settings
                Enter: '3' or 'student' to see student settings
                """);

        switch (System.console().readLine().toLowerCase()) {
            case "1", "classroom":
                classroomManager.HandleClassroom();
                break;

            case "2", "mentor":
                mentorManager.HandleMentor();
                break;

            case "3", "student":
                studentManager.HandleStudent();
                break;

            default:
                System.out.println("Invalid Input!");
                chooseOption();
                break;
        }
    }
}
