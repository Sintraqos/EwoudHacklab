package lesson;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Main
{
    Pattern namePattern;
    Pattern eMailPattern;
    Pattern phoneNumberPattern;
    Pattern addressPattern;
    Pattern residencePattern;
    Pattern classroomNamePattern;

    ArrayList<Classroom> classRoomList;
    ArrayList<Mentor> mentorList;
    ArrayList<Student> studentList;

    public void main(String[] args)
    {
        System.out.println("Lesson 03 - Classrooms, Mentors And Students");
        Lesson03_Setup();
    }

    void Lesson03_Setup() {
        // Script References

        // Patterns
        namePattern = Pattern.compile("[a-zA-Z._%+-]+\\s");
        eMailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
        phoneNumberPattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$");
        addressPattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");
        residencePattern = Pattern.compile("[a-zA-Z._%+-]");
        classroomNamePattern = Pattern.compile("\\b[A-Za-z]+\\h\\d+\\b");

        // Lists
        classRoomList = new ArrayList<>();
        mentorList = new ArrayList<>();
        studentList = new ArrayList<>();

        //region Testing | so I don't need to add classrooms, mentors and students manually when I'm just testing if everything works

        // Create classrooms
        classRoomList.add(new Classroom("Class 1"));
        classRoomList.add(new Classroom("Class 2"));
        classRoomList.add(new Classroom("Class 3"));
        classRoomList.add(new Classroom("Class 4"));
        classRoomList.add(new Classroom("Class 5"));

        // Create mentors
        mentorList.add(new Mentor("Mentor 1", 55, "06 12341234", "mentor1@mail.com", "streetName", "cityName", 1234));
        mentorList.add(new Mentor("Mentor 2", 55, "06 12341234", "mentor2@mail.com", "streetName", "cityName", 1235));
        mentorList.add(new Mentor("Mentor 3", 55, "06 12341234", "mentor3@mail.com", "streetName", "cityName", 1236));
        mentorList.add(new Mentor("Mentor 4", 55, "06 12341234", "mentor4@mail.com", "streetName", "cityName", 1237));
        mentorList.add(new Mentor("Mentor 5", 55, "06 12341234", "mentor5@mail.com", "streetName", "cityName", 1238));

        // Create Students
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

        // Add them to the classrooms
        // Class 1
        classRoomList.get(0).setMentor(mentorList.get(0));
        classRoomList.get(0).addStudent(studentList.get(0));
        classRoomList.get(0).addStudent(studentList.get(1));
        classRoomList.get(0).addStudent(studentList.get(2));

        // Class 2
        classRoomList.get(1).setMentor(mentorList.get(1));
        classRoomList.get(1).addStudent(studentList.get(3));
        classRoomList.get(1).addStudent(studentList.get(4));
        classRoomList.get(1).addStudent(studentList.get(5));

        // Class 3
        classRoomList.get(2).setMentor(mentorList.get(2));

        // Class 4
        classRoomList.get(3).addStudent(studentList.get(6));
        classRoomList.get(3).addStudent(studentList.get(7));
        classRoomList.get(3).addStudent(studentList.get(8));
        classRoomList.get(3).addStudent(studentList.get(9));

        //endregion

        Lesson03();
    }

    void Lesson03()
    {
        System.out.println("""
                Enter: 'classroom' to see classroom settings
                Enter: 'mentor' to see mentor settings
                Enter: 'student' to see student settings
                """);

        switch (System.console().readLine().toLowerCase())
        {
            case "classroom":
                Lesson03_Classroom();
                break;

            case "mentor":
                Lesson03_Mentor();
                break;

            case "student":
                Lesson03_Student();
                break;

            default:
                System.out.println("Invalid Input!");
                Lesson03();
                break;
        }
    }

//region Classroom

    void Lesson03_Classroom()
    {
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

        switch (System.console().readLine().toLowerCase())
        {
            // Classroom
            case "create": ClassroomCreate(); break;
            case "remove": ClassroomRemove(); break;
            case "list": ClassroomList(); break;
            case "info": ClassroomInfo(); break;

            // Mentor
            case "set mentor": ClassroomSetMentor(); break;
            case "remove mentor": ClassroomRemoveMentor(); break;

            // Student
            case "add student": ClassroomAddStudent(); break;
            case "remove student": ClassroomRemoveStudent(); break;

            // Default
            default:
                System.out.println("Invalid Input!");
                Lesson03_Classroom();
                break;
        }

        // Return to the start
        Lesson03();
    }

    //region Classroom: Create - Remove - List - Info

    void ClassroomCreate()
    {
        System.out.println("Choose the name of the classroom you wish to create");
        Classroom classRoom = new Classroom(Functions.TryParseInfoString("Enter Classroom Name: IE: Class 1", classroomNamePattern));

        // Check if the classroom is valid, or if a classroom with the given name already exists
        if(GetClassroom(classRoom.getName()) != null)
        {
            System.out.println("The classroom already exists!");
            Lesson03_Classroom();
            return;
        }

        classRoomList.add(classRoom);
    }

    void ClassroomRemove()
    {
        System.out.println("Choose which classroom you wish to remove");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if(classRoom == null)
        {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        classRoomList.remove(classRoom);
    }

    void ClassroomList()
    {
        // Loop through all classrooms in the list, and print out the classroom name, if it has a mentor print the mentor name, and if it has students print the student count
        for(Classroom classroomListItem : classRoomList)
        {
            // If the classroom has a mentor, but no students, print the classroom name and the mentor name
            if(classroomListItem.classMentor != null && classroomListItem.classStudents.isEmpty())
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()} - Mentor Name: \{classroomListItem.classMentor.getName()}");
            // If the classroom has a mentor and students in it, print the classroom name and the mentor name and the student size
            else if(classroomListItem.classMentor != null)
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()} - Mentor Name: \{classroomListItem.classMentor.getName()} - Class Size: \{classroomListItem.classStudents.size()}");
            // Otherwise print only the classroom name
            else
                System.out.println(STR."Classroom Name: \{classroomListItem.getName()}");
        }
    }

    void ClassroomInfo()
    {
        System.out.println("Choose which classroom you wish to see the information of");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if(classRoom == null)
        {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        System.out.println(STR."""
                        Classroom Name:\{classRoom.getName()}
                        Mentor Name:\{classRoom.classMentor.getName()}
                        Classroom Size:\{classRoom.classStudents.size()}
                        """);
        for(Student studentListItem : classRoom.classStudents)
            System.out.println(STR."Student Name: \{studentListItem.getName()} - Student Number: \{studentListItem.getStudentNumber()}");

        System.out.println();

    }

    //endregion

    //region Mentor: Set - Remove

    void ClassroomSetMentor()
    {
        // Get classroom
        System.out.println("Choose classroom to add a mentor to");

        // Check if the classroom is in the list, if not, return back
    Classroom classRoom = GetClassroom(System.console().readLine());

        if(classRoom == null)
        {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Get mentor
        System.out.println("Choose mentor to add to the classroom");

        // Check if the mentor is in the list, if not, return back
       Mentor mentor = GetMentor(System.console().readLine());

        if(mentor == null)
        {
            System.out.println("The mentor couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Set the classroom mentor to the given mentor
        if(classRoom.setMentor(mentor))
            System.out.println(STR."Succesfully set the mentor to: \{mentor.getName()}");
        else
            System.out.println(STR."Failed to set the mentor to: \{mentor.getName()}, is there already a mentor for this classroom?");
    }

    void ClassroomRemoveMentor()
    {
        // Get classroom
        System.out.println("Choose classroom to remove the mentor from");

        // Check if the classroom is in the list, if not, return back
     Classroom classRoom = GetClassroom(System.console().readLine());

        if(classRoom == null)
        {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Get mentor
        System.out.println("Choose mentor to remove from the classroom");

        // Check if the mentor is in the list, if not, return back
      Mentor mentor = GetMentor(System.console().readLine());

        if(mentor == null)
        {
            System.out.println("The mentor couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Set the classroom mentor to null
        if(classRoom.removeMentor(mentor))
            System.out.println(STR."Succesfully removed mentor: \{mentor.getName()} from classroom: \{classRoom.getName()}");
        else
            System.out.println(STR."Failed to remove the mentor from: \{classRoom.getName()}, is there a mentor for this classroom?");
    }

    //endregion

    //region Student: Add - Remove

    void ClassroomAddStudent()
    {
        // Get classroom
        System.out.println("Choose classroom to add a mentor to");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if(classRoom == null)
        {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Get student
        System.out.println("Choose student to add to the classroom");

        // Check if the mentor is in the list, if not, return back
        Student student = GetStudent(System.console().readLine());

        if(student == null)
        {
            System.out.println("The student couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Add the student to the classroom
        if(classRoom.addStudent(student))
            System.out.println(STR."Succesfully added: \{student.getName()} to the classroom!");
        else
            System.out.println(STR."Failed to add: \{student.getName()} to the classroom, is the student already in this classroom?");
    }

    void ClassroomRemoveStudent()
    {
        // Get classroom
        System.out.println("Choose classroom to remove the mentor from");

        // Check if the classroom is in the list, if not, return back
        Classroom classRoom = GetClassroom(System.console().readLine());

        if (classRoom == null) {
            System.out.println("That classroom couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Get student
        System.out.println("Choose student to remove from the classroom");

        // Check if the student is in the list, if not, return back
        Student student = GetStudent(System.console().readLine());

        if (student == null) {
            System.out.println("The student couldn't be found!");
            Lesson03_Classroom();
            return;
        }

        // Set the classroom mentor to null
        if (classRoom.removeStudent(student))
            System.out.println(STR."Succesfully removed student: \{student.getName()} from classroom: \{classRoom.getName()}");
        else
            System.out.println(STR."Failed to remove \{student.getName()} from: \{classRoom.getName()}, is there a mentor for this classroom?");
    }

    //endregion

    Classroom GetClassroom(String classroomName)
    {
        // Get first student with given name, return null if it isn't in the list
        return classRoomList.stream()
                .filter(classroom -> classroom.getName().equalsIgnoreCase(classroomName))
                .findFirst().orElse(null);
    }

//endregion

//region Mentor

    void Lesson03_Mentor()
    {
        System.out.println("""
                Mentor Settings:
                Enter: 'create' to make a new Mentor
                Enter: 'remove' to remove Mentor
                Enter: 'list' to show all Mentors
                Enter: 'info' to show given Mentor information
                """);

        switch (System.console().readLine().toLowerCase())
        {
            // Mentor: Create - Remove - List - Info
            case "create": MentorCreate(); break;

            case "remove": MentorRemove(); break;

            case "list": MentorList(); break;

            case "info": MentorInfo(); break;

            // Return
            case "return": Lesson03(); break;

            // Info
            default:
                System.out.println("Invalid Input!");
                Lesson03_Mentor();
                break;
        }

        // Return to the start
        Lesson03();
    }

    void MentorCreate()
    {
        // Get mentor info, give the printable text and pattern with it, return the string back
        String mentorName = Functions.TryParseInfoString("Enter Mentor Name: IE: Jane Doe", namePattern);
        int mentorAge = Functions.TryParseInfoInt("Enter Mentor Age: IE: 32");
        String mentorPhoneNumber = Functions.TryParseInfoString("Enter Mentor Phone Number: IE: 06 12341234", phoneNumberPattern);
        String mentorEMail = Functions.TryParseInfoString("Enter Mentor E-Mail Address: IE: Jane@Doe.com", eMailPattern);
        String mentorAddress = Functions.TryParseInfoString("Enter Mentor Address: IE: DoeStreet a05", addressPattern);
        String mentorResidence = Functions.TryParseInfoString("Enter Mentor City Of Residence: DoeTown", residencePattern);
        int mentorNumber = Functions.TryParseInfoInt("Enter Mentor Number: IE: 43214321");

        // Create new mentor
        Mentor mentor = new Mentor(mentorName, mentorAge, mentorPhoneNumber,mentorEMail,mentorAddress,mentorResidence,mentorNumber);

        // Check if a mentor with the given name already exists
        if(GetMentor(mentor.getName()) != null)
        {
            System.out.println("The mentor already exists!");
            Lesson03_Mentor();
            return;
        }

        mentorList.add(mentor);
    }

    void MentorRemove()
    {
        System.out.println("Which mentor do you wish to remove?");
        Mentor mentor = GetMentor(System.console().readLine());

        // Check if the mentor is in the list, if not, return back
        if(mentor == null)
        {
            System.out.println("The mentor couldn't be found!");
            Lesson03_Mentor();
            return;
        }

        // Otherwise remove the mentor from the list
        mentorList.remove(mentor);
    }

    void MentorList()
    {
        for(Mentor mentorListItem : mentorList)
            System.out.println(STR."Mentor Name: \{mentorListItem.getName()} - Employee Number: \{mentorListItem.getEmployeeNumber()}");
    }

    void MentorInfo()
    {
        System.out.println("Which mentor do you wish to see the information of?");
        Mentor mentor = GetMentor(System.console().readLine());

        // Check if the mentor is in the list, if not, return back
        if(mentor == null)
        {
            System.out.println("The mentor couldn't be found!");
            Lesson03_Mentor();
            return;
        }

        System.out.println(STR."""
                        Mentor Name:\{mentor.getName()}
                        Mentor Age:\{mentor.getAge()}
                        Mentor Phone Number:\{mentor.getPhoneNumber()}
                        Mentor E-Mail:\{mentor.getEMail()}
                        Mentor Address:\{mentor.getAddress()}
                        Mentor City:\{mentor.getResidence()}
                        Employee Number:\{mentor.getEmployeeNumber()}
                        """);
    }

    Mentor GetMentor(String mentorName)
    {
        // Get first mentor with given name, return null if it isn't in the list
        return mentorList.stream()
                .filter(mentor -> mentor.getName().equalsIgnoreCase(mentorName))
                .findFirst().orElse(null);
    }

//endregion

//region Student

    void Lesson03_Student()
    {
        System.out.println("""
                Student Settings:
                Enter: 'create' to make a new student
                Enter: 'remove' to remove given
                Enter: 'list' to show all students
                Enter: 'info' to show info of given student
                """);

        switch (System.console().readLine().toLowerCase())
        {
            // Student: Create - Remove - List - Info
            case "create": StudentCreate(); break;

            case "remove": StudentRemove(); break;

            case "list": StudentList(); break;

            case "info": StudentInfo(); break;

            // Return
            case "return": Lesson03(); break;

            // Default
            default:
                System.out.println("Invalid Input!");
                Lesson03_Student();
                break;
        }

        // Return to the start
        Lesson03();
    }

    void StudentCreate()
    {
        String studentName = Functions.TryParseInfoString("Enter Student Name: IE: John Smith", namePattern);
        int studentAge = Functions.TryParseInfoInt("Enter Student Age: IE: 16");
        String studentPhoneNumber = Functions.TryParseInfoString("Enter Student Phone Number: IE: 06 12341234", phoneNumberPattern);
        String studentEMail = Functions.TryParseInfoString("Enter Student E-Mail Address: IE: John@Smith.com", eMailPattern);
        String studentAddress = Functions.TryParseInfoString("Enter Student Address: IE: SmithStreet a05", addressPattern);
        String studentResidence = Functions.TryParseInfoString("Enter Student City Of Residence: SmithCity", residencePattern);
        int studentNumber = Functions.TryParseInfoInt("Enter Student Number: IE: 12341234");

        Student student = new Student(studentName, studentAge, studentPhoneNumber,studentEMail,studentAddress,studentResidence,studentNumber);

        // Check if the student is valid, or if a student with the given name already exists
        if(GetStudent(student.getName()) != null)
        {
            System.out.println("The student already exists!");
            Lesson03_Student();
            return;
        }

        studentList.add(student);
    }

    void StudentRemove()
    {
        System.out.println("Which mentor do you wish to remove?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if(student == null)
        {
            System.out.println("The student couldn't be found!");
            Lesson03_Student();
            return;
        }

        // Otherwise remove the student from the list
        studentList.remove(student);

    }

    void StudentList()
    {
        if(studentList.isEmpty()) return;

        System.out.println("All Students:");
        for(Student studentListItem : studentList)
            System.out.println(STR."Student Name: \{studentListItem.getName()} - Student Number: \{studentListItem.getStudentNumber()}");
        System.out.println();
    }

    void StudentInfo()
    {
        System.out.println("Which student do you wish to see the information of?");

        Student student = GetStudent(System.console().readLine());

        // Check if the student is in the list, if not, return back
        if(student == null)
        {
            System.out.println("The student couldn't be found!");
            Lesson03_Student();
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

    Student GetStudent(String studentName)
    {
        // Get first student with given name, return null if it isn't in the list
        return studentList.stream()
                .filter(student -> student.getName().equalsIgnoreCase(studentName))
                .findFirst().orElse(null);
    }

    //endregion
}
