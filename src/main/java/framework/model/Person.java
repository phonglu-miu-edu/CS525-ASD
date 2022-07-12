package framework.model;

public class Person extends Customer{
    private String birthDate;

    public Person(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        super(name, street, city, state, zip, email);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
