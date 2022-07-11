package ccard.framework;

public class CustomerFactory {
    public static Person createPerson(String name, String phone, String street, String city, String state, String zip, String email, String birthDate) {
        return new Person(name, phone, street, city, state, zip, email, birthDate);
    }
}

