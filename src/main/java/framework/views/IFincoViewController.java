package framework.views;

import framework.IFramework;
import framework.models.Account;
import framework.models.Customer;
import framework.models.Entry;

import java.util.Collection;


public interface IFincoViewController {
    void setVisible();

    IFramework getFrameworkApplication();

    void setFrameworkApplication(IFramework frameworkApplication);

    Customer createPerson(String accountNum, String name, String street, String city, String state, Integer zip, String email, String birthDate);

    Customer createCompany(String accountNum, String name, String street, String city, String state, Integer zip, String email, String noEmployees);

    Collection<Customer> getCustomers();

    Account createAccount(Customer customer, String accountNum);

    Collection<Account> getAccounts();

    void generateReport();

    void addInterest();

    Customer findCustomerByName(String name);

    Entry withdraw(Account account, double amount);

    Entry deposit(Account account, double amount);
}
