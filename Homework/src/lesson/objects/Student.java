package lesson.objects;

public class Student extends PersonMaster {
    // Add student specific variables to this, in this example the student number
    int studentNumber;

    public int getStudentNumber() {
        return studentNumber;
    }

    // Create new student with given input
    public Student(
            String studentName,
            int studentAge,
            String studentPhoneNumber,
            String studentEMail,
            String studentAddress,
            String studentResidence,
            int studentNumber) {
        personName = studentName;
        personAge = studentAge;
        personPhoneNumber = studentPhoneNumber;
        personEMail = studentEMail;
        personAddress = studentAddress;
        personResidence = studentResidence;
        this.studentNumber = studentNumber;
    }
}
