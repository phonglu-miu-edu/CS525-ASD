package framework.command;

import framework.IFinco;
import framework.model.Customer;

public class AddOrganization implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Customer customer;
    private String noEmployees;
    private IFinco finCo;

    public AddOrganization(String name,
                           String street,
                           String city,
                           String state,
                           Integer zip,
                           String email,
                           String noEmployees,
                           IFinco finCo) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.noEmployees = noEmployees;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        customer = finCo.createOrganization(name, street, city, state, zip, email, noEmployees);
    }

    public Customer getCustomer() {
        return customer;
    }
}
