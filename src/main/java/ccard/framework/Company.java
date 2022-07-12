package ccard.framework;

public class Company extends Customer implements ICompany {
    private int noOfEmployees;

    public Company(String name, String phone, String street, String city, String state, String zip, String email, int noOfEmployees) {
        super(name, phone, street, city, state, zip, email);
        this.noOfEmployees = noOfEmployees;
    }

    public int getNoOfEmployees() {
        return this.noOfEmployees;
    }
}
