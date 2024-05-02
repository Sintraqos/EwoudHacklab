package lesson.objects;

public class PersonMaster {
    // These are all private, since we only need to be able to fill them when we create them, after that the info only needs to be read
    String personName;
    int personAge;
    String personPhoneNumber;
    String personEMail;
    String personAddress;
    String personResidence;

    public String getName() {
        return personName;
    }

    public int getAge() {
        return personAge;
    }

    public String getPhoneNumber() {
        return personPhoneNumber;
    }

    public String getEMail() {
        return personEMail;
    }

    public String getAddress() {
        return personAddress;
    }

    public String getResidence() {
        return personResidence;
    }
}
