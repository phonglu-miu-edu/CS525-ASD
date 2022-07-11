package ccard.framework;

import java.util.Date;

public class Person extends Customer implements IPerson {
    private String birthDate;

    public Person(String name, String phone, String street, String city, String state, String zip, String email, String birthDate) {
        super(name, phone, street, city, state, zip, email);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
