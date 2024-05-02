package lesson.managers;

import lesson.Functions;
import lesson.objects.Mentor;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class MentorManager {

    ArrayList<Mentor> mentorList;

    Main main;
    Pattern namePattern;
    Pattern eMailPattern;
    Pattern phoneNumberPattern;
    Pattern addressPattern;
    Pattern residencePattern;

    public MentorManager(Main main, Pattern namePattern, Pattern eMailPattern, Pattern phoneNumberPattern, Pattern addressPattern, Pattern residencePattern) {
        this.main = main;
        this.namePattern = namePattern;
        this.eMailPattern = eMailPattern;
        this.phoneNumberPattern = phoneNumberPattern;
        this.addressPattern = addressPattern;
        this.residencePattern = residencePattern;

        mentorList = new ArrayList<>();

        TESTAddMentors();
    }

    void TESTAddMentors() {
        mentorList.add(new Mentor("Mentor 1", 55, "06 12341234", "mentor1@mail.com", "streetName", "cityName", 1234));
        mentorList.add(new Mentor("Mentor 2", 55, "06 12341234", "mentor2@mail.com", "streetName", "cityName", 1235));
        mentorList.add(new Mentor("Mentor 3", 55, "06 12341234", "mentor3@mail.com", "streetName", "cityName", 1236));
        mentorList.add(new Mentor("Mentor 4", 55, "06 12341234", "mentor4@mail.com", "streetName", "cityName", 1237));
        mentorList.add(new Mentor("Mentor 5", 55, "06 12341234", "mentor5@mail.com", "streetName", "cityName", 1238));
    }

    public void HandleMentor() {
        System.out.println("""
                Mentor Settings:
                Enter: '1' or 'create' to make a new Mentor
                Enter: '2' or 'remove' to remove Mentor
                Enter: '3' or 'list' to show all Mentors
                Enter: '4' or 'info' to show given Mentor information
                """);

        switch (System.console().readLine().toLowerCase()) {
            // Mentor: Create - Remove - List - Info
            case "create":
                MentorCreate();
                break;

            case "remove":
                MentorRemove();
                break;

            case "list":
                MentorList();
                break;

            case "info":
                MentorInfo();
                break;

            // Return
            case "return":
                main.chooseOption();
                break;

            // Info
            default:
                System.out.println("Invalid Input!");
                HandleMentor();
                break;
        }

        main.chooseOption();
    }

    void MentorCreate() {
        // Get mentor info, give the printable text and pattern with it, return the string back
        String mentorName = Functions.TryParseInfoString("Enter Mentor Name: IE: Jane Doe", namePattern);
        int mentorAge = Functions.TryParseInfoInt("Enter Mentor Age: IE: 32");
        String mentorPhoneNumber = Functions.TryParseInfoString("Enter Mentor Phone Number: IE: 06 12341234", phoneNumberPattern);
        String mentorEMail = Functions.TryParseInfoString("Enter Mentor E-Mail Address: IE: Jane@Doe.com", eMailPattern);
        String mentorAddress = Functions.TryParseInfoString("Enter Mentor Address: IE: DoeStreet a05", addressPattern);
        String mentorResidence = Functions.TryParseInfoString("Enter Mentor City Of Residence: DoeTown", residencePattern);
        int mentorNumber = Functions.TryParseInfoInt("Enter Mentor Number: IE: 43214321");

        // Create new mentor
        Mentor mentor = new Mentor(mentorName, mentorAge, mentorPhoneNumber, mentorEMail, mentorAddress, mentorResidence, mentorNumber);

        // Check if a mentor with the given name already exists
        if (GetMentor(mentor.getName()) != null) {
            System.out.println("The mentor already exists!");
            HandleMentor();
            return;
        }

        mentorList.add(mentor);
    }

    void MentorRemove() {
        System.out.println("Which mentor do you wish to remove?");
        Mentor mentor = GetMentor(System.console().readLine());

        // Check if the mentor is in the list, if not, return back
        if (mentor == null) {
            System.out.println("The mentor couldn't be found!");
            HandleMentor();
            return;
        }

        // Otherwise remove the mentor from the list
        mentorList.remove(mentor);
    }

    void MentorList() {
        for (Mentor mentorListItem : mentorList)
            System.out.println(STR."Mentor Name: \{mentorListItem.getName()} - Employee Number: \{mentorListItem.getEmployeeNumber()}");
    }

    void MentorInfo() {
        System.out.println("Which mentor do you wish to see the information of?");
        Mentor mentor = GetMentor(System.console().readLine());

        // Check if the mentor is in the list, if not, return back
        if (mentor == null) {
            System.out.println("The mentor couldn't be found!");
            HandleMentor();
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

    Mentor GetMentor(String mentorName) {
        // Get first mentor with given name, return null if it isn't in the list
        return mentorList.stream()
                .filter(mentor -> mentor.getName().equalsIgnoreCase(mentorName))
                .findFirst().orElse(null);
    }
}
