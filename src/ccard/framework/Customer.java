package ccard.framework;

public abstract class Customer implements ICustomer {
    private String name;
    private String phone;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String email;
    private IAccount account;

    public Customer(String name, String phone, String street, String city, String state, String zip, String email) {
        this.name = name;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
    }

    public void addAccount(IAccount account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }
}
