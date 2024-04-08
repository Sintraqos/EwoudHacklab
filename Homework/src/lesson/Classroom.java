package lesson;

import java.util.ArrayList;

public class Classroom {
    String className;
    Mentor classMentor;
    ArrayList<Student> classStudents;

    public String getName() {
        return className;
    }

    public Classroom(String className) {
        this.className = className;
        this.classMentor = null;
        this.classStudents = new ArrayList<>();
    }

    //region Student

    public boolean addStudent(Student student) {
        if (classStudents.contains(student)) return false;

        classStudents.add(student);
        return true;
    }

    public boolean removeStudent(Student student)
    {
        if(!classStudents.contains(student)) return false;

        classStudents.remove(student);
        return true;
    }

    //endregion

    //region Mentor

    public boolean setMentor(Mentor mentor) {
        if (classMentor != null) return false;

        classMentor = mentor;
        return true;
    }

    public boolean removeMentor(Mentor mentor)
    {
        if(classMentor != mentor) return false;

        classMentor= null;
        return true;
    }

    //endregion
}
