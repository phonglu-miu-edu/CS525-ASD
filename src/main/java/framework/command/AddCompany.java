package framework.command;

import framework.IFinCo;
import framework.model.Customer;
import framework.model.ICustomer;

public class AddCompany implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private ICustomer customer;
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
        customer = finCo.createCompany(name, street, city, state, zip, email, noEmployees);
    }

    public ICustomer getCustomer() {
        return customer;
    }
}
