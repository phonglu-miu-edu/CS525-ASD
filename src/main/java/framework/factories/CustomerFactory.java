package framework.factories;

import framework.models.Company;
import framework.models.Customer;
import framework.models.Person;

public class CustomerFactory {
    public static Customer getPerson(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        return new Person(name, street, city, state, zip, email, birthDate);
    }

    public static Customer getCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        return new Company(name, street, city, state, zip, email, noEmployees);
    }
}
