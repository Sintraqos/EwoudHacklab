package lesson.objects;

public class Mentor extends PersonMaster {
    // Add mentor specific variables to this, in this example the employee number
    int employeeNumber;

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    // Create new mentor with given input
    public Mentor(
            String mentorName,
            int mentorAge,
            String mentorPhoneNumber,
            String mentorEMail,
            String mentorAddress,
            String mentorResidence,
            int employeeNumber) {
        personName = mentorName;
        personAge = mentorAge;
        personPhoneNumber = mentorPhoneNumber;
        personEMail = mentorEMail;
        personAddress = mentorAddress;
        personResidence = mentorResidence;
        this.employeeNumber = employeeNumber;
    }
}
