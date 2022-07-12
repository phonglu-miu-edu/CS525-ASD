package framework.commands;

import framework.IFinCo;
import framework.models.Customer;

public class AddPersonOperation implements IOperation {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private Customer customer;
    private String birthDate;
    private IFinCo finCo;

    public AddPersonOperation(String name,
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
        customer = finCo.createPerson(name, street, city, state, zip, email, birthDate);
    }

    public Customer getCustomer() {
        return customer;
    }
}
