package framework.factory;

import framework.model.*;

public class SimpleFactory {
    public static IAccount createAccount(ICustomer customer, String accountNum) {
        return new Account(customer, accountNum);
    }

    public static ICustomer getCustomer(String name, String street, String city, String state, Integer zip, String email, String birthDate) {
        return new Person(name, street, city, state, zip, email, birthDate);
    }

    public static ICustomer getCompany(String name, String street, String city, String state, Integer zip, String email, String noEmployees) {
        return new Company(name, street, city, state, zip, email, noEmployees);
    }
}
