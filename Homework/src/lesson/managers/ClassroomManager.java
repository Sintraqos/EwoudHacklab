package lesson.managers;

import lesson.Functions;
import lesson.objects.Classroom;
import lesson.objects.Mentor;
import lesson.objects.Student;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class ClassroomManager {
    ArrayList<Classroom> classroomList;
    Main main;
    Pattern classroomNamePattern;

    public ClassroomManager(Main main, Pattern classroomNamePattern) {
        this.main = main;
        this.classroomNamePattern = classroomNamePattern;

        classroomList = new ArrayList<>();

        TESTAddClassrooms();
    }

    void TESTAddClassrooms() {
        // Create new classrooms
        classroomList.add(new Classroom("Class 1"));
        classroomList.add(new Classroom("Class 2"));
        classroomList.add(new Classroom("Class 3"));
        classroomList.add(new Classroom("Class 4"));
        classroomList.add(new Classroom("Class 5"));

        // Add people to the classrooms
        // Class 1
        classroomList.get(0).setMentor(main.mentorManager.mentorList.get(0));
        classroomList.get(0).addStudent(main.studentManager.studentList.get(0));
        classroomList.get(0).addStudent(main.studentManager.studentList.get(1));
        classroomList.get(0).addStudent(main.studentManager.studentList.get(2));

        // Class 2
        classroomList.get(1).setMentor(main.mentorManager.mentorList.get(1));
        classroomList.get(1).addStudent(main.studentManager.studentList.get(3));
        classroomList.get(1).addStudent(main.studentManager.studentList.get(4));
        classroomList.get(1).addStudent(main.studentManager.studentList.get(5));

        // Class 3
        classroomList.get(2).setMentor(main.mentorManager.mentorList.get(2));

        // Class 4
        classroomList.get(3).addStudent(main.studentManager.studentList.get(6));
        classroomList.get(3).addStudent(main.studentManager.studentList.get(7));
        classroomList.get(3).addStudent(main.studentManager.studentList.get(8));
        classroomList.get(3).addStudent(main.studentManager.studentList.get(9));
    }

    public void HandleClassroom() {
        System.out.println("""
                Classroom Settings:
                Enter: 'create' to create a new classroom
                Enter: 'remove' to remove classroom
                Enter: 'list' to show all classroom
                Enter: 'info' to show given classroom information
                                
                Enter: 'set mentor' to set a mentor to the given classroom
                Enter: 'remove mentor' to remove the mentor from the given classroom
                                
                Enter: 'add student' to add a new student to the given classroom
                Enter: 'remove student' to remove a student from the given classroom
                """);

        switch (System.console().readLine().toLowerCase()) {
            // Classroom
            case "create":
                ClassroomCreate();
                break;
            case "remove":
                ClassroomRemove();
                break;
            case "list":
                ClassroomList();
                break;
            case "info":
                ClassroomInfo();
                break;

            // Mentor
            case "set mentor":
                ClassroomSetMentor();
                break;
            case "remove mentor":
                ClassroomRemoveMentor();
                break;

            // Student
            case "add student":
                ClassroomAddStudent();
                break;
            case "remove student":
                ClassroomRemoveStudent();
                break;

            // Default
            default:
                System.out.println("Invalid Input!");
                HandleClassroom();
                break;
        }

        main.chooseOption();
    }

    //region Classroom: Create - Remove - List - Info

    void ClassroomCreate() {
        System.out.println("Choose the name of the classroom you wish to create");
        Classroom classRoom = new Classroom(Functions.TryParseInfoString("Enter Classroom Name: IE: Class 1", classroomNamePattern));

        // Check if the classroom is valid, or if a classroom with the given name already exists
        if (GetClassroom(classRoom.getName()) != null) {
            System.out.println("The classroom already exists!");
            HandleClassroom();
            return;
        }

        classroomList.add(classRoom);
    }

    void ClassroomRemove() {
        System.out.println("Choose which classroom you wish to remove");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        classroomList.remove(classRoom);
    }

    void ClassroomList() {
        // Loop through all classrooms in the list, and print out the classroom name, if it has a mentor print the mentor name, and if it has students print the student count
        for (Classroom classroomListItem : classroomList) {
            // If the classroom has a mentor, but no students, print the classroom name and the mentor name
            if (classroomListItem.getMentor() != null && classroomListItem.getStudents().isEmpty())
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()} - Mentor Name: \{classroomListItem.getMentor().getName()}");
                // If the classroom has a mentor and students in it, print the classroom name and the mentor name and the student size
            else if (classroomListItem.getMentor() != null)
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()} - Mentor Name: \{classroomListItem.getMentor().getName()} - Class Size: \{classroomListItem.getStudents().size()}");
                // Otherwise print only the classroom name
            else
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()}");
        }
    }

    void ClassroomInfo() {
        System.out.println("Choose which classroom you wish to see the information of");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        System.out.println(STR."""
                        Classroom Name:\{classRoom.getName()}
                        Mentor Name:\{classRoom.getMentor().getName()}
                        Classroom Size:\{classRoom.getStudents().size()}
                        """);
        for (Student studentListItem : classRoom.getStudents())
            System.out.println(STR."Student Name: \{studentListItem.getName()} - Student Number: \{studentListItem.getStudentNumber()}");

        System.out.println();
    }

    //endregion

    //region Mentor: Set - Remove

    void ClassroomSetMentor() {
        // Get classroom
        System.out.println("Choose classroom to add a mentor to");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        // Get mentor
        System.out.println("Choose mentor to add to the classroom");

        // Check if the mentor is in the list, if not, return back
        Mentor mentor = main.mentorManager.GetMentor(System.console().readLine());

        if (mentor == null) {
            System.out.println("The mentor couldn't be found!");
            HandleClassroom();
            return;
        }

        // Set the classroom mentor to the given mentor
        if (classRoom.setMentor(mentor))
            System.out.println(STR."Succesfully set the mentor to: \{mentor.getName()}");
        else
            System.out.println(STR."Failed to set the mentor to: \{mentor.getName()}, is there already a mentor for this classroom?");
    }

    void ClassroomRemoveMentor() {
        // Get classroom
        System.out.println("Choose classroom to remove the mentor from");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        // Get mentor
        System.out.println("Choose mentor to remove from the classroom");

        // Check if the mentor is in the list, if not, return back
        Mentor mentor = main.mentorManager.GetMentor(System.console().readLine());

        if (mentor == null) {
            System.out.println("The mentor couldn't be found!");
            HandleClassroom();
            return;
        }

        // Set the classroom mentor to null
        if (classRoom.removeMentor(mentor))
            System.out.println(STR."Succesfully removed mentor: \{mentor.getName()} from classroom: \{classRoom.getName()}");
        else
            System.out.println(STR."Failed to remove the mentor from: \{classRoom.getName()}, is there a mentor for this classroom?");
    }

    //endregion

    //region Student: Add - Remove

    void ClassroomAddStudent() {
        // Get classroom
        System.out.println("Choose classroom to add a mentor to");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        // Get student
        System.out.println("Choose student to add to the classroom");

        // Check if the mentor is in the list, if not, return back
        Student student = main.studentManager.GetStudent(System.console().readLine());

        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleClassroom();
            return;
        }

        // Add the student to the classroom
        if (classRoom.addStudent(student))
            System.out.println(STR."Succesfully added: \{student.getName()} to the classroom!");
        else
            System.out.println(STR."Failed to add: \{student.getName()} to the classroom, is the student already in this classroom?");
    }

    void ClassroomRemoveStudent() {
        // Get classroom
        System.out.println("Choose classroom to remove the student from");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            HandleClassroom();
            return;
        }

        // Get student
        System.out.println("Choose student to remove from the classroom");

        // Check if the student is in the list, if not, return back
        Student student = main.studentManager.GetStudent(System.console().readLine());

        if (student == null) {
            System.out.println("The student couldn't be found!");
            HandleClassroom();
            return;
        }

        // Set the classroom mentor to null
        if (classRoom.removeStudent(student))
            System.out.println(STR."Succesfully removed student: \{student.getName()} from classroom: \{classRoom.getName()}");
        else
            System.out.println(STR."Failed to remove \{student.getName()} from: \{classRoom.getName()}, is there a mentor for this classroom?");
    }

    //endregion

    Classroom GetClassroom(String classroomName) {
        // Get first student with given name, return null if it isn't in the list
        return classroomList.stream()
                .filter(classroom -> classroom.getName().equalsIgnoreCase(classroomName))
                .findFirst().orElse(null);
    }
}
