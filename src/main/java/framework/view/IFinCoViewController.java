package framework.view;

import framework.IFramework;
import framework.model.Account;
import framework.model.Customer;
import framework.model.Entry;

import java.util.Collection;

public interface IFinCoViewController {

    void setVisible();

    IFramework getFrameworkApplication();

    void setFrameworkApplication(IFramework frameworkApplication);

    Customer createCustomer(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate);

    Customer createOrg(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    Collection<Customer> getCustomers();

    Account createAccount(Customer customer, String accountNum);

    Collection<Account> getAccounts();

    void report();

    void addInterest();

    Customer findCustomerByName(String name);

    Entry withdraw(Account account, double amount);

    Entry deposit(Account account, double amount);
}
