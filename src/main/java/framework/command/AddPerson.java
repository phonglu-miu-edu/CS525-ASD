package framework.command;

import framework.IFinCo;
import framework.model.Customer;

public class AddPerson implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Customer customer;
    private String birthDate;
    private IFinCo finCo;

    public AddPerson(String name,
                     String street,
                     String city,
                     String state,
                     Integer zip,
                     String email,
                     String birthDate,
                     IFinCo finCo) {
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
