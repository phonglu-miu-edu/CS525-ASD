package framework.models;

import java.util.ArrayList;
import java.util.Collection;

public class Customer implements ICustomer {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Collection<Account> accounts = new ArrayList<>();

    public Customer(String name, String street, String city, String state, Integer zip, String email) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
    }

    @Override
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
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

    public Integer getZip() {
        return zip;
    }

    public String getEmail() {
        return email;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }
}
