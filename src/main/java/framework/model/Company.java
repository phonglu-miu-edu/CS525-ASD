package framework.model;

public class Company extends Customer implements ICompany {
    private String noEmployees;

    public Company(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        super(name, street, city, state, zip, email);
        this.noEmployees = noEmployees;
    }

    public String getNoEmployees() {
        return noEmployees;
    }

    public void setNoEmployees(String noEmployees) {
        this.noEmployees = noEmployees;
    }
}
