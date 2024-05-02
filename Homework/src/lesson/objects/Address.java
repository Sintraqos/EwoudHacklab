package lesson.objects;

public class Address {
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
}
