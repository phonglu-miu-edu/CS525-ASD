package framework.command;

import framework.IFinco;
import framework.model.Customer;

public class AddCustomer implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Customer customer;
    private String birthDate;
    private IFinco finCo;

    public AddCustomer(String name,
                       String street,
                       String city,
                       String state,
                       Integer zip,
                       String email,
                       String birthDate,
                       IFinco finCo) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.email = email;
        this.birthDate = birthDate;
        this.finCo = finCo;
    }

    @Override
    public void execute() {
        customer = finCo.createCustomer(name, street, city, state, zip, email, birthDate);
    }

    public Customer getCustomer() {
        return customer;
    }
}
