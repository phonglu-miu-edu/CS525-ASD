package framework.factory;

import framework.model.Customer;
import framework.model.Organization;
import framework.model.Person;

public class SimpleFactory {

    public static Customer getCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        return new Person(name, street, city, state, zip, email, birthDate);
    }

    public static Customer getCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        return new Organization(name, street, city, state, zip, email, noEmployees);
    }
}
