package ccard.framework;

import java.util.Date;

public class AddPerson implements IOperator {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String birthDate;

    public AddPerson(String name, String phone, String street, String city, String state, String zip, String email, String birthDate) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.birthDate = birthDate;
    }

    @Override
    public void execute() {
        Person person = new Person(name, phone, street, city, state, zip, email, birthDate);
    }
}