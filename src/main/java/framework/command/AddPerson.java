package framework.command;

import framework.FinCo;
import framework.model.ICustomer;

public class AddPerson implements ICommand {
    private String name;
    private String street;
    private String city;
    private String state;
    private Integer zip;
    private String email;
    private ICustomer customer;
    private String birthDate;
    private FinCo finCo;

    public AddPerson(String name,
                     String street,
                     String city,
                     String state,
                     Integer zip,
                     String email,
                     String birthDate,
                     FinCo finCo) {
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

    public ICustomer getCustomer() {
        return customer;
    }
}
