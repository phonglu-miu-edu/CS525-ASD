package framework.command;

import framework.IFinCo;
import framework.model.Customer;

public class AddCompany implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Customer customer;
    private String noEmployees;
    private IFinCo finCo;

    public AddCompany(String name,
                      String street,
                      String city,
                      String state,
                      Integer zip,
                      String email,
                      String noEmployees,
                      IFinCo finCo) {
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